package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
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

    @Id
    @Column(name = "cartId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartId;
    @Column(name = "userId")
    int userId;
    @Column(name = "orderStatus")
    String orderStatus;



    @Column(name = "requestBook")
    boolean requestBook;

    public boolean isRequestBook() {
        return requestBook;
    }

    public void setRequestBook(boolean requestBook) {
        this.requestBook = requestBook;
    }
}
