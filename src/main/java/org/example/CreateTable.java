package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:p2p.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " user_name text NOT NULL,\n"
                + " email text, \n"
                + " password text, \n"
                + " phone_number text, \n"
                + " is_phone_num_real integer DEFAULT 0, \n"
                + " is_email_real integer DEFAULT 0 \n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
        createOrdersTable();
    }


    public static void createOrdersTable() {
        // Connection string should be appropriately defined
        String url = "jdbc:sqlite:p2p.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // The SQL string is executed to create the table
            String sql = "CREATE TABLE IF NOT EXISTS Orders (\n"
                    + " order_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " buy TEXT NOT NULL,\n"
                    + " price REAL NOT NULL,\n"
                    + " fiat_amount REAL NOT NULL,\n"
                    + " reserve_quantity REAL,\n"
                    + " total_quantity REAL,\n"
                    + " fee REAL NOT NULL,\n"
                    + " creation_order_user_id INTEGER NOT NULL,\n"
                    + " buyer_user_id INTEGER,\n"
                    + " payment_method_str TEXT,\n"
                    + " did_buyer_paid BOOLEAN,\n"
                    + " did_order_maker_paid BOOLEAN,\n"
                    + " is_order_success BOOLEAN,\n"
                    + " FOREIGN KEY (creation_order_user_id) REFERENCES Users(user_id),\n"
                    + " FOREIGN KEY (buyer_user_id) REFERENCES Users(user_id)\n"
                    + ");";


            stmt.execute(sql);
            System.out.println("Orders table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating the Orders table: " + e.getMessage());
        }
    }

}
