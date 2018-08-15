package entity;

public class Course {
	int id;
	String coursename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Course(int id, String coursename) {
		super();
		this.id = id;
		this.coursename = coursename;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

}
