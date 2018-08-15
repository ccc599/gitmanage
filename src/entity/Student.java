package entity;

public class Student {
	int id;
	String studentid;
	String studentname;
	int sex;
	String password;
	String course_id;
	String coursename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Student(int id, String studentid, String studentname, int sex, String password, String course_id,
			String coursename) {
		super();
		this.id = id;
		this.studentid = studentid;
		this.studentname = studentname;
		this.sex = sex;
		this.password = password;
		this.course_id = course_id;
		this.coursename = coursename;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
