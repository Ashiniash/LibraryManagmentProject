package org.example.dto;


public class CartDTO {


    int cartId;

    int userId;

    String orderStatus;


    boolean orderBook;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isOrderBook() {
        return orderBook;
    }

    public void setOrderBook(boolean orderBook) {
        this.orderBook = orderBook;
    }

}
