package controller.order;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;
import entity.OrderDetail;
import entity.OrderView;
import entity.ProductCartView;
import entity.Shipping;
import service.CartService;
import service.OrderService;

@WebServlet("/order/confirm")
// @@@이전페이지에서 order,shipping 정보 받은걸로 여기서 db 처리하기부터@
public class ConfirmController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId =  (String) session.getAttribute("memberId") ;
		
		OrderService orderService = new OrderService();
		
		// id용으로 2023-07-01 23:59:59 -> 20230701235959 로 변환.. 하려고 했으나 여기서 하려니 throw도 안돼고 try catch 하려니 아래 다 해야해서 처음 date를 쓰는
		String dateForId = request.getParameter("dateForId");
		 String subNum = "";
		 for(int i = 1; i <= 4; i ++) {
			  subNum += (int)(Math.random() * 10);
			 }
		String shippingId = dateForId + subNum;
		
		
		Shipping shipping = new Shipping() ; 
			shipping.setShippingId( shippingId );  ;
			shipping.setMemberId(memberId);
			shipping.setSum(Integer.parseInt(request.getParameter("sum")));  ;
			shipping.setName(request.getParameter("name"));  ;
			shipping.setPhone(request.getParameter("phone"));  ;
			shipping.setPostcode(request.getParameter("postcode"));  ;
			shipping.setAddress(request.getParameter("address"));
			shipping.setMemo(request.getParameter("memo"));  ;
			shipping.setOrderDate(request.getParameter("orderDate"));  
			shipping.setStatus("prepare");
			;
		
		orderService.insertShipping(shipping);
		
		
		CartService cartService = new CartService();
		String strCartIds =  request.getParameter("shippingCartIds"); 	//  7,8,13,14,15
		// shippingCartIds 7,8,9 를 ,를 기준으로 나눠서 list나 배열에 담고
		String[] CIds = strCartIds.split(","); // [7,8,13,14,15]
		
		// cartId 하나당 c로 그걸로 for문을 돌리면서 orderDetail 객체에 추가하기 
		for (String strCartId : CIds) {
			int cartId = Integer.parseInt(strCartId);
			Cart c =  cartService.getCartById(cartId);
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderDetailId(shippingId + strCartId);
			// 여기 i에 1,2,3 순서대로 들어가게 만들기 또는 cartId
			orderDetail.setShippingId(shippingId);
			orderDetail.setProductId( c.getProductId() );
			orderDetail.setQuantity( c.getQuantity());
			
		orderService.insertOrderDetail(orderDetail);
		}
		
		Shipping viewShipping =  orderService.getShipping(shippingId);
		request.setAttribute("s", viewShipping);
		

		List<OrderView> orderViewList = orderService.getOrderViewList(shippingId);
		request.setAttribute("orderViewList", orderViewList);
		
		cartService.resetCart(strCartIds);  
		
		request.getRequestDispatcher("/WEB-INF/view/order/confirm.jsp").forward(request, response);	
	}
	
}


