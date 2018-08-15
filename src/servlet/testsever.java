package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;

public class testsever extends GenericServlet
{

	public void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/tests/aaa.jsp").forward(req, resp);
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		HttpServletRequest req= (HttpServletRequest) arg0;
	    HttpServletResponse resp= (HttpServletResponse) arg1;
	   ArrayList<HashMap<String, Object>> list=   SqlHelper.executeQuery("select * from student");
	     
	    req.setAttribute("list", list);
		def(req,resp);
	}

	

	

}
