package org.example.dao.repository;

import org.example.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId")
    List<Cart> getAllCart(@Param("userId") int userId);

    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId  and cart.orderStatus=:orderStatus ")
    Cart findCart(@Param("userId") int userId, @Param("orderStatus") String orderStatus);


    @Query("SELECT cart FROM Cart cart WHERE  cart.orderStatus='PENDING'")
    List<Cart> displayPendingBooks();

    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId ")
    List<Cart> displayMyOrders(@Param("userId") int userId);


    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId  and cart.cartId=:cartId")
    Cart findCartById(@Param("userId") int userId, @Param("cartId") int cartId);

    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId  and cart.cartId=:cartId")
    Cart findCartsById(@Param("userId") int userId, @Param("cartId") int cartId);

    @Query("SELECT cart FROM Cart cart WHERE cart.userId=:userId and cart.orderStatus=:orderStatus ")
    List<Cart> displayMyOrdersByStatus(@Param("userId") int userId, @Param("orderStatus") String orderStatus);


    @Query("SELECT cart FROM Cart cart WHERE cart.cartId=:cartId and cart.userId=:userId")
    List<Cart> displayMyOrdersByCartId(@Param("cartId") int cartId, @Param("userId") int userId);

    @Query("SELECT cart FROM Cart cart WHERE  cart.userId=:userId and cart.orderStatus=:orderStatus")
    List<Cart> displayLoanedBookByCartId(@Param("userId") int userId, @Param("orderStatus") String orderStatus);

}
