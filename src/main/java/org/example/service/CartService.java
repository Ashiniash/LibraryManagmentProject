package org.example.service;

import org.example.model.Cart;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


public interface CartService {
    Cart getCartById(int userId, int bookId);

    Cart getCartByStatus(int userId, String orderStatus);

    Cart getCartBookById(int cartId);

    Cart findCartById(int cartId);

//
//    ModelAndView orderBookStatus(Cart carts);
}
