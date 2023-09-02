package controller.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;
import entity.Product;
import entity.ProductCartView;
import service.CartService;
import service.ProductService;

@WebServlet("/cart/list")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
			
		CartService cartService = new CartService() ;
		List<ProductCartView> cartlist =  cartService.getMemberCartList(memberId);
		request.setAttribute("cartlist", cartlist);
		request.setAttribute("memberId", memberId);
		if(memberId== null) request.setAttribute("message", "로그인 후 이용해주세요.");
		
		request.getRequestDispatcher("/WEB-INF/view/cart/list.jsp").forward(request, response);	

		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ?
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
			
		CartService cartService = new CartService() ;
		List<ProductCartView> cartlist =  cartService.getMemberCartList(memberId);
		request.setAttribute("cartlist", cartlist);
		
		request.getRequestDispatcher("/WEB-INF/view/cart/list.jsp").forward(request, response);	

	}
}
