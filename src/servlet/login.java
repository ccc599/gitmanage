package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SqlHelper;
import dao.jsUntil;

public class login extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		
		HttpServletRequest  req= (HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		
		if(req.getParameter("username")!=null) {		
		if(req.getParameter("username").indexOf("T")==0)
			checkinTeacher(req,resp);
		else if (req.getParameter("username").indexOf("S")==0) {
			checkinStudent(req,resp);
		}
		
		
		}
	}

	private void checkinTeacher(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		 String name=req.getParameter("username");
		 String password=req.getParameter("password");
		 
		 req.setCharacterEncoding("utf-8");
		
		 ArrayList<HashMap<String, Object>> list= SqlHelper.executeQuery("select * from teacher where teacherid=? and password=?", name,password);
		 if(list.size()>0&&req.getSession().getAttribute("randomCode").equals(req.getParameter("code")))
		 {
			 try {
				 //req.getSession().setMaxInactiveInterval(5000);
				
				 req.getSession().setAttribute("id",String.valueOf(list.get(0).get("id")) );
				 req.getSession().setAttribute("authoraty", list.get(0).get("authoraty") );
				 resp.setCharacterEncoding("utf-8");
				 resp.getWriter().write(jsUntil.returnvalues(1,(String) list.get(0).get("teachername")));
				
			   
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else
		 {
			 try {
				resp.getWriter().write(jsUntil.returnvalues(-1, ""));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}

	private void checkinStudent(HttpServletRequest req, HttpServletResponse resp) {
		
	 String name=req.getParameter("username");
	 String password=req.getParameter("password");
	 
	ArrayList<HashMap<String, Object>> list= SqlHelper.executeQuery("select * from student where studentid=? and password=?", name,password);
	 if(list.size()>0&&req.getSession().getAttribute("randomCode").equals(req.getParameter("code")))
	 {
		 try {
			
			 req.getSession().setAttribute("id",String.valueOf(list.get(0).get("id")) );
			 resp.setCharacterEncoding("utf-8");
			 resp.getWriter().write(jsUntil.returnvalues(3, (String)list.get(0).get("studentname") ));
			
		   
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 else
	 {
		 try {
			resp.getWriter().write(jsUntil.returnvalues(-1, ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	}

}
