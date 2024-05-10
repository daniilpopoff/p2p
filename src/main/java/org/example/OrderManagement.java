package org.example;

import static org.example.InsertOrder.insertOrder;
import static org.example.RegistrationUser.getCurrentUser;

public class OrderManagement {

    /**
     * Create a USDT order with a specified fiat amount.
     *
     * @param userId The ID of the user creating the order.
     * @param fiatAmount The amount in som to spend on USDT.
     * @return The created Order object.
     */
    public static Order createOrder(int userId, String coinName, double fiatAmount) {

        double pricePerCoin = getCoinPrice(coinName);  // 1 USDT = 91 som
        double totalQuantity = fiatAmount / pricePerCoin;  // Calculate how many USDTs can be bought
        double fee = calculateFee(fiatAmount);  // Assuming there's a method to calculate fees
        double reserveQuantity = fiatAmount - fee;
        // Assuming we have a method to get the next order ID from the database or memory
        int orderId = getNextOrderId();

        Order order = new Order(
                orderId,
                coinName,
                pricePerCoin,
                fiatAmount,
                reserveQuantity,  // For this example,  is the same as totalQuantity
                totalQuantity,
                fee,
                userId,
                null,  // BuyerUserId is null since it's a new order
                "Bank Transfer",  // Example payment method
                false,  // DidBuyerPay
                false,  // DidOrderMakerPay
                false   // IsOrderSuccess
        );


        // Assuming there's a method to save the order to a database
        InsertOrder newOrder = new InsertOrder();
        newOrder.insertOrder(order);

        return order;
    }



    private static int getNextOrderId() {
        // This should interact with your database to get the next order ID.
        // Placeholder for demonstration.
        return (int) (Math.random() * 1000);
    }

    private static void saveOrder(Order order) {
        // Implement this method to save order to your database.
        System.out.println("Order saved: " + order);
    }

    private static double calculateFee(double amount) {
        // Simple fee calculation, for example, 1% of the transaction amount
        return amount * 0.01;
    }


    private static double getCoinPrice(String coinName){
        double coinPrice = 0;
        if (coinName.equals("USDT")){
            coinPrice = 92.1;
        }
        return coinPrice;
    }





}

