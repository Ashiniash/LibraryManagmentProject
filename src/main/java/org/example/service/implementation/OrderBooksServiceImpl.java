package org.example.service.implementation;

import org.example.dao.repository.CartRepository;
import org.example.dao.repository.OrderBooksRepository;
import org.example.dto.OrderBookDTO;
import org.example.model.Cart;
import org.example.model.OrderBook;
import org.example.service.OrderBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

public class OrderBooksServiceImpl implements OrderBooksService {
    @Autowired
    OrderBooksRepository orderBooksRepository;
    @Autowired
    CartRepository cartRepository;

    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }

//    @Override
//    public OrderBook saveBookOrder(OrderBookDTO orderBookDTO, int bookId, int userId) {
//        OrderBook orderBook = new OrderBook();
//        orderBook.setOrderStatus(String.valueOf(orderStatus.PENDING));
//        orderBook.setBookId(bookId);
//        orderBook.setUserId(userId);
//        orderBook.setOrderDate(orderBookDTO.getOrderDate());
//        orderBook.setReturnDate(orderBookDTO.getReturnDate());
//        return orderBooksRepository.save(orderBook);
//
//    }

    @Override
    public ModelAndView insertOrderBook(int cartId) {
        Cart cart=cartRepository.findById(cartId).get();
        OrderBook orderBook=new OrderBook();
        orderBook.setCartId(cart.getCartId());
        orderBook.setUserId(cart.getUserId());
        orderBooksRepository.save(orderBook);
        return new ModelAndView("order Placed");
    }

    @Override
    public ModelAndView saveOrderBook(int cartId) {
        return null;
    }
//    @Override
//    public List<OrderBook> findApprovedOrders(int userId) {
//        return orderBooksRepository.displayApprovedOrders(userId);
//    }

    @Override
    public List<OrderBook> getAllBookByStatus() {
        return orderBooksRepository.findAll();
    }

    @Override
    public OrderBook findById(int orderId) {
        return orderBooksRepository.findById(orderId).get();
    }

    @Override
    public void save(OrderBook orderBook) {
        orderBooksRepository.save(orderBook);
    }

    @Override
    public OrderBook getOrderBook(int orderId) {
        return orderBooksRepository.findById(orderId).get();
    }

//    @Override
//    public void statusUpdateByLibrarian(OrderBookDTO orderBookDTO, int orderId) {
//        OrderBook orderBookObj = orderBooksRepository.findById(orderId).get();
//        orderBookObj.setOrderStatus((String.valueOf(orderStatus.APPROVED)));
//        orderBooksRepository.save(orderBookObj);
//    }
//
//    @Override
//    public void statusRejectByLibrarian(OrderBookDTO orderBookDTO, int orderId) {
//        OrderBook orderBookObj = orderBooksRepository.findById(orderId).get();
//        orderBookObj.setOrderStatus((String.valueOf(orderStatus.PENDING)));
//        orderBooksRepository.save(orderBookObj);
//    }




}



