package org.example.service;

import org.example.dto.CartDTO;
import org.example.model.Cart;
import org.example.model.CartDetail;
import java.util.List;

public interface CartService {
    Cart getCartById(int userId, int bookId);

    Cart getCartByStatus(int userId, String orderStatus);

    void placeOrder(Cart cart, int userId);

    List<Cart> getAllBookByStatus();

    Cart getCartsById(int cartId);

    void statusUpdateByLibrarian(CartDTO cartDTO, int cartId, CartDetail cartDetail);

    void statusRejectByLibrarian(CartDTO cartDTO, int cartId);

    void returnBook(int cartId, int userId);
}
