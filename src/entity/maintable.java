package entity;

import java.util.Date;

public class maintable {
public Integer id;
public Integer testpaper_id;
public Integer course_id;
public Integer teacher_id;
public Date publishdate;
public Date getPublishdate() {
	return publishdate;
}
public void setPublishdate(Date publishdate) {
	this.publishdate = publishdate;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getTestpaper_id() {
	return testpaper_id;
}
public void setTestpaper_id(Integer testpaper_id) {
	this.testpaper_id = testpaper_id;
}
public Integer getCourse_id() {
	return course_id;
}
public void setCourse_id(Integer course_id) {
	this.course_id = course_id;
}
public Integer getTeacher_id() {
	return teacher_id;
}
public void setTeacher_id(Integer teacher_id) {
	this.teacher_id = teacher_id;
}

}
