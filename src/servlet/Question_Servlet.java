package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Question;

public class Question_Servlet extends BasicServlet{
	Question entity;
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
		
		List s2= SqlHelper.executeQuery("select * from question ");
		int maxp = ((s2.size()+max)/max)-1;	
		if(count>maxp) {count = maxp;}	
		if(count < 0) {count=0;}
		
		int[] pages= new int[maxp+1];
		
		for(int i=0;i<maxp+1;i++) {pages[i]=i;}
		
		req.setAttribute("pages",pages);
		req.setAttribute("max",max);
		req.setAttribute("count",count);
		req.setAttribute("he",he);
		where = "where q.keyword like '%"+he+"%'";
		req.setAttribute("list", SqlHelper.executeQuery("select q.*,qt.qtype,c.coursename from question q inner join qtype qt on q.qtype_id = qt.id inner join course c on q.course_id = c.id  "+where+" LIMIT "+max+" OFFSET "+count*max,Question.class));
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("insert into question (keyword,qtype_id,awareness,title,difficult,grade,course_id,answer) values(?,?,?,?,?,?,?,?)",entity.getKeyword(),entity.getQtype_id(),entity.getAwareness(),entity.getTitle(),entity.getDifficult(),entity.getGrade(),entity.getCourse_id(),entity.getAnswer());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("update question set keyword=?,qtype_id=?,awareness=?,title=?,difficult=?,grade=?, course_id=?,answer=? where id=?", entity.getKeyword(),entity.getQtype_id(),entity.getAwareness(),entity.getTitle(),entity.getDifficult(),entity.getGrade(),entity.getCourse_id(),entity.getAnswer(),entity.getId());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		req.setAttribute("awnstatus",entity.awnstatus);
		req.setAttribute("dfstatus",entity.dfstatus);
		req.setAttribute("course", SqlHelper.executeQuery("select * from course "));
		req.setAttribute("qtypes", SqlHelper.executeQuery("select * from qtype "));
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("info", SqlHelper.executeQuery("select q.*,qt.qtype,c.coursename from question q inner join qtype qt on q.qtype_id = qt.id inner join course c on q.course_id = c.id where q.id=?", Integer.valueOf(entity.getId())).get(0));
		add(req, resp);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 SqlHelper.executeUpdate("delete from question where id = "+ entity.getId());
		 def(req, resp);
	}
	
	protected void deleteall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("delete from question where id in ("+req.getParameter("lists")+")" );
		 def(req, resp);
	}
}
