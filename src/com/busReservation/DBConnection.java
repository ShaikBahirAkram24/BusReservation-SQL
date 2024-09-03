package com.busReservation;
import java.sql.*;


public class DBConnection {

private static final String url = "jdbc:mysql://localhost:3306/busdatabase";
private static final String userName = "root";
private static final String passWord = "12345";
		
public static Connection getConnection() throws SQLException{
return  DriverManager.getConnection(url,userName,passWord);
		

	}
}
