package org.example.service;

import org.example.dto.OrderBookDTO;
import org.example.model.OrderBook;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface OrderBooksService {

//    OrderBook saveBookOrder(OrderBookDTO orderBookDTO, int bookId, int userId);

//    List<OrderBook> findApprovedOrders(int userId);

    List<OrderBook> getAllBookByStatus();

    OrderBook findById(int orderId);

    void save(OrderBook orderBook);

    OrderBook getOrderBook(int orderId);

//    void statusUpdateByLibrarian(OrderBookDTO orderBookDTO, int orderId);
//
//    void statusRejectByLibrarian(OrderBookDTO orderBookDTO, int orderId);

    ModelAndView insertOrderBook(int cartId);
}
