package controller.admin.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import entity.Product;
import service.ProductService;

// post가 필요하려나?: o -> 상품 등록 처리해야하니까
// get은 그냥 처음 등록창 보여주는 거고

@MultipartConfig(								 
		// location = "/Users/hyeonjilee/upload",	 
		fileSizeThreshold = 1024*1024,	 
		maxFileSize = 1024*1024*50 ,	 
		maxRequestSize = 1024*1024*50*5	 
		)
@WebServlet("/admin/product/add")
public class AddController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/admin/product/add.jsp").forward(request, response);	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 경로 알아내는 방법
		
		Collection <Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		
		for (Part p : parts) {
			if(!p.getName().equals("productImage")) continue;	 // 파일 아니면 for 빠져나가기, file이면 진행,
			if(p.getSize() == 0 ) continue;	// 파일 2개를 받는 상황에서도: 1개가 안들어와도 빠져나가기
		
			Part filePart =  p;  // 파라미터는 전달된 애의 문자열을 얻는거라면, part는 전달한 name 값을 가지고 특정파트를 받는 것 
			String fileName =  filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");		// 사용자가 첨부한 파일명을 ,,이어서 갖게됨
			
			InputStream fis = filePart.getInputStream();
			
			String realPath = request.getServletContext().getRealPath("/resources/images");
			// System.out.println("realPath"+realPath);
			File path = new File(realPath);		// upload라고 하는 디렉토리가 실제 물리적으로 얻어졌을 때 그게 있느냐 
			if( !path.exists())					// 없으면, directory를 만든다. 
				path.mkdirs();
			
			String filePath = realPath +  File.separator + fileName ;
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[]  buf = new byte[1024] ;	// 1byte 씩 int 값으로하면 너무 오래걸림:  1024크기의 바가지에 
			int size = 0 ;				
			while (( size = fis.read(buf)) != -1 ) // 담긴 데이터 사이즈를 반환 : 데이터를 계속 읽다가 더이상 읽을 값이 없을 때는:  -1 반환 
				fos.write(buf, 0, size); // 마지막 전에는 계속 buf에 담겨있는게 1024지만 0번째부터 size의 개수(1024)만큼 반복해서 저장하게 됨? 계속 1024 반환하는 일 없어짐
			
			fos.close();
			fis.close();
		}
		builder.delete(builder.length()-1, builder.length()); // 마지막 "," 빼기
		
		
		String productId = request.getParameter("productId");	 
		String pname= request.getParameter("pname");	 	 
		Integer unitPrice= Integer.parseInt(request.getParameter("unitPrice"));	 
		String description= request.getParameter("description");	 
		String manufacturer= request.getParameter("manufacturer");	 
		String category= request.getParameter("category");	 	 
		long unitsInStock= Long.parseLong(request.getParameter("unitPrice"))   ;
		String filename= request.getParameter("productImage");	 
		String isOpen= request.getParameter("open");	// true or null
		Boolean pub = false;
		if(isOpen != null)
			pub = true;
		
		Product product = new Product();
			product.setCategory(category);
			product.setDescription(description);
			product.setManufacturer(manufacturer);
			product.setPname(pname);
			product.setProductId(productId);
			product.setUnitPrice(unitPrice);
			product.setUnitsInStock(unitsInStock);
			product.setPub(pub);
			product.setFilename(builder.toString());
			
			// System.out.println(builder.toString());
		
		ProductService productService = new ProductService();
		productService.insertProduct(product);
		
		response.sendRedirect("list");
	}
	

}
