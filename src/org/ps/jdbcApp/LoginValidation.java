package org.ps.jdbcApp;

import java.sql.*;
import java.util.Scanner;
public class LoginValidation {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String Query="select username from pentagon.emp where name=? and password=?";
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the name");
		String name=sc.next();
		System.out.println("Please enter password");
		String pwd=sc.next();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver class is loaded and registered");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
			System.out.println("Connection is established with DB server");
			pstmt=con.prepareStatement(Query);
			
			//Set the data for placeholder
			pstmt.setString(1,name);
			pstmt.setString(2,pwd);
			
			//Execute the query
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String username=rs.getString(1);
				System.out.println("Hello "+username+" you are successfully logged in");
			}
			else {
				System.out.println("Invalid name/password");
			}
					
		} catch (ClassNotFoundException | SQLException e) {		
			e.printStackTrace();
		}	
		finally {
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null)
			{
				try {
				 con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("All costly resources are closed ");
		}
	  
	}
}
