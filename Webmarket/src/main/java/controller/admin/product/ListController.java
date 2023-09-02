package controller.admin.product;

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
import service.ProductService;

@WebServlet("/admin/product/list")
public class ListController extends HttpServlet {
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String[] OpenIds = request.getParameterValues("open-id");	// p3,p5
		String[] delIds = request.getParameterValues("del-id");	// 'p1', 'p2', 'p3' ..
		String cmd = request.getParameter("cmd");
		String ids_ = request.getParameter("ids");
		String [] ids = ids_.trim().split(" ");		// p1, p2, p3, p4, p5
		
		ProductService productService = new ProductService();
		
		switch (cmd) {
		case "선택공개": 
			for(String openId : OpenIds)
				System.out.printf("---");
			
			List<String> oids = Arrays.asList(OpenIds);
			// p1,2,3,4,5   -   p3, 5
			// 최종 비공개 처리할 애 : p1, 2, 4
			List<String> cids =  new ArrayList(Arrays.asList(ids));  // ArrayList에 담아써야하는 이유: asList 반환하는 List 객체는 정적 -> 여기에 뭔가를 삭제, 추가 불가해서
			cids.removeAll(oids);
			
//			System.out.println(Arrays.asList(ids));
//			System.out.println(oids);
//			System.out.println(cids);
			
			// Transaction 처리 : 업무단위를 하나로 처리하는 것
			productService.pubProductAll(oids, cids);	// update product set pub=1 where id in (..)
//			productService.closeProductList(clsIds);	  // 이렇게 서비스 함수가 2개이면 좋지않음, 하나만 실행될수도 있음 			  
			
			break;
		case "선택삭제": 
			String result = productService.removeProductAll(delIds);
			break;
		}
		// 삭제 로직 끝난 다음에 목록 페이지로 이동되게
		response.sendRedirect("list");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// list?f=pname&q=갤럭시
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		// null 일수도있기때문에 string 
		String category_ = request.getParameter("category");
		
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
		List<ProductView> listOfProducts = productsService.getProductList(category, field, query, page);
		
		int count = productsService.getProuductCount(category, field, query);
		request.setAttribute("count", count);
		request.setAttribute("plist", listOfProducts);
		
		
		// foward
		request.getRequestDispatcher("/WEB-INF/view/admin/product/list.jsp").forward(request, response);	
			
		
	}
}












