package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Database() {}
	
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {               
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_db?useSSL=false", "root", "root");
	        } catch (SQLException e) {
	        	throw new DatabaseException(e.getMessage());
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    return conn;
	}
		
	public static void closeConnection() {
		if (conn != null) {
	    	try {
	        	conn.close();
	        } catch(SQLException e) {
	        	throw new DatabaseException(e.getMessage());
			}
		}
	}
		
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
	        	st.close();
			} catch (SQLException e) {
	        	throw new DatabaseException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
	        	throw new DatabaseException(e.getMessage());
			}
		}
	}
}
