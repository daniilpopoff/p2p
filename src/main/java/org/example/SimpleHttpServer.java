package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        // Create an HTTP server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new RootHandler());
        server.setExecutor(null); // Creates a default executor
        server.start();
        System.out.println("Server is listening on port 8080");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            // Access the request body
            InputStream inStream = httpExchange.getRequestBody();
            Scanner scanner = new Scanner(inStream);
            String data = scanner.nextLine(); // Read the next line from the request body
            System.out.println(data); // Print the received data

            // Send a response back to the client
            String response = "Received your message: " + data;
            httpExchange.sendResponseHeaders(200, response.getBytes().length); // Set the response status code and content length
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes()); // Write the response body
            }
            scanner.close(); // Close the scanner
        }
    }
}

