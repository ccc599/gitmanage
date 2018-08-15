package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import entity.Question;
import entity.Testpaper;

public class Testpaper_Servlet extends BasicServlet{
	Testpaper entity;
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
		
		int[] pages= new int[maxp];
		
		for(int i=0;i<maxp;i++) {pages[i]=i;}
		
		req.setAttribute("pages",pages);
		req.setAttribute("max",max);
		req.setAttribute("count",count);
		req.setAttribute("he",he);
		where = "where t.testpapername like '%"+he+"%'";
		req.setAttribute("list", SqlHelper.executeQuery("select t.*,c.coursename  from testpaper t inner join course c on t.course_id = c.id  "+where+" LIMIT "+max+" OFFSET "+count*max,Testpaper.class));
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("insert into testpaper (testpapername,starttime,examtime,status,grade,testpapery,season,questions_id,course_id) values(?,?,?,?,?,?,?,?,?)",entity.getTestpapername(),entity.getStarttime(),entity.getExamtime(),entity.getStatus(),entity.getGrade(),entity.getTestpapery(),entity.getSeason(),entity.getQuestions_id(),entity.getCourse_id());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("update testpaper set testpapername=?,starttime=?,examtime=?,status=?,grade=?,testpapery=?, season=?,questions_id=?,course_id = ? where id=?", entity.getTestpapername(),entity.getStarttime(),entity.getExamtime(),entity.getStatus(),entity.getGrade(),entity.getTestpapery(),entity.getSeason(),entity.getQuestions_id(),entity.getCourse_id(),entity.getId());
		resp.getWriter().write(JsonUtil.jsoninfo(1, ""));
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("seanstatus",entity.seanstatus);
		req.setAttribute("course", SqlHelper.executeQuery("select * from course "));
		req.setAttribute("qtype", SqlHelper.executeQuery("select * from qtype "));
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("info", SqlHelper.executeQuery("select t.*,c.coursename from testpaper t inner join course c on t.course_id = c.id where t.id="+Integer.valueOf(entity.getId()),Testpaper.class ).get(0));
		add(req, resp);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 SqlHelper.executeUpdate("delete from testpaper where id = "+ entity.getId());
		 def(req, resp);
	}
	
	protected void deleteall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlHelper.executeUpdate("delete from testpaper where id in ("+req.getParameter("lists")+")" );
		 def(req, resp);
	}
	
	protected void question(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qtype = Integer.valueOf(req.getParameter("qtype"));
		int course = Integer.valueOf(req.getParameter("course_id"));
		req.setAttribute("list", SqlHelper.executeQuery("select * from question where qtype_id = "+qtype+" and course_id = "+course,Question.class));
		req.getRequestDispatcher("addquestion.jsp").forward(req, resp);
	}
	
	protected void value(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, Object> a =SqlHelper.executeQuery("select sum(grade) grade  from question where id in ("+req.getParameter("value")+")").get(0);
		resp.getWriter().write(JsonUtil.jsoninfo(1, a.get("grade").toString()));
	}
	
}
