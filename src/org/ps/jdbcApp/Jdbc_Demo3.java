//Code to fetch particular record from cursor or buffer memory.

package org.ps.jdbcApp;

import java.sql.*;
public class Jdbc_Demo3 {

	public static void main(String[] args) 
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String Query="select * from  pentagon.student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver class is loaded and registered");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
			System.out.println("Connection is established with DB server");
			pstmt=con.prepareStatement(Query);
			System.out.println("Platform is created");
			 rs=pstmt.executeQuery();
			boolean val= rs.absolute(1);
			 if(val) {
				 int id=rs.getInt(1);
				 String name=rs.getString(2);
				 Double percentage= rs.getDouble(3);		
				 System.out.println(id+"  "+name+"  "+percentage);
				 System.out.println("Particular student details are printed");
			 }
			 else {
				 System.out.println("No such record is present in database");
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
