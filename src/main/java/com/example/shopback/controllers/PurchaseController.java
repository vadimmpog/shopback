package com.example.shopback.controllers;

import com.example.shopback.models.Order;
import com.example.shopback.models.OrderItem;
import com.example.shopback.services.PurchaseService;
import com.example.shopback.support.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/order")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Order> getOrders() {
        return purchaseService.getAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
        params = {"id"})
    public void deleteOrder(@RequestParam("id") Integer id) {
        purchaseService.deleteOrder(id);
    }

    @RequestMapping(value = "/delete/orderItem", method = RequestMethod.DELETE,
        params = {"id"})
    public void deleteOrderItem(@RequestParam("id") Integer id) {
        purchaseService.deleteFromOrder(id);
    }

    @RequestMapping(value = "/formOrder", method = RequestMethod.POST)
    public void formOrder(@RequestBody Tuple<Integer,List<OrderItem>> data) {
        purchaseService.formOrder(data.x, data.y);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET,
            params = {"orderId"})
    public Tuple<List<OrderItem>,Float> getOrderList(@RequestParam(value = "orderId") Integer order_id) {
        return purchaseService.getOrderList(order_id);
    }
}
