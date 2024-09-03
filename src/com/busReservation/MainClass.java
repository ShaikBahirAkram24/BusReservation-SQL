package com.busReservation;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String userInput = homepage(sc);
            
            while (userInput.equals("x")) {
                System.out.println("Your Reservation starts here");
                // Add the logic for reservation here
                Bookings book = new Bookings();
                book.getDetails();
                
                // Prompt user for further action
                System.out.println("Enter 'x' to continue with another reservation or 'y' to exit:");
                userInput = sc.next(); // Update userInput based on user response
            }
            
            System.out.println("Thank you for Visiting Us!!!");    
        } catch (SQLException e) {
            System.out.println("An error occurred while processing your request: " + e.getMessage());
        } finally {
            sc.close(); // Close the scanner at the end of the program
        }
    }

    public static String homepage(Scanner sc) {
        System.out.println("Indian Bus Reservation");
        System.out.println("Please proceed to 'x' for reservation or 'y' to exit:");
        return sc.next();
    }
}
