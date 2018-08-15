package entity;

public class Record {
	int id;
	int maintable_id;
	int student_id;
	String record;
	int grade;
	String studentid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaintable_id() {
		return maintable_id;
	}
	public void setMaintable_id(int maintable_id) {
		this.maintable_id = maintable_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public Record(int id, int maintable_id, int student_id, String record, int grade, String studentid) {
		super();
		this.id = id;
		this.maintable_id = maintable_id;
		this.student_id = student_id;
		this.record = record;
		this.grade = grade;
		this.studentid = studentid;
	}
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
