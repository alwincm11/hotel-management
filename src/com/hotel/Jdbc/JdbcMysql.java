package com.hotel.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcMysql {
	public static Statement stmt;
	public static  void conectToOracleDatabase() {
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		try {
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver"); 

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection = null;

		try {

         
           connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagement","root",""); 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("success");
		} else {
			System.out.println("Failed to make connection!");
		}
      try {
            stmt = connection.createStatement();
            
	     //   loadDatabaseToTable();
	      // stmt.close();
          // connection.close();
      }
      catch (SQLException e) {

			System.out.println("error in accessing the relation");
			e.printStackTrace();
			return;

		}    
	}

}
