package org.example;

import java.sql.*;
import java.util.Scanner;

public class RegistrationUser {
    private static User currentUser;



public static void main(String[] args) {
    registerUser();

}


public static void registerUser() {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter your username: ");
    String username = scanner.nextLine();
    System.out.println("Please enter your email: ");
    String email = scanner.nextLine();
    System.out.println("Please enter your phone number: ");
    String phoneNumber = scanner.nextLine();
    System.out.println("Please enter your password: ");
    String password = scanner.nextLine();
    System.out.println("Please enter your password again: ");
    String passwordAgain = scanner.nextLine();

    try{
        if (!password.equals(passwordAgain)) {
            throw new Exception("Passwords do not match!");
//            registerUser();
        }
        else{
            currentUser = new User(username, password, email, phoneNumber);
            System.out.println("You have successfully registered!");
        }
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
    }



    InsertUser app = new InsertUser();
    app.insert(username, email, phoneNumber, password);
}

    public static void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        // Simulate login by checking with a database or a simple check if username and password match
        if (authenticate(username, password)) {
            currentUser = new User(username, password); // Set the logged user
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed, try again.");
        }
    }

    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                // if the result set is not empty, user is authenticated
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
