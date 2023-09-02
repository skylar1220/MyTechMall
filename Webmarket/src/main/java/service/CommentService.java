package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionDb;
import entity.Cart;
import entity.Comment;

public class CommentService {
	public void insertComment(Comment comment) {
		
		String sql = " insert into comment "
				+ " (  productId , writerId , c_content ) "
				+ " values ( ? , ? , ? ) "
				;
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString( 1, comment.getProductId() );
			pstmt.setString( 2, comment.getWriterId() );
			pstmt.setString( 3,  comment.getC_content() );
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
	
	
	public List<Comment> getCommentList (String productId){
		return getCommentList(productId, 1);
	}
	
	public List<Comment> getCommentList (String productId,  int page){
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = " select * from  comment "
				+ " where productId = ? "
				+ " limit ?, 10" ;
	try {
		Connection con = ConnectionDb.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString( 1, productId );
		pstmt.setInt(2, (page-1)*10 );
		ResultSet rs = pstmt.executeQuery();
	
		while (rs.next()) {
			Comment c = new Comment(rs.getInt("c_number"), rs.getString("productId"), rs.getString("writerId"), rs.getString("c_content"), rs.getTimestamp("c_regdate")) ;
			commentList.add(c);
		}
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return commentList;
	}

	public int getCommentCount(String productId) {
		int count = 0 ;
		
		String sql = " select COUNT(c_number) count "
				+ "  from  comment "
				+ " where productId =  ?  "
				;
		
		try {
		Connection con = ConnectionDb.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
		pstmt.setString( 1, productId );
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			count =  rs.getInt("count");

	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getStackTrace());
	}
		return count;
	}

	
	public void updateComment(String editContent, int c_number) {

		String sql = "update comment  "
				+ " set c_content =  ?    "
				+ " where c_number = ?  ";
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql);  
			pstmt.setString(1, editContent);
			pstmt.setInt(2 , c_number );
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
	}



	public Comment getComment(String productId, String memberId) {
		Comment comment = null;
		try {
			Connection con = ConnectionDb.getConnection();
			
			String sql = "select * from comment where productId = ? and writerId = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql); // 쿼리 실행
			pstmt.setString(1, productId);
			pstmt.setString(2, memberId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				comment = new Comment(rs.getInt("c_number"), rs.getString("productId"), rs.getString("writerId")
						, rs.getString("c_content"), rs.getTimestamp("c_regdate")) ;
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			return comment;
	}



	public void deleteComment(int c_number) {
		String sql = "delete from comment "
				+ " where c_number = ? " ;
		
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_number);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
}
