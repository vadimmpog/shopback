package com.example.shopback.controllers;
import com.example.shopback.models.Product;
import com.example.shopback.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ShopService shopService;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<Product> allProducts() {
        return shopService.getAllProducts();
    }

    @RequestMapping(value = "/user/find", method = RequestMethod.GET,
            params = {"id"})
    public Optional<Product> findProduct(
            @RequestParam(value = "id") Integer id) {
        return shopService.findProductById(id);
    }

    @RequestMapping(value = "/user/find", method = RequestMethod.GET,
            params = {"name"})
    public Optional<Product> findProduct(
            @RequestParam(value = "name") String name) {
        return shopService.findProductByName(name);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product){
        shopService.createProduct(product);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            params = {"id"})
    public void deleteProductById(
            @RequestParam(value = "id") Integer id){
        shopService.deleteProductById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateProduct(@RequestBody Product product){
        shopService.updateProduct(product);
    }

    @RequestMapping(value = "/updateDesc", method = RequestMethod.POST)
    public void updateProductDescription(@RequestBody String data){
        JSONObject dataJson = new JSONObject(data);
        String description = dataJson.getString("description");
        Integer id = dataJson.getInt("id");
        shopService.updateProductDescriptionById(id, description);
    }

    @RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
    public void updateProductPrice(@RequestBody String data){
        JSONObject dataJson = new JSONObject(data);
        Float price = dataJson.getFloat("price");
        Integer id = dataJson.getInt("id");
        shopService.updateProductPriceById(id, price);
    }

    @RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
    public void updateProductQuantity(@RequestBody String data){
        JSONObject dataJson = new JSONObject(data);
        Integer quantity = dataJson.getInt("quantity");
        Integer id = dataJson.getInt("id");
        shopService.updateProductQuantityById(id, quantity);
    }

    @RequestMapping(value = "/setQuantity", method = RequestMethod.POST)
    public void setProductQuantity(@RequestBody String data){
        JSONObject dataJson = new JSONObject(data);
        Integer quantity = dataJson.getInt("quantity");
        Integer id = dataJson.getInt("id");
        shopService.setProductQuantityById(id, quantity);
    }
}