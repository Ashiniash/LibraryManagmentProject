package org.example.service.implementation;

import org.example.dao.repository.CartDetailRepository;
import org.example.model.CartDetail;
import org.example.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    CartDetailRepository cartDetailRepository;
    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }


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
      return  cartDetailRepository.save(carts);
    }


    @Override
    public CartDetail getCartBookById(int cartId) {
        return  cartDetailRepository.findById(cartId).get();
    }

    @Override
    public List<CartDetail> getAllBookByStatus() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetail getCartDetailsById(int cartDetailId) {
        return cartDetailRepository.findById(cartDetailId).get();
    }

//    @Override
//    public List<CartDetail> findViewBooks(int userId, int cartId) {
//        return cartDetailRepository.findCart(userId,cartId);
//    }

//    @Override
//    public List<CartDetail> findApprovedOrders(int userId) {
//        return cartDetailRepository.displayApprovedOrders(userId);
//    }

//    @Override
//    public List<CartDetail> findPendingOrders(int userId) {
//        return cartDetailRepository.displayPendingOrders(userId);
//    }

    @Override
    public List<CartDetail> getCartDetailsByCartId(int cartId) {
        return (List<CartDetail>) cartDetailRepository.findById(cartId).get();
    }

//    @Override
//    public void statusUpdateByLibrarian(CartDetailDTO cartDetailDTO, int cartDetailId) {
//        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).get();
//        cartDetail.setOrderStatus((String.valueOf(orderStatus.APPROVED)));
//        cartDetailRepository.save(cartDetail);
//    }
}
