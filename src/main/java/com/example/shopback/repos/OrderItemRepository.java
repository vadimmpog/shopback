package com.example.shopback.repos;


import com.example.shopback.models.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderId=:orderId")
    List<OrderItem> findByOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderId=null AND oi.userId=:userId")
    List<OrderItem> findAllByUserId(@Param("userId") Integer userId);
}
