package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;



public class TestJdbc {

	public static void main(String[] args) {
		//download hibernate orm, put files from lib to lib in project
		//mysql.com/downloads -> connectros -> connector/j put jar to lib in project
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		
		try{
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("Connection successful!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
