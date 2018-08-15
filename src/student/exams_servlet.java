package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Question;
import entity.Student;
import entity.Testpaper;
import servlet.BasicServlet;

public class exams_servlet extends BasicServlet{
	Testpaper entity;
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Student student =(Student) SqlHelper.executeQuery("select * from student where id = 1",Student.class).get(0);
		req.setAttribute("list", SqlHelper.executeQuery("SELECT t.id mainid, ts.id, ts.testpapername,c.coursename,ts.starttime,ts.examtime,ts.grade,t.teachername from maintable m INNER JOIN teacher t on m.teacher_id= t.id INNER JOIN testpaper ts on m.testpaper_id = ts.id INNER JOIN course c on m.course_id = c.id where c.id = "+student.getCourse_id()));
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void enterexam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, Object> a = SqlHelper.executeQuery("SELECT questions_id from testpaper where id = "+req.getParameter("id")).get(0);
		req.setAttribute("list", SqlHelper.executeQuery("SELECT * from question where id in (" + a.get("questions_id").toString()+")",Question.class));
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
}
