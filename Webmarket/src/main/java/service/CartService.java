package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionDb;
import entity.Cart;
import entity.Product;
import entity.ProductCartView;

public class CartService {
	
	public int insertCart (Cart cart) {
		int result = 0 ;
		
		String sql = "insert into cart "
				+ " (memberId, productId, quantity )  "
				+ " values( ?, ?, ? )";
				;
		
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString(1, cart.getMemberId() );
			pstmt.setString(2, cart.getProductId()  );
			pstmt.setInt(3, cart.getQuantity());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
			return result ;
	}

	public List<ProductCartView> getMemberCartList(String memberId) {
		List<ProductCartView> cartlist = new ArrayList<ProductCartView>();
		
		String sql = " select * from product_cart "
				+ " where memberId = ? ";
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductCartView p = new ProductCartView(rs.getInt("cartId"),rs.getString("memberId"),rs.getString("productId"), rs.getString("pname")
						, rs.getInt("unitPrice"), rs.getInt("quantity"));
				cartlist.add(p);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return cartlist;
	}
	
	public Cart getCartById(int cartId) {
		Cart c = null;
		
		try {
			Connection con = ConnectionDb.getConnection();
			
			String sql = "select * from cart where cartId = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setInt(1, cartId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				c = new Cart(rs.getInt("cartId"),rs.getString("memberId"), rs.getString("productId"),  
						rs.getInt("quantity"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			return c;
		
	}
	
	public Cart getCartByProduct(String productId, String memberId) {
		Cart c = null;
		
		try {
			Connection con = ConnectionDb.getConnection();
			
			String sql = "select * from cart where productId = ? and memberId = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString(1, productId);
			pstmt.setString(2, memberId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				c = new Cart(rs.getInt("cartId"),rs.getString("memberId"), rs.getString("productId"),  
						rs.getInt("quantity"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			return c;
	}
	
	public List<ProductCartView> getShippingCartList(String cartIds) {
		List<ProductCartView> shippingCartViewList = new ArrayList<ProductCartView>();
		if(cartIds.startsWith(","))
			cartIds =  cartIds.substring(1);

		String sql = " select * from product_cart where "
					+ " cartId in ( "+ cartIds +" )  ";
		try {
			Connection con = ConnectionDb.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ProductCartView c = new ProductCartView(rs.getInt("cartId"), rs.getString("memberId"), rs.getString("productId"), rs.getString("pname"), rs.getInt("unitPrice"), rs.getInt("quantity"));
				shippingCartViewList.add(c);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		return shippingCartViewList;
	}
	
	public int update1Cart(Cart c) {
		int result = 0 ;
		String sql =  "update cart set  "
				+ " quantity = ? "
				+ " where cartId = ?  ";
		
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c.getQuantity()+1);
			pstmt.setInt(2, c.getCartId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return result ;
	}

	public int deleteCart(int cartId) {
		int result = 0 ;
		
		String sql = "delete from cart "
				+ " where cartId = ? " ;
		
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return result ;
	}
	
	public int resetCart(String cartIds) {
		int result = 0 ;
		
		String sql = "delete from cart "
				+ " where cartId  in ( "+ cartIds +" )  ";
		
		try {
			Connection con = ConnectionDb.getConnection();
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		return result ;
				
	}
	
	public Integer getCartCount(String memberId) {
		Integer count = 0 ;
		
		String sql = " select COUNT(cartId) count from cart "
					+ " where memberId = ? "
				;
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, memberId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				count =  rs.getInt("count");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return count;
	}
	
}
