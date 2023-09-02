package controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.P2roductRepository;
import entity.Comment;
import entity.Product;
import service.CommentService;
import service.ProductService;

@WebServlet("/product/deleteComment")
public class DeleteCommentController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int c_number = Integer.parseInt(request.getParameter("c_number"));	
		
		
		
		CommentService commentService = new CommentService();
		commentService.deleteComment(c_number);
		

		
		// foward
		request.getRequestDispatcher("/WEB-INF/view/product/detail.jsp").forward(request, response);
		// request.getRequestDispatcher("/WEB-INF/view/admin/product/edit.jsp").forward(request, response);
		// 얘를 호출해서 req,rep를 공유할 것이다

	}
	
}
