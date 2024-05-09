package org.example;

public class Help {


    public static void showGeneralHelp() {
        System.out.println("Available commands:");
        System.out.println("1. register - Register as a new user.");
        System.out.println("2. login - Log into the application.");
        System.out.println("3. create_order - Create a new order.");
        System.out.println("4. view_orders - View list of all orders.");
        System.out.println("Type 'help [command]' to get more information on a specific command.");
    }

    public static void showRegistrationHelp() {
        System.out.println("Registration Help:");
        System.out.println("Command: register");
        System.out.println("Description: Use this command to register as a new user.");
        System.out.println("Usage: register");
    }

    public static void showLoginHelp() {
        System.out.println("Login Help:");
        System.out.println("Command: login");
        System.out.println("Description: Use this command to log into your account.");
        System.out.println("Usage: login");
    }

    public static void showCreateOrderHelp() {
        System.out.println("Create Order Help:");
        System.out.println("Command: create_order");
        System.out.println("Description: Use this command to create a new trading order.");
        System.out.println("Usage: create_order <currency_from> <currency_to> <amount>");
    }

    public static void showViewOrdersHelp() {
        System.out.println("View Orders Help:");
        System.out.println("Command: view_orders");
        System.out.println("Description: Use this command to view all current orders.");
        System.out.println("Usage: view_orders");
    }


}
