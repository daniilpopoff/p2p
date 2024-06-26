package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class InsertUser {






    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:p2p.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void insert(String user_name, String email, String password, String phone_number) {
        String sql = "INSERT INTO users (user_name, email, password, phone_number ) VALUES(?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, phone_number);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public User getUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phone_number");
                    return new User(username, password, email, phoneNumber);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
    public int getUserIdByEmail( User user) {
        String sql = "SELECT id FROM Users WHERE email = ?";
        Connection conn = this.connect();


        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());  // Use email to identify the user
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user ID: " + e.getMessage());
        }
        return -1;  // Return -1 if the user is not found or if an error occurs
    }


}
