package entity;

import java.util.Date;

public class Testpaper {
	public String []sstatus={"未发布","发布"};
	public String []seanstatus={"春","夏","秋","冬"};
	int id;
	String testpapername;
	Date starttime;
	int examtime;
	int status;
	int grade;
	int testpapery;
	int season;
	String questions_id;
	int course_id;
	String coursename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestpapername() {
		return testpapername;
	}
	public void setTestpapername(String testpapername) {
		this.testpapername = testpapername;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public int getExamtime() {
		return examtime;
	}
	public void setExamtime(int examtime) {
		this.examtime = examtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getTestpapery() {
		return testpapery;
	}
	public void setTestpapery(int testpapery) {
		this.testpapery = testpapery;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public String getQuestions_id() {
		return questions_id;
	}
	public void setQuestions_id(String questions_id) {
		this.questions_id = questions_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
	public String getSstatus() {
		return sstatus[status];
	}
	public void setSstatus(String[] sstatus) {
		this.sstatus = sstatus;
	}
	public String getSeanstatus() {
		return seanstatus[season];
	}
	public void setSeanstatus(String[] seanstatus) {
		this.seanstatus = seanstatus;
	}
	public Testpaper(int id, String testpapername, Date starttime, int examtime, int status, int grade, int testpapery,
			int season, String questions_id, int course_id, String coursename) {
		super();
		this.id = id;
		this.testpapername = testpapername;
		this.starttime = starttime;
		this.examtime = examtime;
		this.status = status;
		this.grade = grade;
		this.testpapery = testpapery;
		this.season = season;
		this.questions_id = questions_id;
		this.course_id = course_id;
		this.coursename = coursename;
	}
	public Testpaper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
