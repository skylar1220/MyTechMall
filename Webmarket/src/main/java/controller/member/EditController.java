package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Member;
import service.MemberService;

@WebServlet("/member/edit")
public class EditController extends  HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session = request.getSession();
	String memberId = (String) session.getAttribute("memberId");
	
	MemberService memberService = new MemberService() ; 
	Member member = memberService.getMember(memberId);
	
	request.setAttribute("m", member);
	
	request.getRequestDispatcher("/WEB-INF/view/member/edit.jsp").forward(request, response);	

}

@Override
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
	Member member = new Member();
		member.setMemberId( request.getParameter("memberId") );
		member.setPassword( request.getParameter("password"));
		member.setName( request.getParameter("name"));
		member.setEmail(request.getParameter("mail1")+"@"+request.getParameter("mail2") );
		member.setAddress( request.getParameter("address"));

	MemberService memberService = new MemberService();
	memberService.updateMember(member);

response.sendRedirect("welcome?m=edit");
	}
}
