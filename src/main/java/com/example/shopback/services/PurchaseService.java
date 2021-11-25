package com.example.shopback.services;

import com.example.shopback.models.*;
import com.example.shopback.repos.*;
import com.example.shopback.support.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public Tuple<List<OrderItem>,Float> getOrderList(Integer orderId){
        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderId);
        Float sum = (float)0;
        return new Tuple<>(orderItemList, sum);
    }

    public void deleteFromOrder(Integer productListId){
        orderItemRepository.deleteById(productListId);
    }

    public Integer formOrder(Integer userId, List<OrderItem> productList){
        boolean notEnough = false;
        for(OrderItem orderItem : productList){
            Integer productId = orderItem.getProductId();
            if(productId != null) {
                Integer quantity = productRepository.getQuantity(productId);
                if (quantity < orderItem.getQuantity()) {
                    notEnough = true;
                    break;
                }
            }
        }
        Integer orderId = 0;
        if(!notEnough) {
            Order order = new Order();
            order.setUserId(userId);
            orderId = orderRepository.save(order).getId();
            for (OrderItem i : productList) {
                i.setOrderId(orderId);
                productRepository.updateQuantity(i.getProductId(), i.getQuantity());
                orderItemRepository.save(i);
            }
            orderRepository.setSum(orderId);
        }
        return orderId;
    }


}
