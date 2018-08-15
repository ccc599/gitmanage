package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Cla;

public class test_servlet extends BasicServlet{

	Cla entity;	
	@Override
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", SqlHelper.executeQuery("select * from cla",Cla.class));
	    req.getRequestDispatcher("/tests/index.jsp").forward(req, resp);
	   
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("insert into cla (name,status,clatypeid) values(?,?,?)",entity.getName(),entity.getStatus(),entity.getClatypeid());
		def(req, resp);
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("update cla set name=?,status=?,clatypeid=? where id=?", entity.getName(),entity.getStatus(),entity.getClatypeid(),entity.getId());
		def(req, resp);
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("clastatus",  Cla.clastatus);
		req.setAttribute("typerow", SqlHelper.executeQuery("select * from clatype "));
		req.getRequestDispatcher("/edit.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("info", SqlHelper.executeQuery("select * from cla where id=?", Integer.valueOf(entity.getId())).get(0));
		add(req, resp);
		
	}
}
