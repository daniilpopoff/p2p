package org.example;

import java.util.Scanner;

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
                case "register ":
                    registerUser();
                    break;
                case "login":
                    loginUser();
                    break;
                case "new order":
                    createOrder(); //как вытащить id user

                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }
    }
}