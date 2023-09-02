package dao;


import java.util.ArrayList;
import java.util.List;

import entity.Product;
import entity.ProductView;
import service.ProductService;

public class P2roductRepository {

	public List<ProductView> getAllProducts() {
		ProductService productsService  = new ProductService();
		List<ProductView> listOfProducts = productsService.getProductList();
		return listOfProducts;
	}
	


	public Product getProductById(String productId) {
		ProductService productsService  = new ProductService();
		Product p = productsService.getProduct(productId);
		return p;
	}

//	public void addProduct(Product product) {
//		try {
//			Connection con = ConnectionDb.getConnection();
//			String sql = "insert into product " + "	values(" + "	?,?,?,?,?,?,?,?,?" + "	);";
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			setPstmt(pstmt, product);
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//
////	@@@ 여기부터 구현
//	public ArrayList<Product> getProductByCategory(String category) {
//		ArrayList<Product> listOfCategoryProducts = new ArrayList<Product>();
//		Product p = null;
//		try {
//			Connection con = ConnectionDb.getConnection();
//			String sql = "select * from product where category = ? " ;
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, category);
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				p = setProductVo(rs);
//				listOfCategoryProducts.add(p);
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return listOfCategoryProducts;
//
//	}
}
