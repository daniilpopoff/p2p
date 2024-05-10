package org.example;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {  // Read user input from console
                out.println(userInput);  // Send user input to server
                System.out.println("Server says: " + in.readLine());  // Read server's response
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to connect to " + hostName + " on port " + port);
            System.out.println(e.getMessage());
        }
    }
}
