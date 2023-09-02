package filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class LogFileFilter implements Filter {

	PrintWriter writer;

	public void init(FilterConfig config) throws ServletException {
		String filename = config.getInitParameter("filename");

		if (filename == null)
			throw new ServletException("파일이 없습니다..");

		try {
			writer = new PrintWriter(new FileWriter(filename, true), true);
		} catch (IOException e) {
			throw new ServletException("초기화 에러.");
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
		writer.println("  IP : " + request.getRemoteAddr());
		long start = System.currentTimeMillis();
		writer.println("URL: " + getURLPath(request));
		writer.println("현재시간" + getCurrentTime());
		
		chain.doFilter(request, response);

		long end = System.currentTimeMillis();
		writer.println(" 현재시간: " + getCurrentTime());
		writer.println(" 시차 : " + (end - start) + "ms ");
		writer.println("=======================================================");
	}

	public void destroy() {
		writer.close();
	}

	private String getURLPath(ServletRequest request) {
		HttpServletRequest req;
		String currentPath = "";
		String queryString = "";
		if (request instanceof HttpServletRequest) {
			req = (HttpServletRequest) request;
			currentPath = req.getRequestURI();
			queryString = req.getQueryString();
			queryString = queryString == null ? "" : "?" + queryString;
		}
		return currentPath + queryString;
	}

	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
