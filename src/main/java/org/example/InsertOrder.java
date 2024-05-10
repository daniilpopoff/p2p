package org.example;

import java.sql.*;


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

}
