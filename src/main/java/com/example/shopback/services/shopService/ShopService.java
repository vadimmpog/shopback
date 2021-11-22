package com.example.shopback.services.shopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product){

    }

    public void deleteProduct(Product product){

    }

    public void updateProduct(Product product){

    }

    public void findProductById(int id){

    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
