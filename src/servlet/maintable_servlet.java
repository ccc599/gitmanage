package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.util.JSONPObject;

import dao.SqlHelper;
import dao.jsUntil;
import entity.Cla;
import entity.maintable;
import entity.stuclass;
import entity.flush;
public class maintable_servlet extends BasicServlet {

	
	maintable entity;
	@Override
	protected void def(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  ArrayList<HashMap<String, Object>> list=  SqlHelper.executeQuery
 ("select m.*,t.testpapername,o.coursename,e.teachername  from maintable m inner join testpaper t on m.testpaper_id=t.id inner join course o on o.id=m.course_id  inner join teacher e on m.teacher_id=e.id ");
	  
	  req.setAttribute("list", list);
	  req.getRequestDispatcher("index.jsp").forward(req, resp);
	  
	
	}
	
	protected void onchange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	ArrayList<HashMap<String, Object>> list=SqlHelper.executeQuery("select * from testpaper where course_id=? and status=0", entity.course_id);
	String jsons="";
	ArrayList<flush> flu=new ArrayList<>();
	 for(int i=0;i<list.size();i++)
	 {
		 flu.add(new flush((Integer) list.get(i).get("id"), (String) list.get(i).get("testpapername")));
		 if(i==list.size()-1) {
		jsons+= jsUntil.toString(new flush((Integer) list.get(i).get("id"), (String) list.get(i).get("testpapername")));
		 }
		 else
			 jsons+= jsUntil.toString(new flush((Integer) list.get(i).get("id"), (String) list.get(i).get("testpapername")))+",";
	 }
	 jsons="["+jsons+"]";
	 resp.setCharacterEncoding("utf-8");
	 PrintWriter out=resp.getWriter();
	 
	 out.print(jsons);
	  
	 
	 out.flush();
	 out.close();
		
		  
		
		}
	
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		ArrayList<HashMap<String, Object>> date=null;
		 String dat="";
        if(entity.id!=null) {
	      date=SqlHelper.executeQuery("select publishdate from maintable where id=?", entity.id);
	      SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  dat=  simpleDateFormat.format(date.get(0).get("publishdate"));
        }
		ArrayList<HashMap<String, Object>> teacherlist=null;
	    ArrayList<HashMap<String, Object>> courselist=null;
		ArrayList<HashMap<String, Object>> testpaperlist=null;
		ArrayList<HashMap<String, Object>> testpaperlists=null;
		if(entity.teacher_id!=null) {
		
			
		  teacherlist=  SqlHelper.executeQuery
				  ("select * from teacher where id=?",entity.teacher_id);
		  courselist=  SqlHelper.executeQuery
				  ("select * from course where id=?",entity.course_id);
		  testpaperlist=  SqlHelper.executeQuery
				  ("select t.* from testpaper t inner join course c on t.course_id=c.id where t.id=?",entity.testpaper_id);
		  testpaperlists=  SqlHelper.executeQuery
				  ("select t.* from testpaper t inner join course c on t.course_id=c.id where c.id=? and t.status=0",entity.course_id);
		  
		}
		if(entity.teacher_id==null) {
			
			   teacherlist= SqlHelper.executeQuery
					  ("select * from teacher where id=1");
			    courselist= SqlHelper.executeQuery
					  ("select * from course where id=1");
//			          testpaperlist=  SqlHelper.executeQuery
//					  ("select * from testpaper t inner join course c on t.course_id=c.id where t.id=1");
			   testpaperlists=  SqlHelper.executeQuery
					  ("select t.* from testpaper t inner join course c on t.course_id=c.id where c.id=1 and t.status=0  ");
			  
			}
		  
		  ArrayList<HashMap<String, Object>> teacherlists=  SqlHelper.executeQuery
				  ("select * from teacher ");
		  
		
		  ArrayList<HashMap<String, Object>> courselists=  SqlHelper.executeQuery
				  ("select * from course ");
		  
		  
		  
		  if(teacherlist!=null&&courselist!=null&&testpaperlist!=null) {
		 req.setAttribute("teacherlist", teacherlist.get(0));
		 req.setAttribute("courselist", courselist.get(0));
		 req.setAttribute("testpaperlist", testpaperlist.get(0));
		 
		  }
		
			
			
		 req.setAttribute("id", req.getParameter("id"));
		 req.setAttribute("publishdate", dat);
		 req.setAttribute("teacherlists", teacherlists);
		 req.setAttribute("courselists", courselists);
		 req.setAttribute("testpaperlists", testpaperlists);
		 
		 req.getRequestDispatcher("edit.jsp").forward(req, resp);
		  
		
		}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException
	{
	     
       	 SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date dat= simpleDateFormat.parse(req.getParameter("publishdate"));
         if(entity.testpaper_id!=null) {
         SqlHelper.executeUpdate("update testpaper set status=1 where id=?",entity.testpaper_id );
         
		 SqlHelper.executeUpdate("insert into maintable (teacher_id,course_id,testpaper_id,publishdate) values(?,?,?,?)",entity.teacher_id,entity.course_id,entity.testpaper_id,dat);
		 resp.getWriter().write(new jsUntil().returnvalues(1, ""));
         }
         else
         {
        	 resp.getWriter().write(new jsUntil().returnvalues(-1, ""));
        	 
         }
        	 
		 
	}
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException
	{
                
          SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date dat= simpleDateFormat.parse(req.getParameter("publishdate"));
           
          if(entity.testpaper_id!=null) {
		  SqlHelper.executeUpdate("update maintable set testpaper_id=?  where id=?",entity.testpaper_id,entity.id);
		  SqlHelper.executeUpdate("update maintable set course_id=? where id=?",entity.course_id,entity.id );
		  SqlHelper.executeUpdate("update maintable set teacher_id=? where id=?",entity.teacher_id,entity.id );	 
		  SqlHelper.executeUpdate("update maintable set publishdate=? where id=?",dat,entity.id);
		  SqlHelper.executeUpdate("update testpaper set status=0 where id=?", req.getParameter("oldtestpaperid"));
		  SqlHelper.executeUpdate("update testpaper set status=1 where id=?",entity.testpaper_id );   
		  resp.getWriter().write(new jsUntil().returnvalues(2, ""));
          }
          else
          {
        	  resp.getWriter().write(new jsUntil().returnvalues(-2, ""));
          }
	}
	
	protected void delect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		  SqlHelper.executeUpdate("delete from maintable where id=?", entity.id );
		 def(req,resp);
	}
}
