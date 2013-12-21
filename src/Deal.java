public class Deal {
	private int id;
	private String title;
	private String name;
	private String location;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation(){
		return location;
	}
	public void setLocation(String location){
		this.location = location;
	}
	@Override
	public String toString(){
		return this.id+":"+this.title+":"+this.name+":"+this.location;
	}
}