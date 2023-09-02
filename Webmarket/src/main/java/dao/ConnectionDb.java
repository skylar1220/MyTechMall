package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/marketdb";
		String user = "marketdb_admin";
		String psw = "admin1234";
		// mysql driver load
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =   DriverManager.getConnection(url, user, psw);		
		return conn;
	}

}
