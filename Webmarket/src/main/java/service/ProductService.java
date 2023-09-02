package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.ConnectionDb;
import entity.Product;
import entity.ProductView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductService {
	public String removeProductAll(String[] delIds) {
		String result = "" ;
		
		String params = "";
		for (int i = 0; i < delIds.length; i++) {
			params += delIds[i] ;
			
			if(i < delIds.length-1 )
				params += "','"; 		//  1", "2", "3
		}
		
		String sql = "delete from product where productId in ( '"+ params +"' )";		//  "1", "2", "3"
				;

		
		try {
		Connection con = ConnectionDb.getConnection();
		
		Statement stmt = con.createStatement(); // 쿼리 실행
		int rs = stmt.executeUpdate(sql);

	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return result ;
	}
	
	public int pubProductAll(int [] oids, int [] cids ) {	// 들어올 수 있는 자료형을 자유롭게 해주는 작업: 오버로드의 매력
		List<String> oidsList =  new ArrayList<>();
		for (int i = 0; i < oids.length; i++) 
			oidsList.add(String.valueOf(oids[i]));
	    
		List<String> cidsList =  new ArrayList<>();
		for (int i = 0; i < cids.length; i++) 
			cidsList.add(String.valueOf(cids[i]));
		return pubProductAll(oidsList, cidsList) ;  // 위 작업을 통해 collection으로 바꿔서 줌
	}
	
	public int pubProductAll(List<String> oids, List<String> cids ) {	
		String oidsCSV = "'" + String.join("' , '" , oids) + "'"  ;  	// 구분자로 구분해서 문자의 가변데이터를 더해줘서 문자를 만들어줌
		String cidsCSV = "'" +  String.join("' , '"  , cids) + "'";	
		return pubProductAll(oidsCSV, cidsCSV) ;	// 위 작업을 통해 collection으로 바꿔서 줌
	}
	
	public int pubProductAll(String oidsCSV, String cidsCSV ) {	 // 20, 33, 45 (comma) -> collection으로 받음 -> '20','30',45' 바꿔야
		int result = 0 ;
		String sqlOpen = String.format("update product set pub = 1 where productId in (%s)" , oidsCSV) ;
		String sqlClose  = String.format("update product set pub = 0 where productId in (%s)" , cidsCSV) ;
		
		try {
		Connection con = ConnectionDb.getConnection();
		Statement stmtOpen = con.createStatement();  
		result += stmtOpen.executeUpdate(sqlOpen);
		
		Statement stmtClose = con.createStatement();  
		result += stmtClose.executeUpdate(sqlClose);
		
		stmtOpen.close();
		stmtClose.close();
		con.close();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return result ;
	}
		
	
	public int insertProduct(Product product) {
		int result = 0 ;
		String fileNotNullSql = "", fileNotNullValues ="";
		if(!product.getFilename().isEmpty()) {
			 fileNotNullSql = ", filename " ;
			 fileNotNullValues = ", ? " ;
		}
		
		
		String sql = "insert into product "
				+ " (productId, pname, unitPrice, descriptions, "
				+ " manufacturer, category, unitsInStock , pub"+ fileNotNullSql + ") "
				+ " values( ?, ?, ? ,? , ? , ? ,? ,  ? " + fileNotNullValues+ ")";
				;
		
		try {
		Connection con = ConnectionDb.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
		pstmt.setString(1, product.getProductId());
		pstmt.setString(2 , product.getPname() );
		pstmt.setInt(3 , product.getUnitPrice() );
		pstmt.setString(4 , product.getDescription() );
		pstmt.setString(5 , product.getManufacturer() );
		pstmt.setString(6 , product.getCategory() );
		pstmt.setLong(7 , product.getUnitsInStock() );
		pstmt.setBoolean(8, product.getPub());
		if(!product.getFilename().isEmpty()) pstmt.setString(9 , product.getFilename() );
		
		result = pstmt.executeUpdate();

	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return result ;
	}

	public int updateProduct(String ogProductId,  Product product) {	// 수정되면 수정된 개수인 1을 반환, 아니면 0 반환 
		int result = 0 ; 

		String sql_img = "";
		if(!product.getFilename().equals(""))
			sql_img = " filename =  '" +  product.getFilename() +"', "; 
		
		
		String sql = "update product set  "
				+ " productId =  ?  ,  "
				+ " pname = ?  ,  "
				+ " unitPrice = ? , "
				+ " descriptions = ? ,"
				+ " manufacturer = ? , "
				+ " category = ? , "
				+ " unitsInStock = ? , "
				+ sql_img
				+ " pub = ? "
				+ " where productId = ?  ";
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString(1, product.getProductId());
			pstmt.setString(2 , product.getPname() );
			pstmt.setInt(3 , product.getUnitPrice() );
			pstmt.setString(4 , product.getDescription() );
			pstmt.setString(5 , product.getManufacturer() );
			pstmt.setString(6 , product.getCategory() );
			pstmt.setLong(7 , product.getUnitsInStock() );
			pstmt.setBoolean(8, product.getPub());
			pstmt.setString(9, ogProductId );
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
			return result ;
		}
	

	public String deleteProduct(String delIds) {
		return "" ;
	}
	
	
	public List<Product> getProductNewestList(){
		return null;
	}
	
	
	
	// 이름이 같은 유사한 코드들은 하나만 구현하고, 나머지는 그걸 호출하는 방식으로 해야 간편. 가장 파라미터가 많은 거 구현.
	public List<ProductView> getProductList(){
		return getProductList("", "pname", "", 1);
	}
	
	public List<ProductView> getProductList(int page){
		return getProductList("", "pname", "", page);
	}
	
	public List<ProductView> getProductList(String category, String field/*pname, descriptions*/, String query/*갤럭시*/, int page){
		List<ProductView> listOfProducts = new ArrayList<>();
		

		String sql = " select * "
				+ "  from (select * from product_view where "+ field + " like ? "
				+ "  and   category   like ?  ) N  "
				+ "	 order by unitPrice "
				+ "	 limit ?, 6 "	 // 0, 6, 12 번째부터 6개 -> ? = (page-1)*6
				;

		
		try {
		Connection con = ConnectionDb.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
		
		pstmt.setString(1, "%" + query + "%");
		pstmt.setString(2, "%" + category + "%" );
		pstmt.setInt(3, (page-1)*6 );
		
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			ProductView p = setProductVoView(rs);
			listOfProducts.add(p);
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		
		return listOfProducts;
	}
	
	

	public List<ProductView> getProductPubList(String category, String field, String query, int page) {
			List<ProductView> listOfProducts = new ArrayList<>();
			

			String sql = " select * "
					+ "  from (select * from product_view where "+ field + " like ?  "
					+ "  and  category like ?  ) N  "
					+ "  where pub = 1 "
					+ "	 order by unitPrice "
					+ "	 limit ?, 6 "
					;

			
			try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			
			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + category + "%" );
			pstmt.setInt(3, (page-1)*6 );
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductView p = setProductVoView(rs);
				listOfProducts.add(p);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
			
			return listOfProducts;
	}

	
	
	
	public int getPubProuductCount() {
		return getProuductCount("", "pname", "");
	}
	
	public int getPubProuductCount(String category, String field, String query) {
		int count = 0 ;
		
		String sql = " select COUNT(productId) count "
				+ "  from (select * from product where "+ field + " like ? "
				+ "  and   category like ?  ) N  "
				+ "  where pub = 1 "
				+ "	 order by unitPrice desc "
				;
		
		try {
		Connection con = ConnectionDb.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
		
		pstmt.setString(1, "%" + query + "%");
		pstmt.setString(2, "%" + category + "%" );
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			count =  rs.getInt("count");

	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return count;
	}
	
	
	
	
	
	public int getProuductCount() {
		return getProuductCount("", "pname", "");
	}
	
	public int getProuductCount(String category, String field, String query) {
		int count = 0 ;
		
		String sql = " select COUNT(productId) count "
				+ "  from (select * from product where "+ field + " like ? "
				+ "  and   category like ?  ) N  "
				+ "	 order by unitPrice desc "
				;
		
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			
			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + category + "%" );
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				count =  rs.getInt("count");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return count;
	}
	
	
	public Product getProduct(String productId) {
		Product p = null;
		
		try {
		Connection con = ConnectionDb.getConnection();
		
		String sql = "select * from product where productId = ? ";
		PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
		pstmt.setString(1, productId);
		
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			p = setProductVo(rs);
		}
		rs.close();
		pstmt.close();
		con.close();
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		return p;
	}
	
	
	
	/* 여기는 나중에 필요하면 구현 */
	public Product getNextProduct(String id) {
		return null;
	}
	
	public Product getPrivousProduct(String id) {
		return null;
	}
	/* 여기는 나중에 필요하면 구현 */
	
	
	private Product setProductVo(ResultSet rs) throws SQLException {
		Product p = new Product(rs.getString("productId"), rs.getString("pname"), rs.getInt("unitPrice"),
				rs.getString("descriptions"), rs.getString("manufacturer"), rs.getString("category"),
				rs.getLong("unitsInStock"), rs.getString("filename") , rs.getBoolean("pub"));
		return p;
	}
	
	private ProductView setProductVoView(ResultSet rs) throws SQLException {
		ProductView p = new ProductView(rs.getString("productId"), rs.getString("pname"), rs.getInt("unitPrice"),
				rs.getString("descriptions"), rs.getString("manufacturer"), rs.getString("category"),
				rs.getLong("unitsInStock"), rs.getString("filename"), rs.getBoolean("pub") , rs.getInt("cmtCount"));
		return p;
	}

	
	

	
	
//	private void setPstmt(PreparedStatement pstmt, Product product) throws SQLException {
//		pstmt.setString(1, product.getProductId());
//		pstmt.setString(2, product.getPname());
//		pstmt.setInt(3, product.getUnitPrice());
//		pstmt.setString(4, product.getDescription());
//		pstmt.setString(5, product.getManufacturer());
//		pstmt.setString(6, product.getCategory());
//		pstmt.setLong(7, product.getUnitsInStock());
//		pstmt.setString(9, product.getFilename());
//	}
	
}
