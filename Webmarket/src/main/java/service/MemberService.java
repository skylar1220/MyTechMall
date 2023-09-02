package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.ConnectionDb;
import entity.Member;

public class MemberService {
	public Member getMember(String memberId) {
		Member m = null ; 
		String sql = "select * from member where memberId = ? ";
		try { 
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m = new Member(rs.getString("memberId"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("address"));
			}
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m ; 
	}
	
	public int updateMember (Member member ) {
		int result = 0 ;
		
		String sql = "update member set  "
				+ " password =  ?  ,  "
				+ " name = ?  ,  "
				+ " email = ? , "
				+ " address = ?  "
				+ " where memberId = ?  ";
		
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword() );
			pstmt.setString(2, member.getName() );
			pstmt.setString(3, member.getEmail() );
			pstmt.setString(4, member.getAddress()  );
			pstmt.setString(5, member.getMemberId()  );
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
			return result ;
	}
	
	
	
	private Member setMemberVo(ResultSet rs) throws SQLException {
		Member m = new Member(rs.getString("memberId"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("address"));
		return m;
	}
	
	public int addMemeber(Member member) {
		int result = 0 ;
		String sql = "insert into member "
				+ " (memberId , password, name,  email, address) "
				+ " values( ?, ?, ? ,? , ? )";
		
		try {
			Connection con = ConnectionDb.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return result ;
	}
	
	public int deleteMember(String memberId) {
		int result = 0 ;
		
		String sql = "delete from member where memberId = ? " ;
		
		try {
			Connection con = ConnectionDb.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		return result ;
	}

}
