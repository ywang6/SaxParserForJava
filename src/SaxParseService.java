import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SaxParseService extends DefaultHandler{
	private List<Deal> deals = null;
	private Deal deal = null;
	private String preTag = null;//record last textnode
	private int id = 0;
	public List<Deal> getDeals(InputStream xmlStream) throws Exception{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SaxParseService handler = new SaxParseService();
		parser.parse(xmlStream, handler);
		return handler.getDeals();
	}
	
	public List<Deal> getDeals(){
		return deals;
	}
	
	@Override
	public void startDocument() throws SAXException {
		deals = new ArrayList<Deal>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if("a".equals(qName))
		{
			deal = new Deal();
			deal.setId(id++);
		}
		if("div".equals(qName))
		{
			if(attributes.getValue(0).equals("deal-title"))
				preTag = "deal-title";
		}
		if("p".equals(qName))
		{
			 if(attributes.getValue(0).equals("merchant-name"))
				preTag = "merchant-name";
			 if(attributes.getValue(0).equals("location"))
				preTag = "location";
		}
		//passing node name to preTag
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("a".equals(qName)){
			deals.add(deal);
			deal = null;
		}
		preTag = null;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(preTag!=null){
			String content = new String(ch,start,length);
			if("deal-title".equals(preTag)){
				deal.setTitle(content);
			}else if("merchant-name".equals(preTag)){
				deal.setName(content);
			}else if("location".equals(preTag)){
				deal.setLocation(content);
			}
		}
	}
	
}