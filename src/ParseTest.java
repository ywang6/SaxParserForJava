import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import junit.framework.TestCase;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ParseTest extends TestCase{

	public void testSAX() throws Throwable{
		SaxParseService sax = new SaxParseService();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("deals.xml");
		List<Deal> deals = sax.getDeals(input);
		for(Deal deal : deals){
			System.out.println(deal.toString());
		}
	}
}