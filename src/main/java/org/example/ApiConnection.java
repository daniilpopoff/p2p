package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ApiConnection {
    static String apiUrl = "https://api.polygon.io/v1/open-close/crypto/USDT/USD/2024-05-10?adjusted=true&apiKey=YIgHd1CETmA0ci_vKgNZPh5N7HFPx7ja";


    public static double CostPerCoin() throws IOException {
        // Define the URL for the API request
//        String apiUrl = "https://api.polygon.io/v1/open-close/crypto/USDT/USD/2024-05-10?adjusted=true&apiKey=YIgHd1CETmA0ci_vKgNZPh5N7HFPx7ja";

        // Create a URL object with the API URL
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Read the response from the API
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // Print the response
        System.out.println("Response: " + response.toString());

        // Close the connection
        connection.disconnect();


        JSONObject jsonResponse = new JSONObject(response.toString());

        // Extract the 'open' value from the JSON object
        double openPrice = jsonResponse.getDouble("open");


        return openPrice;
    }


}