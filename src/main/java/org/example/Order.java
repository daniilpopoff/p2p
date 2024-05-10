package org.example;

public class Order {
    private int orderId;
    private String coinName;
    private double pricePerCoin;
    private double fiatAmountToBuy;
    private double reserveQuantity;
    private double totalQuantity;
    private double fee;
    private int creatorUserId;
    private Integer buyerUserId; // Using Integer to allow null if there's no buyer yet
    private String paymentMethod;
    private boolean didBuyerPay;
    private boolean didOrderMakerPay;
    private boolean isOrderSuccess;

    // Constructor
    public Order(int orderId, String coinName, double pricePerCoin, double fiatAmountToBuy,
                 double reserveQuantity, double totalQuantity, double fee, int creatorUserId,
                 Integer buyerUserId, String paymentMethod, boolean didBuyerPay,
                 boolean didOrderMakerPay, boolean isOrderSuccess) {
        this.orderId = orderId;
        this.coinName = coinName;
        this.pricePerCoin = pricePerCoin;
        this.fiatAmountToBuy = fiatAmountToBuy;
        this.reserveQuantity = reserveQuantity;
        this.totalQuantity = totalQuantity;
        this.fee = fee;
        this.creatorUserId = creatorUserId;
        this.buyerUserId = buyerUserId;
        this.paymentMethod = paymentMethod;
        this.didBuyerPay = didBuyerPay;
        this.didOrderMakerPay = didOrderMakerPay;
        this.isOrderSuccess = isOrderSuccess;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getCoinName() { return coinName; }
    public void setCoinName(String coinName) { this.coinName = coinName; }

    public double getPricePerCoin() { return pricePerCoin; }
    public void setPricePerCoin(double pricePerCoin) { this.pricePerCoin = pricePerCoin; }

    public double getFiatAmountToBuy() { return fiatAmountToBuy; }
    public void setFiatAmountToBuy(double fiatAmountToBuy) { this.fiatAmountToBuy = fiatAmountToBuy; }

    public double getReserveQuantity() { return reserveQuantity; }
    public void setReserveQuantity(double reserveQuantity) { this.reserveQuantity = reserveQuantity; }

    public double getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(double totalQuantity) { this.totalQuantity = totalQuantity; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public int getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(int creatorUserId) { this.creatorUserId = creatorUserId; }

    public Integer getBuyerUserId() { return buyerUserId; }
    public void setBuyerUserId(Integer buyerUserId) { this.buyerUserId = buyerUserId; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public boolean isDidBuyerPay() { return didBuyerPay; }
    public void setDidBuyerPay(boolean didBuyerPay) { this.didBuyerPay = didBuyerPay; }

    public boolean isDidOrderMakerPay() { return didOrderMakerPay; }
    public void setDidOrderMakerPay(boolean didOrderMakerPay) { this.didOrderMakerPay = didOrderMakerPay; }

    public boolean isOrderSuccess() { return isOrderSuccess; }
    public void setOrderSuccess(boolean isOrderSuccess) { this.isOrderSuccess = isOrderSuccess; }
}
