package com.busReservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Buses {
	
     public void DisplayInfo() throws SQLException
     {
    	 String Query="SELECT * FROM bus ";
    	 Connection con= DBConnection.getConnection();
    	 Statement st = con.createStatement();
    	 ResultSet rs = st.executeQuery(Query);
    	 while(rs.next())
    	 {
    		 System.out.println("Bus id :"+rs.getInt(1));
    		 System.out.println("Bus no :"+rs.getInt(2));
    	     System.out.println("Bus Route :"+rs.getString(3));
    	     System.out.println("Bus Capacity :"+rs.getInt(4));
     }  
    	 System.out.println("------------------------------------------");
    	 
     }
     
     public int Capacity(int id) throws SQLException
     {
    	 String Query="SELECT capacity FROM Bus WHERE busNo = "+ id;
    		
;
    	 Connection con= DBConnection.getConnection();
    	 Statement st = con.prepareStatement(Query);
          ResultSet rs = st.executeQuery(Query);
          rs.next();
         return rs.getInt(1);
     }
     
     public String BusRoute(int id) throws SQLException
     {
    	 String Query="SELECT route FROM Bus WHERE busNo = "+ id;
    		
;
    	 Connection con= DBConnection.getConnection();
    	 Statement st = con.prepareStatement(Query);
          ResultSet rs = st.executeQuery(Query);
          rs.next();
         return rs.getString(1);
     }
     
     
}
