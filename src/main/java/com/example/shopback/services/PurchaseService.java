package com.example.shopback.services;

import com.example.shopback.models.*;
import com.example.shopback.repos.*;
import com.example.shopback.support.CartItem;

import com.example.shopback.support.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UsersRepository usersRepository;

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

    public void deleteCartItem(Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User u = usersRepository.getByLogin(login).orElse(null);
        OrderItem i = orderItemRepository.findById(id).orElse(null);
        if(u != null && i != null) {
            if(i.getOrderId() == null && u.getId() == i.getUserId())
                orderItemRepository.deleteById(id);
        }
    }

    public Integer formOrder(List<OrderItem> productList){
        Integer orderId = 0;
        if(!productList.isEmpty()) {
            boolean notEnough = false;
            for (OrderItem orderItem : productList) {
                Integer productId = orderItem.getProductId();
                if (productId != null) {
                    Integer quantity = productRepository.getQuantity(productId);
                    if (quantity < orderItem.getQuantity()) {
                        notEnough = true;
                        break;
                    }
                }
            }
            if (!notEnough) {
                Order order = new Order();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String login = authentication.getName();
                usersRepository.getByLogin(login).ifPresent(u -> order.setUserId(u.getId()));
                orderId = orderRepository.save(order).getId();
                for (OrderItem i : productList) {
                    orderItemRepository.deleteById(i.getId());
                    i.setId(null);
                    i.setOrderId(orderId);
                    i.setUserId(order.getUserId());
                    productRepository.updateQuantity(i.getProductId(), i.getQuantity());
                    orderItemRepository.save(i);
                }

                orderRepository.setSum(orderId);
            }
        }
        return orderId;
    }

    public List<CartItem> loadCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User u = usersRepository.getByLogin(login).orElse(null);
        if(u != null){
            List<CartItem> cartList = new ArrayList<>();
            List<OrderItem> list = orderItemRepository.findAllByUserId(u.getId());
            for(OrderItem i: list){
                productRepository.findById(i.getProductId()).ifPresent(p -> cartList.add(new CartItem(p.getName(), p.getPrice(), i.getQuantity(), i.getId(), p.getId())));
            }
            return cartList;
        }
        return null;
    }

    public void addItem(OrderItem item){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        usersRepository.getByLogin(login).ifPresent(user -> item.setUserId(user.getId()));
        if (item.getUserId() != null && item.getProductId() != null && item.getQuantity() != null) {
            item.setOrderId(null);
            orderItemRepository.save(item);
        }
    }

}
