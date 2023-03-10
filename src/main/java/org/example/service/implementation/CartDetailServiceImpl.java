package org.example.service.implementation;

import org.example.dao.repository.CartDetailRepository;
import org.example.model.CartDetail;
import org.example.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    CartDetailRepository cartDetailRepository;

    @Override
    public CartDetail getCartDetailById(int cartId) {
        return cartDetailRepository.findById(cartId).get();
    }

    @Override
    public List<CartDetail> findCartByCartId(int cartId) {
        return (List<CartDetail>) cartDetailRepository.findById(cartId).get();
    }

    @Override
    public CartDetail orderBookStatus(CartDetail carts) {
        return cartDetailRepository.save(carts);
    }

    @Override
    public CartDetail getCartBookById(int cartId) {
        return cartDetailRepository.findById(cartId).get();
    }

    @Override
    public List<CartDetail> getAllBookByStatus() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetail getCartDetailsById(int cartDetailId) {
        return cartDetailRepository.findById(cartDetailId).get();
    }

    @Override
    public List<CartDetail> getCartDetailsByCartId(int cartId) {
        return (List<CartDetail>) cartDetailRepository.findById(cartId).get();
    }


    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }
}
