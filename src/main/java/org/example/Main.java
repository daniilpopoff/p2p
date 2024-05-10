package org.example;

import java.util.List;
import java.util.Scanner;


import static org.example.InsertOrder.getCreatorPhoneNumber;
import static org.example.InsertOrder.getLastFiveOrders;
import static org.example.OrderManagement.createOrder;
import static org.example.RegistrationUser.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        while (true) {
            if (getCurrentUser() != null) {
                System.out.print(getCurrentUser().getUser_name() + "@app: $ "); // Custom prompt showing the username
            } else {
                System.out.print("app: $ "); // Default prompt when no user is logged in
            }
            System.out.println("Enter command (or 'help' for assistance): ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "help":
                    Help.showGeneralHelp();
                    break;
                case "help register":
                    Help.showRegistrationHelp();
                    break;
                case "help login":
                    Help.showLoginHelp();
                    break;
                case "help create_order":
                    Help.showCreateOrderHelp();
                    break;
                case "help view_orders":
                    Help.showViewOrdersHelp();
                    break;
                case "register":
                    registerUser();
                    break;
                case "login":
                    loginUser();
                    break;
                case "neworder":
                    InsertUser userFromDB = new InsertUser();
                    Order order = createOrder(userFromDB.getUserIdByEmail(getCurrentUser()),
                            "USDT",
                            850);
                    order.printOrderDetails(); //как вытащить id user
                    break;

                case "listorders":
                    List<Order> lastFiveOrders = getLastFiveOrders();
                    lastFiveOrders.forEach(orderlist -> {
                        System.out.println("Order ID: " + orderlist.getOrderId());
                        orderlist.printOrderDetails();
                    });
                    break;

                case "buyorder":
                    System.out.println("Input order id you are want to buy:");
                    int idOrder = scanner.nextInt();
                    getCreatorPhoneNumber(idOrder);
                    System.out.println("You can connect with this person and deal about purchase ");

                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }
    }
}