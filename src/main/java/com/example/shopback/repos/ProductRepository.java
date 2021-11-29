package com.example.shopback.repos;

import com.example.shopback.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name=:name")
    Optional<Product> getByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p SET p.description=:description WHERE p.id=:id")
    void updateDescription(@Param("id") Integer id, @Param("description") String description);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p SET p.price=:price WHERE p.id=:id")
    void updatePrice(@Param("id") Integer id, @Param("price") Float price);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p SET p.quantity=:quantity WHERE p.id=:id")
    void setQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product p SET p.quantity=p.quantity-:quantity WHERE p.id=:id")
    void updateQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);

    @Query("SELECT price, quantity FROM Product WHERE id=:id")
    Float getPrice(@Param("id") Integer id);

    @Query("SELECT quantity FROM Product WHERE id=:id")
    Integer getQuantity(@Param("id") Integer id);
}