package com.example.shopback.repos;


import com.example.shopback.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Order o SET o.sum=(SELECT SUM(p.price * oi.quantity) FROM OrderItem oi JOIN Product p ON p.id=oi.productId WHERE oi.orderId=:orderId) WHERE o.id=:orderId")
    void setSum(@Param("orderId") Integer orderId);
}

