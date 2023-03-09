package org.example.dao.repository;

import org.example.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {


    @Query("SELECT cartDetail FROM CartDetail cartDetail WHERE cartDetail.cartId=:cartId ")
    List<CartDetail> findByCartId(@Param("cartId") int cartId);


}
