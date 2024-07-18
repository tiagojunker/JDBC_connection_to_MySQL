package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null; // Connection to DataBase
		Statement st = null;	// Object to handle with SQL commands
		ResultSet rs = null;	// Object to receive the result of Query made by Statement
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM department");  // SQL command (SELECT)
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// Closing connections
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeCOnnection();
		}	
		
		
	}

}
