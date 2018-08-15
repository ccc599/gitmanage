package entity;

public class Question {
	public String []awnstatus={"初级","中级","高级"};
	public String []dfstatus={"简单","中等","困难"};
	int id;
	String keyword;
	int qtype_id;
	int awareness;
	String title;
	int difficult;
	int grade;
	int course_id;
	String answer;
	String qtype;
	String coursename;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getQtype_id() {
		return qtype_id;
	}
	public void setQtype_id(int qtype_id) {
		this.qtype_id = qtype_id;
	}
	public int getAwareness() {
		return awareness;
	}
	public void setAwareness(int awareness) {
		this.awareness = awareness;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDifficult() {
		return difficult;
	}
	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAwnstatus() {
		return awnstatus[this.awareness];
	}
	public void setAwnstatus(String[] awnstatus) {
		this.awnstatus = awnstatus;
	}
	public String getDfstatus() {
		return dfstatus[this.difficult];
	}
	public void setDfstatus(String[] dfstatus) {
		this.dfstatus = dfstatus;
	}
	
}
