package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Cla;
import entity.Teacherclass;

public class Teacher_servlet extends BasicServlet {

	Teacherclass entity;
	
	
	protected void changeteacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

		ArrayList<HashMap<String, Object>> list=SqlHelper.executeQuery("select * from teacher where id=?", new BigInteger( (String) req.getSession().getAttribute("id")) );	
		req.setAttribute("list", list.get(0));
		req.getRequestDispatcher("Teacheredit.jsp").forward(req, resp);
		
	}

	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		  SqlHelper.executeUpdate("update teacher set teacherid=?  where id=?",entity.teacherid,entity.id );
		  SqlHelper.executeUpdate("update teacher set teachername=? where id=?",entity.teachername,entity.id );
		  SqlHelper.executeUpdate("update teacher set sex=? where id=?",entity.sex,entity.id );
		  SqlHelper.executeUpdate("update teacher set password=? where id=?",entity.password,entity.id );
		  resp.sendRedirect("Teacherindex.jsp");
	}
	
	
	
}
