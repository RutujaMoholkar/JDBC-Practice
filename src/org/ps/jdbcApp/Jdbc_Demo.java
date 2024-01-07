package org.ps.jdbcApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Jdbc_Demo {

	public static void main(String[] args)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		String iQuery="insert into pentagon.student values(?,?,?)";
	try {
        Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver class is loaded and registered");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
		System.out.println("Connction is established with DB server");
		 pstmt = con.prepareStatement( iQuery);
		 System.out.println("Platform is created");
		 //SET THE DATA FOR PLACEHOLDER BEFORE THE EXECUTION
		 int count=0;
		 pstmt.setInt(1, 429);
		 pstmt.setString(2,"Rutuja");
		 pstmt.setDouble(3, 94.80);
		count=count+pstmt.executeUpdate();
		
		pstmt.setInt(1, 430);
		 pstmt.setString(2,"Ritesh");
		 pstmt.setDouble(3, 90.22);
		count=count+pstmt.executeUpdate();
		
		pstmt.setInt(1, 431);
		 pstmt.setString(2,"Ram");
		 pstmt.setDouble(3, 90.00);
		count=count+pstmt.executeUpdate();
		
		pstmt.setInt(1, 432);
		 pstmt.setString(2,"Rashid");
		 pstmt.setDouble(3, 72.22);
		count=count+pstmt.executeUpdate();
		System.out.println(count+" records are inserted into database");
		 
	} catch (ClassNotFoundException | SQLException e) 
	{	
		e.printStackTrace();
	}
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			
			
		}
	}
	
	
	
	
	
	
	
	
	}

}
