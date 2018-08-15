package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Teacherclass;

public class Teachermanage_servlet extends BasicServlet{

	Teacherclass entity;
	
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 ArrayList<HashMap<String, Object>> list=  SqlHelper.executeQuery("select * from teacher");
		 
		  req.setAttribute("list", list);
		  req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	protected void add (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
   
		 SqlHelper.executeUpdate("insert into teacher (teacherid,teachername,sex,authoraty,password) values(?,?,?,?,?)",entity.teacherid,entity.teachername,entity.sex,entity.authoraty,entity.password);
		 def(req,resp);
	}
	protected void delect (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 SqlHelper.executeUpdate("delete from teacher where id=?", entity.id );
		 req.getRequestDispatcher("index.jsp").forward(req, resp);
		 def(req,resp);
	}

	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		  SqlHelper.executeUpdate("update teacher set teacherid=?  where id=?",entity.teacherid,entity.id );
		  SqlHelper.executeUpdate("update teacher set teachername=? where id=?",entity.teachername,entity.id );
		  SqlHelper.executeUpdate("update teacher set sex=? where id=?",entity.sex,entity.id );
		  SqlHelper.executeUpdate("update teacher set password=? where id=?",entity.password,entity.id );
		  def(req,resp);
		  
	}
protected void edit (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

	ArrayList<HashMap<String, Object>> list=SqlHelper.executeQuery("select * from teacher where id=? ",entity.id);	
		req.setAttribute("list", list.get(0));
	req.getRequestDispatcher("Teachermanageedit.jsp").forward(req, resp);
		
	}
}
