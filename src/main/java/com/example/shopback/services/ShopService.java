package com.example.shopback.services;

import com.example.shopback.models.Product;
import com.example.shopback.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ProductRepository productRepository;

    public Integer createProduct(Product product){
        Product p = productRepository.save(product);
        return p.getId();
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }

    public Integer updateProduct(Product product){
        Integer id = product.getId();
        if(id != null && id != 0){
            Product oldProduct = productRepository.getById(id);
            product.update(oldProduct);
            Product p = productRepository.save(product);
            System.out.println(p.getName());
            return p.getId();
        }
        return 0;
    }

    public void updateProductDescriptionById(Integer id, String description){
        productRepository.updateDescription(id, description);
    }

    public void updateProductPriceById(Integer id, Float price){
        productRepository.updatePrice(id, price);
    }

    public void updateProductQuantityById(Integer id, Integer quantity){
        productRepository.updateQuantity(id, quantity);
    }

    public void setProductQuantityById(Integer id, Integer quantity){
        productRepository.setQuantity(id, quantity);
    }

    public Optional<Product> findProductById(Integer id){
        return productRepository.findById(id);
    }

    public Optional<Product> findProductByName(String name){
        return productRepository.getByName(name);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
