package entity;

public class Qtype {
	int id;
	String qtype;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public Qtype(int id, String qtype) {
		super();
		this.id = id;
		this.qtype = qtype;
	}
	public Qtype() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
