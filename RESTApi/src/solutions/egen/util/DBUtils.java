package solutions.egen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	private static final String db_url1 ="jdbc:mysql://localhost:3306/emp_db";
	private static final String db_url2 ="jdbc:mysql://localhost:3306/res_db";
	private static final String db_usr ="root";
	private static final String db_pwd="root";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver has been Loaded");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in Loading MySQL JDBC Driver");
		}
	}
	
	public static Connection connect(){
		Connection conn = null;
		try {
			conn=DriverManager.getConnection(db_url1, db_usr, db_pwd);
			System.out.println("MySQL Connection is succesful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MySQL Connection is NOT succesful");
		}
		return conn;
	}
	
	public static Connection resConnect(){
		Connection conn = null;
		try {
			conn=DriverManager.getConnection(db_url2, db_usr, db_pwd);
			System.out.println("MySQL Connection is succesful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MySQL Connection is NOT succesful");
		}
		return conn;
	}
	
	public static void closeResource (PreparedStatement ps, ResultSet rs, Connection conn) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
			
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		DBUtils.resConnect();
	}
	
}
