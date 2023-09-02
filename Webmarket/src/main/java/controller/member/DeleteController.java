package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;

@WebServlet("/member/delete")
public class DeleteController extends  HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session = request.getSession();
	String memberId = (String) session.getAttribute("memberId");
	
	MemberService memberService = new MemberService();
	memberService.deleteMember(memberId);
	session.invalidate();
	
	response.sendRedirect("/welcome");
	//request.getRequestDispatcher("/WEB-INF/view/member/delete.jsp").forward(request, response);	
	}
}
