package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Member;
import entity.WelcomeNotice;
import service.MemberService;


@WebServlet("/welcome")
public class WelcomeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String greeting = "웹 쇼핑몰에 오신 것을 환영합니다";
		String tagline = "Welcome to Web Market!";
		LocalDateTime localDate = LocalDateTime.now();
		Date date = Timestamp.valueOf(localDate);
		
		WelcomeNotice welcomeNotice = new WelcomeNotice(
				greeting, tagline, date
				);
				
		request.setAttribute("w", welcomeNotice);
		
		// foward
		request.getRequestDispatcher("/WEB-INF/view/welcome.jsp").forward(request, response);	
		// welcome을  호출해서 req,rep를 공유할 것이다
		
	}
	
}
