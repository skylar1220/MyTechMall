package controller.admin.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.P2roductRepository;
import entity.Product;
import entity.ProductView;
import entity.Shipping;
import service.OrderService;
import service.ProductService;

@WebServlet("/admin/order/list")
public class ListController extends HttpServlet {
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		OrderService orderService = new OrderService();

		String page_ = request.getParameter("p");
		int  page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_) ;
		
		int count = orderService.getOrderCount();
		request.setAttribute("count", count);
		
		List<Shipping> shippingList =  orderService.getshippingList(page);
		request.setAttribute("shippingList", shippingList);
		
		request.getRequestDispatcher("/WEB-INF/view/admin/order/list.jsp").forward(request, response);	
	}
	 
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		OrderService orderService = new OrderService();
		String shippingId = request.getParameter("shippingId");
		String status = request.getParameter("status");
		
		orderService.updateStatus(shippingId, status);
		
		String pageURL  = "/admin/order/list?p="+request.getParameter("page");
		response.sendRedirect(pageURL);
	}
}












