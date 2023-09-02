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
import entity.Member;
import entity.Product;
import service.CommentService;
import service.MemberService;
import service.ProductService;

@WebServlet("/product/detail")
public class DetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("id");	
		
		ProductService productsService = new ProductService();
		Product product = productsService.getProduct(productId);
		request.setAttribute("p", product);
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		request.setAttribute("memberId", memberId);
		if(memberId== null) request.setAttribute("message", "로그인 후 이용해주세요.");
		
		
		CommentService commentService = new CommentService();
		List<Comment> commentList = commentService.getCommentList(productId);
		request.setAttribute("commentList", commentList);
		
		int commentCnt = commentService.getCommentCount(productId);
		request.setAttribute("commentCnt", commentCnt);

		
		// foward
		request.getRequestDispatcher("/WEB-INF/view/product/detail.jsp").forward(request, response);
		// request.getRequestDispatcher("/WEB-INF/view/admin/product/edit.jsp").forward(request, response);
		// 얘를 호출해서 req,rep를 공유할 것이다

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");	 			
		String memberId = request.getParameter("writerId");
		String insertChecked = request.getParameter("insertChecked"); 		
		String editContent = request.getParameter("editContent");
		int c_number ;
			if(request.getParameter("c_number") != null) c_number = Integer.parseInt(request.getParameter("c_number"));
			else  c_number = 0 ; 
		String type = request.getParameter("type");
		CommentService commentService = new CommentService();
			
		// 후기 등록
		if(type.equals("insert")) {
			Comment com = commentService.getComment(productId, memberId);
			// 등록한 후기 있는지 확인
			// 확인 전이면
			if (insertChecked == null ){
				// 조회했을 때, 후기가 없으면
				if(com==null)  response.sendRedirect("detail?id="+productId);
				// 조회했을 때, 후기가 있으면
				else  throw new NullPointerException("이미 작성한 후기 있음");
			} 
			// 확인 후이면
			else {
				Comment comment = new Comment();
				comment.setC_content(request.getParameter("c_content"));
				comment.setWriterId(request.getParameter("writerId"));
				comment.setProductId(productId);
			commentService.insertComment(comment);
			response.sendRedirect("detail?id="+productId);
		} 
			
		} 
		// 후기 수정
		else if (type.equals("edit")) {
			commentService.updateComment(editContent, c_number);
			response.sendRedirect("detail?id="+productId);
		} 
		// 후기 삭제 
		else if (type.equals("delete")) {
			commentService.deleteComment(c_number);
			response.sendRedirect("detail?id="+productId);
		}
	}
}
