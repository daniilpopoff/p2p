package org.example;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RegistrationUser {
    private static User currentUser;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationUser.class);


    public static User getCurrentUser() {
        if (currentUser != null) {
        }
        return currentUser;
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
            currentUser = new User( username, password, email, phoneNumber);//todo make function to take user id
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

        InsertUser app = new InsertUser();
        User user = app.getUser(username, password) ;
        if (user != null ) {
            currentUser = user; // Set the logged user
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed, try again.");
        }
    }




}
