package org.ps.jdbcApp;

import java.sql.*;
import java.util.Scanner;
public class JdbcTransaction {

	public static void main(String[] args) 	{
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		
		String sql1="insert into pentagon.StudentCollege values(?,?,?)";
		String sql2="insert into pentagon.StudentPersonal values(?,?,?)";
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the student id:");
		int id=sc.nextInt();
		System.out.println("Enter the student name:");
		String name=sc.next();
		System.out.println("Enter the department name:");
		String dept=sc.next();
		System.out.println("Enter the student place :");
		String place=sc.next();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
			
			//Disable auto commit mode
			con.setAutoCommit(false);
			pstmt1=con.prepareStatement(sql1);
			pstmt2=con.prepareStatement(sql2);
			
			//Set values for place holders
			pstmt1.setInt(1, id);
			pstmt1.setString(2, name);
			pstmt1.setString(3, dept);
			pstmt1.executeUpdate();
			
			pstmt2.setInt(1, id);
			pstmt2.setString(2, name);
			pstmt2.setString(3, place);
			pstmt2.executeUpdate();
			
			con.commit();			
			
		} catch (ClassNotFoundException | SQLException e) {
			try {
				con.rollback();
				System.out.println("Entire transaction is rolled out");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if(pstmt2!=null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt1!=null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
