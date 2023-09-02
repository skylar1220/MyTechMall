package filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LogFilter implements Filter  {
	
	public void  init(FilterConfig config) throws ServletException{
		System.out.println("WebMarket...."); 
	}
	
	public void  doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws java.io.IOException, ServletException {
		System.out.println(" IP : " + request.getRemoteAddr());
		long start = System.currentTimeMillis();
        System.out.println("URL: " + getURLPath(request));
		System.out.println(" 현재시간: " + getCurrentTime());
		chain.doFilter(request,response);
		
		long end = System.currentTimeMillis();		
		System.out.println(" 현재시간 : " + getCurrentTime());
		System.out.println(" 처리시간 : " + (end-start)+ "ms ");
		System.out.println("=======================================================");
	}

	public void destroy( ){
      
	}

	private String getURLPath(ServletRequest request) {
		HttpServletRequest req;
		String currentPath="";
		String queryString=""; 
		if(request instanceof HttpServletRequest){
			req = (HttpServletRequest)request;
			currentPath = req.getRequestURI();
			queryString = req.getQueryString();
			queryString = queryString == null ? "" : "?" + queryString;
		}
		return currentPath+queryString;
	}
	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
