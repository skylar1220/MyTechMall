package controller.order;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.OrderDetail;
import entity.ProductCartView;
import entity.Shipping;
import service.CartService;
import service.OrderService;

@WebServlet("/order/shippingInfo")
public class ShippingInfoController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId =  (String) session.getAttribute("memberId");
		
		String shippingCartIds = request.getParameter("shippingCartIds"); // ,7,8,9
		shippingCartIds =  shippingCartIds.substring(1); //7,8,9
		request.setAttribute("shippingCartIds", shippingCartIds);
		
		CartService cartService = new CartService() ;
		List<ProductCartView> shippingCartViewList =  cartService.getShippingCartList(shippingCartIds);
		request.setAttribute("shippingCartViewList", shippingCartViewList);
		
		LocalDateTime localDate = LocalDateTime.now();
		Date date = Timestamp.valueOf(localDate);
		
		// 나중에 id를 위한 date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateForId = simpleDateFormat.format(date);
		
		request.setAttribute("date", date);
		request.setAttribute("dateForId", dateForId);
		
		request.getRequestDispatcher("/WEB-INF/view/order/shippingInfo.jsp").forward(request, response);	
		

			
			
	}
}
