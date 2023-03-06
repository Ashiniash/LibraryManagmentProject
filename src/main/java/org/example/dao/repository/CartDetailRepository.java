package org.example.dao.repository;
import org.example.model.Book;
import org.example.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository <CartDetail, Integer> {
    @Query("SELECT cartDetail FROM CartDetail cartDetail WHERE cartDetail.userId=:userId ")
    List<CartDetail> displayMyOrders(@Param("userId") int userId);
    @Query("SELECT cartDetail FROM CartDetail cartDetail WHERE cartDetail.userId=:userId and cartDetail.cartId=:cartId")
    List<CartDetail> findCart(@Param("userId") int userId, @Param("cartId") int cartId);



}
