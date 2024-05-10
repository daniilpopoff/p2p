package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.json.JSONObject;

import javax.swing.text.Document;

public class USDPriceParser {

    public static double getUSSDPrice() throws IOException {
        // Define the URL for the API request
        String apiUrl = "https://freecurrencyrates.com/ru/convert-KGS-USD#google_vignette\n";

        // Create a URL object with the API URL
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        System.out.println("Response Code: " + responseCode);
        System.out.println(connection.toString());

        return 0 ;
    }

    public static void main(String[] args) throws IOException {
//        getUSSDPrice();
        System.out.println(fetchHtml("https://freecurrencyrates.com/ru/convert-KGS-USD#google_vignette"));
    }






    public static String fetchHtml(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check if the response code is HTTP OK
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: HTTP status code " + status);
        }

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder htmlContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            htmlContent.append(line);
        }

        reader.close();
        connection.disconnect();

        return htmlContent.toString();
    }


    public static double parseUSDPrice(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);
        Element inputElement = document.getElementById("value_to");
        String valueStr = inputElement.attr("value");
        return Double.parseDouble(valueStr);
}



//https://freecurrencyrates.com/ru/convert-KGS-USD#google_vignette
//<input type="text" id="value_to" class="thin cp-input" value="0.011">