package com.example.shopback.services.shopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/products")
public class ProductPage {
    List<Product> products;

    private ShopService shopService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void allProducts() {
        products = shopService.getAllProducts();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addProducts(@RequestBody Product product){
        products.add(product);
        return String.format("Сообщение \"%s\"%s", product.getName(), product.getDescription());
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public String deleteproducts(@RequestBody Product note){
        for (Product n : products){
            if(n.getMessage().equalsIgnoreCase(note.getMessage())){
                if(products.remove(note)){
                    return String.format("Сообщение удалено \"%s", note.getMessage());
                }
            }
        }
        return String.format("Сообщение не найдено \"%s", note.getMessage());
    }

    @RequestMapping(value = "/products/flush", method = RequestMethod.GET)
    public String clearProducts(){
        products.clear();
        return "Сообщения удалены";
    }


}