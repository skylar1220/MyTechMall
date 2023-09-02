package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Member;
import service.MemberService;
@WebServlet("/member/add")
public class AddController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/add.jsp").forward(request, response);	
	}
	
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberService = new MemberService();
		
		Member isMember =  memberService.getMember( request.getParameter("memberId") ) ;
		
		if(isMember == null ) {
			Member member = new Member();
			member.setMemberId( request.getParameter("memberId") );
			member.setPassword( request.getParameter("password"));
			member.setName( request.getParameter("name"));
			member.setEmail(request.getParameter("mail"));
			member.setAddress( request.getParameter("address"));
		
			
		memberService.addMemeber(member);
		} else {
			throw new NullPointerException("회원가입 실패: 이미 가입된 아이디");
		}
	}
}

