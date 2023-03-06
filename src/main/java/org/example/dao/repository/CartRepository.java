package org.example.dao.repository;

import org.example.model.Cart;
import org.example.service.implementation.CartServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId")
    List<Cart> getAllCart(@Param("userId") int userId);
    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId  and cart.orderStatus=:orderStatus")
    Cart findCart(@Param("userId") int userId, @Param("orderStatus") String orderStatus);


}
