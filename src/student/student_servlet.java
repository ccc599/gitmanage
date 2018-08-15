package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Student;
import servlet.BasicServlet;

public class student_servlet extends BasicServlet{
	Student entity;
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", SqlHelper.executeQuery("select * from student where id = ?",Student.class,Integer.valueOf((String) req.getSession().getAttribute("id")) ).get(0));
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}
	
	protected void passwordalter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		SqlHelper.executeUpdate("update student set password=? where id=?",req.getParameter("password"),Integer.valueOf(req.getParameter("id")));
		def(req, resp);
	}
	
}
