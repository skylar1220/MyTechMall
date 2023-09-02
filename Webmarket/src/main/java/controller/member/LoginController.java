package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Member;
import net.sf.json.JSONObject;
import service.CartService;
import service.MemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		CartService cartService = new CartService();
		// JSONObject obj = new JSONObject();
		PrintWriter writer = response.getWriter();
		writer.print("admin");
		
//		MemberService memberService = new MemberService();
//		Member member = memberService.getMember(memberId);
		
//		System.out.println("#member.getMemberId():"+member.getMemberId());
//		System.out.println(password);
//		// memberId로 조회했을 때, 멤버가 있으면
//		if(member!=null) {
//			if (member.getMemberId().equals("ADMIN")){
//				if(password.equals("admin1234")) {
//					writer.print("admin");
//				}
//			} else if( member.getPassword().equals(password)  ) {
//				request.setAttribute("m", member);
//				session.setAttribute("memberId", member.getMemberId());
//				session.setAttribute("cartCount", cartService.getCartCount(memberId));
//				response.sendRedirect("/welcome");
//			} else {
//				throw new NullPointerException("로그인 실패: 없는 유저");
//			}
//		}
//		// memberId로 조회했을 때, 멤버가 없으면
//		else {
//			throw new NullPointerException("로그인 실패: 없는 유저");
//		}
		
	}
}
