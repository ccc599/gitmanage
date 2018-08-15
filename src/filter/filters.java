package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class filters implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain file)
			throws IOException, ServletException {
		HttpServletRequest  rep=(HttpServletRequest) arg0;
		HttpServletResponse rop= (HttpServletResponse) arg1;
		
		if(rep.getRequestURI().indexOf("login.jsp")>=0||rep.getRequestURI().indexOf("LoginJava")>=0||rep.getRequestURI().indexOf("img")>=0) {
			  file.doFilter(rep, rop);
		}
		else if(rep.getSession().getAttribute("user")!=null) {
			  file.doFilter(rep, rop);
		}
		else
		{
			rop.sendRedirect("login.jsp");
		}
               
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
