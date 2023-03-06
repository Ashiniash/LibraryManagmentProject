package org.example.service;

import org.example.model.CartDetail;

import java.util.List;

public interface CartDetailService {


    CartDetail getCartDetailById(int cartId);



    List<CartDetail> findCartByCartId(int cartId);
    CartDetail orderBookStatus(CartDetail carts);

    CartDetail getCartBookById(int cartId);
}
