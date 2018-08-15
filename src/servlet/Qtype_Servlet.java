package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Course;
import entity.Qtype;

public class Qtype_Servlet extends BasicServlet{
	Qtype entity;
	int max;
	int count;
	String he;
	String where;
	
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("count")==null) {
			max = 3;
			count=0;
			he="";
		}
		
		if(req.getParameter("he")!=null && req.getParameter("he")!="") he = req.getParameter("he");
		if(req.getParameter("he")==null) he = "";
		
		List s2= SqlHelper.executeQuery("select * from Qtype ");
		int maxp = ((s2.size()+max)/max)-1;	
		if(count>maxp) {count = maxp;}	
		if(count < 0) {count=0;}
		
		int[] pages= new int[maxp+1];
		
		for(int i=0;i<maxp+1;i++) {pages[i]=i;}
		
		req.setAttribute("pages",pages);
		req.setAttribute("max",max);
		req.setAttribute("count",count);
		req.setAttribute("he",he);
		where = "where qtype like '%"+he+"%'";
		req.setAttribute("list", SqlHelper.executeQuery("select * from Qtype "+where+" LIMIT "+max+" OFFSET "+count*max,Qtype.class));
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("insert into Qtype (qtype) values(?)",entity.getQtype());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("update Qtype set qtype=? where id=?", entity.getQtype(),entity.getId());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("info", SqlHelper.executeQuery("select * from Qtype where id=?", Integer.valueOf(entity.getId())).get(0));
		add(req, resp);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 SqlHelper.executeUpdate("delete from Qtype where id = "+ entity.getId());
		 def(req, resp);
	}
	
	protected void deleteall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("delete from Qtype where id in ("+req.getParameter("lists")+")" );
		 def(req, resp);
	}
}
