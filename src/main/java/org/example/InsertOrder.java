package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InsertOrder {
    private static Connection connect() {
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



    public static void insertOrder(Order order) {
        String sql = "INSERT INTO Orders (buy, price, fiat_amount, reserve_quantity, total_quantity, fee, creation_order_user_id, buyer_user_id, payment_method_str, did_buyer_paid, did_order_maker_paid, is_order_success) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, order.getCoinName());
            pstmt.setDouble(2, order.getPricePerCoin());
            pstmt.setDouble(3, order.getFiatAmountToBuy());
            pstmt.setDouble(4, order.getReserveQuantity());
            pstmt.setDouble(5, order.getTotalQuantity());
            pstmt.setDouble(6, order.getFee());
            pstmt.setInt(7, order.getCreatorUserId());
            pstmt.setObject(8, order.getBuyerUserId());  // Use setObject for nullable integer
            pstmt.setString(9, order.getPaymentMethod());
            pstmt.setBoolean(10, order.isDidBuyerPay());
            pstmt.setBoolean(11, order.isDidOrderMakerPay());
            pstmt.setBoolean(12, order.isOrderSuccess());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));  // Retrieves the auto-generated order ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e.getMessage());
        }
    }





    public static List<Order> getLastFiveOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders ORDER BY order_id DESC LIMIT 5";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("buy"),
                        rs.getDouble("price"),
                        rs.getDouble("fiat_amount"),
                        rs.getInt("reserve_quantity"),
                        rs.getInt("total_quantity"),
                        rs.getDouble("fee"),
                        rs.getInt("creation_order_user_id"),
                        rs.getObject("buyer_user_id") != null ? rs.getInt("buyer_user_id") : null, // handle possible null
                        rs.getString("payment_method_str"),
                        rs.getBoolean("did_buyer_paid"),
                        rs.getBoolean("did_order_maker_paid"),
                        rs.getBoolean("is_order_success")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching orders: " + e.getMessage());
        }
        return orders;
    }


    public static String getCreatorPhoneNumber(int orderId) {
        String sql = "SELECT u.phone_number FROM Users u JOIN Orders o ON u.user_id = o.creation_order_user_id WHERE o.order_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("phone_number");
                } else {
                    System.out.println("No order found with ID: " + orderId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null; // Return null if no phone number is found or an error occurs
    }

//    public static void main(String[] args) {
////        List<Order> lastFiveOrders = getLastFiveOrders();
////        lastFiveOrders.forEach(order -> {
////            System.out.println("Order ID: " + order.getOrderId());
////            order.printOrderDetails();
//            // Add more print statements if needed to display other details
//        });
//    }

}
