package servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import dao.jsUntil;
import entity.stuclass;

public class Myservlet extends GenericServlet {
	
  public stuclass stu;

	
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		  HttpServletRequest req=(HttpServletRequest) arg0;
		  HttpServletResponse rop=(HttpServletResponse) arg1;
		  
		  req.setCharacterEncoding("utf-8");
	
		  Enumeration<String> name=  req.getParameterNames();
		
		  Object o=this;
		  try {
				Field f=getClass().getDeclaredField("stu");
				o=f.getType().newInstance();
				
				f.setAccessible(true);
				f.set(this, o);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		  
		 
		  while(name.hasMoreElements())
		  {
			  try {
			  String names=name.nextElement();
			  Field s= o.getClass().getField(names);
			  setvalue(o,names,s,req);
		  }
			 
		  catch (Exception e) {
				
				//e.printStackTrace();
			} 
		  }
		

		//根据 cmd参数值找函数  
		  String cmd=req.getParameter("cmd");
		  if(cmd==null||cmd.equals(""))
		  {
			  defaut(req,rop);
		  }
		  else
		  {
			try {	
				
        Method fie=  this.getClass().getDeclaredMethod(cmd,HttpServletRequest.class, HttpServletResponse.class);
        fie.setAccessible(true);			   
        fie.invoke(this, req,rop);
			
			} 
		 catch (Exception e) {
			// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
		  }
		
		  
	}
	
	
	//给stuclass里面设置属性
	public void setvalue(Object o, String names, Field s, HttpServletRequest req) {
		
		try {
			   if( s.getType().equals(int.class)||s.getType().equals(Integer.class))
			   {
				   s.set(o, Integer.valueOf(req.getParameter(names)));
			   }
			  
			   else if( s.getType().equals(double.class)||s.getType().equals(Double.class))
			   {
				   s.set(o, Double.valueOf(req.getParameter(names)));
			   }
			   else
			   {
				   s.set(o,req.getParameter(names));
			   }
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	
	
	
	
	public void defaut(HttpServletRequest req,  HttpServletResponse rop) throws ServletException, IOException 
	{
		
		Integer fan=0;
		if(req.getParameter("pagepr")!=null)
		{	
			fan=Integer.valueOf(req.getParameter("pagepr"));
			
		    fan=fan-1;
		    System.out.println(fan);
		}
		else if(req.getParameter("pageaf")!=null)
		{	
			fan=Integer.valueOf(req.getParameter("pageaf"));
		  fan=fan+1;
		  System.out.println(fan);
		}
		else
		{
			fan=0;
		}
		
	    ArrayList<HashMap<String, Object>> list=SqlHelper.executeQuery
	 ("select s.id, s.name,s.old,s.sex,c.name schoolname from Student s left join school c on s.classid=c.id ");
		
	      int countpage=0;
	     if(list.size()%5==0)
	     {
	    	 countpage=list.size()/5;
	     }
	     else
	     {
	    	 countpage=list.size()/5+1;
	     }
	
	     if(fan<=0) {
	    	 fan=0;
	     }
	     if(fan>=countpage-1) {
	    	 fan=countpage-1;
	     }
	     ArrayList<HashMap<String, Object>> lists=SqlHelper.executeQuery
	    ("select s.id, s.name,s.old,s.sex,c.name schoolname from Student s left join school c on s.classid=c.id limit "+fan*5+", 5");
	
	     req.setAttribute("fan", fan);
	     req.setAttribute("list", lists); 
	     req.getRequestDispatcher("index.jsp").forward(req, rop);
	
	}
	

	
   //添加
	public void add(HttpServletRequest req,  HttpServletResponse rop) throws ServletException, IOException {
		  
		  ArrayList<HashMap<String, Object>> list=SqlHelper.executeQuery
	    		  ("select * from school" );
		  req.setAttribute("list", list);
	      req.getRequestDispatcher("edit.jsp").forward(req, rop);
	     
	}
	
	
	//添加新数据
	public void addnew(HttpServletRequest req,  HttpServletResponse rop) throws ServletException, IOException {
		 SqlHelper.executeUpdate("insert into student (name,old,sex,classid) values(?,?,?,?)",stu.name,stu.old,stu.sex,stu.classid);
		 defaut(req,rop);
	}
	
	
	//删除
	public void delect(HttpServletRequest req, HttpServletResponse rop) throws ServletException, IOException {
		
		  SqlHelper.executeUpdate("delete from student where id=?", stu.id );
		  defaut(req,rop);
	}
	
	
	//更新
	public void update(HttpServletRequest req, HttpServletResponse rop) throws ServletException, IOException {
		
		  SqlHelper.executeUpdate("update student set name=?  where id=?",stu.name,stu.id );
		  SqlHelper.executeUpdate("update student set old=? where id=?",stu.old,stu.id );
		  SqlHelper.executeUpdate("update student set sex=? where id=?",stu.sex,stu.id );
		  SqlHelper.executeUpdate("update student set classid=? where id=?",stu.classid,stu.id );
		  rop.getWriter().write(new jsUntil().returnvalues(1, ""));
		  //defaut(req,rop);
	}
	
	
	//插入
	public void edit(HttpServletRequest req, HttpServletResponse rop) throws ServletException, IOException {
  ArrayList<HashMap<String, Object>> info=SqlHelper.executeQuery
 ("select s.id, s.name,s.old,s.sex,s.classid,c.name schoolname from Student s left join school c on s.classid=c.id where s.id=? ",Integer.valueOf(req.getParameter("id")) );	 
     
      ArrayList<HashMap<String, Object>> list=SqlHelper.executeQuery
    		  ("select * from school" );
      req.setAttribute("info", info.get(0));
      req.setAttribute("list", list);
      req.getRequestDispatcher("edit.jsp").forward(req, rop);
	  
	}
}
