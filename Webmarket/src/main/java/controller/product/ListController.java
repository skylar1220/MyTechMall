package controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.P2roductRepository;
import entity.Product;
import entity.ProductView;
import service.ProductService;

@WebServlet("/product/list")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// list?f=pname&q=갤럭시
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		// null 일수도있기때문에 string 
		String category_ = request.getParameter("c");
		
		String field = "pname";
		if(field_ != null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;
		
		int  page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_) ;

		String category = "";
		if(category_ != null && !category_.equals(""))
			category = category_;
		
		ProductService productsService = new ProductService();
		List<ProductView> listOfProducts = productsService.getProductPubList(category, field, query, page);
		
		int count = productsService.getPubProuductCount(category, field, query);
		request.setAttribute("count", count);
		
		request.setAttribute("plist", listOfProducts);
		
		// foward
		request.getRequestDispatcher("/WEB-INF/view/product/list.jsp").forward(request, response);	
			
		
	}
}
