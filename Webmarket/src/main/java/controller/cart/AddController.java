package controller.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;
import entity.Product;
import service.CartService;
import service.ProductService;

@WebServlet("/cart/add")
public class AddController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productId = request.getParameter("id");
		HttpSession session = request.getSession();
		
		ProductService productService = new ProductService();
		Product p = productService.getProduct(productId);
		if( productId == null || productId.trim().equals("")) {
			response.sendRedirect("/product/list");
			return ;
		}
		if(p == null) {
			response.sendRedirect("/product/list");
			return ;
		}
		
		CartService cartService = new CartService() ;
		String memberId = (String) session.getAttribute("memberId");
		
//		productId로 getCartbyProduct 해봤는데 null이면(기존 수량이 없으면) insert하고( 그러면 productId가 unique여야 맞겠다)
		Cart c = cartService.getCartByProduct(productId, memberId);
		if(c == null) {
			Cart c1 = new Cart();
				c1.setMemberId(memberId) ;
				c1.setProductId(p.getProductId()) ;
				c1.setQuantity(1) ;
			cartService.insertCart(c1);
		} else {
//		null이 아니면 그 카트 quantity+1 한 애로 업데이트 updateCart
			cartService.update1Cart(c);
		}
		
		response.sendRedirect("/product/detail?id="+ productId);
		
	}
}
