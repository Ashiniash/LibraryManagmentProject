package org.example.service.implementation;

import org.example.dao.repository.CartRepository;
import org.example.model.Cart;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;


    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }

    @Override
    public Cart getCartById(int userId, int bookId) {
        List<Cart>cartList=cartRepository.getAllCart(userId);
        if ((cartList.isEmpty())){
            return getCart(userId);
        }else{
            if((cartRepository.findCart(userId, String.valueOf(orderStatus.PENDING))==null)){
                return getCart(userId);
            }else return cartRepository.findCart(userId, String.valueOf(orderStatus.PENDING));
        }
    }
    private Cart getCart(int userId) {
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setOrderStatus(String.valueOf(orderStatus.PENDING));
        cartRepository.save(newCart);
        return newCart;
    }
    @Override
    public Cart getCartByStatus(int userId, String orderStatus) {
        return cartRepository.findCart(userId,orderStatus);
    }

    @Override
    public Cart getCartBookById(int cartId) {
        return  cartRepository.findById(cartId).get();
    }

    @Override
    public Cart findCartById(int cartId) {
        return cartRepository.findById(cartId).get();
    }
//
//    @Override
//    public ModelAndView orderBookStatus(Cart carts) {
//        carts.setOrderBook(true);
//        cartRepository.save(carts);
//        return null;
//    }

}
