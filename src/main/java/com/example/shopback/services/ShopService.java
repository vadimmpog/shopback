package com.example.shopback.services;

import com.example.shopback.MessageResponse;
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

//    @Autowired
//    private CartRepository cartRepository;

    public MessageResponse createProduct(Product product){
        Product p = productRepository.save(product);
        return new MessageResponse("Product successfully created", p.getId());
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }

    public MessageResponse updateProduct(Product product){
        Product p = productRepository.save(product);
        return new MessageResponse("Product successfully updated", p.getId());
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
