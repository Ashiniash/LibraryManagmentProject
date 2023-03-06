package org.example.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cartdetail")
public class CartDetail {
    @Id
    @Column(name = "cartDetail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartDetail;

    public int getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(int cartDetail) {
        this.cartDetail = cartDetail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Column(name = "userId")
    int userId;
    @Column(name = "bookId")
    int bookId;
    @Column(name = "cartId")
    int cartId;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Column(name = "orderDate")
    Date orderDate;




    @Column(name = "returnDate")
    Date returnDate;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "orderStatus")
    String orderStatus;
}
