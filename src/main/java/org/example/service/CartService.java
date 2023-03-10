package org.example.service;

import org.example.controller.UserController;
import org.example.dto.CartDTO;
import org.example.model.Cart;
import org.example.model.CartDetail;

import java.util.List;


public interface CartService {
    Cart getCartById(int userId, int bookId);

    Cart getCartByStatus(int userId, String orderStatus);

    Cart getCartBookById(int cartId);

    Cart findCartById(int cartId);


    void placeOrder(Cart cart, int userId);


//    List<Cart> getRequestedSongsByStatus();

    List<Cart> getAllBookByStatus();

    Cart getCartsById(int cartId);

    void statusUpdateByLibrarian(CartDTO cartDTO, int cartId, CartDetail cartDetail);

    void statusRejectByLibrarian(CartDTO cartDTO, int cartId);


    List<Cart> displayCartByUserId(int userId);

    Cart getCartByUserId(int userId);

    List<Cart> getAllBookByUserId(int userId, String orderStatus);

    void returnBook(int cartId, int userId);
}
