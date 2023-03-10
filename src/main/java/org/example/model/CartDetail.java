package org.example.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cartdetail")
public class CartDetail {

    @Id
    @Column(name = "cartDetail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartDetailId;
    @Column(name = "bookId")
    int bookId;
    @Column(name = "cartId")
    int cartId;
    @Column(name = "orderDate")
    Date orderDate;
    @Column(name = "returnDate")
    Date returnDate;

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

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

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


}
