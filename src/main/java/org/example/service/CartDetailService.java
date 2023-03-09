package org.example.service;

import org.example.model.CartDetail;

import java.util.List;

public interface CartDetailService {


    CartDetail getCartDetailById(int cartId);

    List<CartDetail> findCartByCartId(int cartId);
    CartDetail orderBookStatus(CartDetail carts);

    CartDetail getCartBookById(int cartId);

    List<CartDetail> getAllBookByStatus();

    CartDetail getCartDetailsById(int cartDetailId);


//    List<CartDetail> findViewBooks(int userId, int cartId);

//    List<CartDetail> findApprovedOrders(int userId);
//
//    List<CartDetail> findPendingOrders(int userId);

    List<CartDetail> getCartDetailsByCartId(int cartId);
}
