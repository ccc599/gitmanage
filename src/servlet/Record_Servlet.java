package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Record;;

public class Record_Servlet extends BasicServlet{
	Record entity;
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
		
		List s2= SqlHelper.executeQuery("select * from record");
		int maxp = ((s2.size()+max)/max)-1;	
		if(count>maxp) {count = maxp;}	
		if(count < 0) {count=0;}
		
		int[] pages= new int[maxp+1];
		
		for(int i=0;i<maxp+1;i++) {pages[i]=i;}
		
		req.setAttribute("pages",pages);
		req.setAttribute("max",max);
		req.setAttribute("count",count);
		req.setAttribute("he",he);
		where = "where s.studentid like '%"+he+"%'";
		req.setAttribute("list", SqlHelper.executeQuery("select r.*,s.studentid  from Record r inner join student s on r.student_id = s.id "+where+" LIMIT "+max+" OFFSET "+count*max,Record.class));
	
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("insert into record (maintable_id,student_id,record,grade) values(?,?,?,?)",entity.getMaintable_id(),entity.getStudent_id(),entity.getRecord(),entity.getGrade());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("update record set grade=? where id=?", entity.getGrade(),entity.getId());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("info", SqlHelper.executeQuery("select r.*,s.studentid  from Record r inner join student s on r.student_id = s.id where r.id=?", Integer.valueOf(entity.getId())).get(0));
		add(req, resp);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 SqlHelper.executeUpdate("delete from record where id = "+ entity.getId());
		 def(req, resp);
	}
	
	protected void deleteall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("delete from record where id in ("+req.getParameter("lists")+")" );
		 def(req, resp);
	}
}
