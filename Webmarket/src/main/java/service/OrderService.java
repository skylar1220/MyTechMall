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
import entity.OrderDetail;
import entity.OrderView;
import entity.Product;
import entity.ProductCartView;
import entity.Shipping;

public class OrderService {
	public int insertShipping(Shipping shipping) {
		int result = 0 ;
		
		String sql = " insert into shipping "
				+ " (shippingId, memberId, sum, name, phone, postcode, address, memo, orderDate, status)"
				+ " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? ,?) "
				;
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString( 1, shipping.getShippingId() );
			pstmt.setString( 2, shipping.getMemberId()  );
			pstmt.setInt( 3,  shipping.getSum() );
			pstmt.setString( 4,  shipping.getName() );
			pstmt.setString( 5,  shipping.getPhone() );
			pstmt.setString( 6 , shipping.getPostcode()  );
			pstmt.setString( 7 , shipping.getAddress()  );
			pstmt.setString( 8 , shipping.getMemo()  );
			pstmt.setString(9, shipping.getOrderDate() );
			pstmt.setString(10, shipping.getStatus() );
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return result = 0 ;
	}
	
	public int insertOrderDetail(OrderDetail orderDetail) {
		int result = 0 ; 
		
		String sql = " insert into orderDetail "
				+ " (orderDetailId, shippingId, productId, quantity )"
				+ " values ( ? , ? , ? , ?  ) "
				;
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString( 1,  orderDetail.getOrderDetailId() );
			pstmt.setString( 2,  orderDetail.getShippingId()  );
			pstmt.setString( 3,  orderDetail.getProductId()  );
			pstmt.setInt( 4,   orderDetail.getQuantity() );

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return result = 0 ;
		
	}
	
	public Shipping getShipping(String shippingId) {
		Shipping s = null;
		
		try {
			Connection con = ConnectionDb.getConnection();
			
			String sql = "select * from shipping where shippingId = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString(1, shippingId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				s = new Shipping(rs.getString("shippingId"), rs.getString("memberId"), rs.getInt("sum"), rs.getString("name"),
						rs.getString("phone"), rs.getString("postcode"), rs.getString("address"), rs.getString("memo"), rs.getString("orderDate"), rs.getString("status"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			return s;
	}
	
	public List<OrderView> getOrderViewList (String shippingId){
		List<OrderView> orderViewList = new ArrayList<OrderView>();
		String sql = " select * from order_view where "
				+ " shippingId = ?  ";
	try {
		Connection con = ConnectionDb.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shippingId);
		ResultSet rs = pstmt.executeQuery();
	
		while (rs.next()) {
			OrderView ov = new OrderView(rs.getString("orderDetailId"), rs.getString("productId"), rs.getInt("quantity"), 
					rs.getString("pname"), rs.getInt("unitPrice"), rs.getString("shippingId"), 
					rs.getString("memberId"),  rs.getInt("sum"), rs.getString("name"), rs.getString("phone"), 
					rs.getString("postcode"), rs.getString("address"), rs.getString("memo"), rs.getString("orderDate"), "prepare");
			orderViewList.add(ov);
		}
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
	return orderViewList;
	}
	
	
	public List<Shipping> getshippingList (){
		return getshippingList(1);
	}
	
	public List<Shipping> getshippingList (int page){
		List<Shipping> shippingList = new ArrayList<Shipping>();
		String sql = " select * from shipping "
				+ " limit ?, 5" ;
	try {
		Connection con = ConnectionDb.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, (page-1)*5 );
		ResultSet rs = pstmt.executeQuery();
	
		while (rs.next()) {
			Shipping sp = new  Shipping(rs.getString("shippingId"), rs.getString("memberId"), rs.getInt("sum"), rs.getString("name"), 
					rs.getString("phone"), rs.getString("postcode"), rs.getString("address"), rs.getString("memo"), rs.getString("orderDate"), rs.getString("status"));
			shippingList.add(sp);
		}
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
	return shippingList;
	}

	public int getOrderCount() {
		int count = 0 ;
		
		String sql = " select COUNT(shippingId) count "
				+ "  from shipping "
				;
		
		try {
		Connection con = ConnectionDb.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			count =  rs.getInt("count");

	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return count;
	}

	public void updateStatus(String shippingId, String status) {

		String sql = "update shipping set  "
				+ " status =  ?    "
				+ " where shippingId = ?  ";
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql);  
			pstmt.setString(1, status);
			pstmt.setString(2 , shippingId );
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
	}
	
	
}
