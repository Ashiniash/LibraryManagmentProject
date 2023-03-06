package org.example.dao.repository;

import org.example.model.OrderBook;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderBooksRepository extends JpaRepository<OrderBook, Integer> {

//    @Query("SELECT user FROM User user WHERE user.username=:username ")
//    List<User> registeredUsername(@Param("username") String username);
//
//    @Query("SELECT orderBook FROM OrderBook orderBook WHERE orderBook.userId=:userId and orderBook.orderStatus='APPROVED'")
//    List<OrderBook> displayApprovedOrders(@Param("userId") int userId);
//
//    @Query("SELECT orderBook FROM OrderBook orderBook WHERE orderBook.userId=:userId ")
//    List<OrderBook> displayMyOrders(@Param("userId") int userId);
}
