package entity;

public class flush {
public int id;
public String papername;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getPapername() {
	return papername;
}
public flush(int id, String papername) {
	super();
	this.id = id;
	this.papername = papername;
}
public void setPapername(String papername) {
	this.papername = papername;
}
}
