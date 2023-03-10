package org.example.service.implementation;

import org.example.controller.UserController;
import org.example.dao.repository.CartDetailRepository;
import org.example.dao.repository.CartRepository;
import org.example.dto.CartDTO;
import org.example.model.Cart;
import org.example.model.CartDetail;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartDetailRepository cartDetailRepository;

    @Override
    public Cart getCartById(int userId, int bookId) {
        List<Cart> cartList = cartRepository.getAllCart(userId);
        if ((cartList.isEmpty())) {
            return getCart(userId);
        } else {
            if ((cartRepository.findCart(userId, String.valueOf(orderStatus.PENDING)) == null)) {
                return getCart(userId);
            } else return cartRepository.findCart(userId, String.valueOf(orderStatus.PENDING));
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
        return cartRepository.findCart(userId, orderStatus);
    }

    @Override
    public Cart getCartBookById(int cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Cart findCartById(int cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public void placeOrder(Cart cart, int userId) {
        cart.setRequestBook(true);
        cart.setOrderStatus(String.valueOf(orderStatus.PENDING));
        cart.setUserId(userId);
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllBookByStatus() {
        return cartRepository.displayPendingBooks();
    }

    @Override
    public Cart getCartsById(int cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public void statusUpdateByLibrarian(CartDTO cartDTO, int cartId, CartDetail cartDetail) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setOrderStatus((String.valueOf(CartDetailServiceImpl.orderStatus.APPROVED)));
        cartRepository.save(cart);
    }

    @Override
    public void statusRejectByLibrarian(CartDTO cartDTO, int cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setOrderStatus((String.valueOf(orderStatus.PENDING)));
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> displayCartByUserId(int userId) {
        return (List<Cart>) cartRepository.findById(userId).get();
    }

    @Override
    public Cart getCartByUserId(int userId) {
        return cartRepository.findById(userId).get();
    }

    @Override
    public List<Cart> getAllBookByUserId(int userId, String orderStatus) {
        return cartRepository.findBookByStatus(userId);
    }

    @Override
    public void returnBook(int cartId, int userId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setOrderStatus(String.valueOf(orderStatus.RETURNED));
        cartRepository.save(cart);

    }

    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }
}





