package com.busReservation;

import java.sql.Connection;
import java.sql.Date; // java.sql.Date for SQL operations
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Bookings {
    private int id = 0;
    private String name;
    private int busno; // Changed to int
    private int age;
    private String gender;
    private java.util.Date date; // java.util.Date for input
    private Scanner sc = new Scanner(System.in); // Single Scanner object

    public void getDetails() throws SQLException 
    {
 
        System.out.println("Enter your name:");
        name = sc.next();

        System.out.println("Enter your age:");
        age = sc.nextInt();

        System.out.println("Enter bus number (e.g., 1, 2, 3):");
        busno = sc.nextInt(); // Updated to int

        System.out.println("Enter gender:");
        gender = sc.next();

        System.out.println("Enter travel date (yyyy-MM-dd):");
        String dateString = sc.next();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in 'yyyy-MM-dd' format.");
            return; // Exit the method if the date format is invalid
        }

        isAvailable(busno, new java.sql.Date(date.getTime()));
    }

    public void isAvailable(int busNo, Date dateOfJourney) throws SQLException {
        int isCapacityAvailable = bookedCount(busNo, dateOfJourney);
        Buses bus = new Buses();
        int capacityOfBus = bus.Capacity(busNo);

        if (isCapacityAvailable < capacityOfBus) {
            System.out.println("Seats are available for the bus you're looking for. Hurry up!!!!");
            System.out.println("Enter 'proceed' to confirm booking:");
            String userInput = sc.next(); // Using the same Scanner object
            if (userInput.equals("proceed")) {
                addBookings();
            }
        } else {
            System.out.println("Sorry, there are no seats available.");
        }
    }

    public void addBookings() throws SQLException {
        String query = "INSERT INTO passengertable ( id,name, age, gender, busNo, dateOfJourney) VALUES (?, ?, ?, ?, ?, ?)";
        Buses bus = new Buses();
        String BusRoute=bus.BusRoute(busno);
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, age);
            pst.setString(4, gender);
            pst.setInt(5, busno); 
            pst.setDate(6, new java.sql.Date(date.getTime()));
            pst.executeUpdate();
        }
        System.out.println("Your Booking is confirmed on :"+date+"in Bus No: "+busno+"for Passenger Name: "+name+"on Route: "+BusRoute);
        System.out.println("ThankYou for Ur Bookings !!!");
    }

    public int bookedCount(int busNo, java.sql.Date dateOfJourney) throws SQLException {
        String query = "SELECT COUNT(*) AS passenger_count FROM Passengertable WHERE busNo = ? AND dateOfJourney = ?;";
        
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, busNo); // Changed to int
        st.setDate(2, dateOfJourney);
        
        ResultSet rs = st.executeQuery();
        rs.next();
        int passengersCount = rs.getInt("passenger_count");
        
        rs.close();
        st.close();
        con.close();
        
        return passengersCount;
    }
}
