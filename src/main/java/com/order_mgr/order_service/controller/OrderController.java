package com.order_mgr.order_service.controller;

import com.order_mgr.order_service.logic.IOrderService;
import com.order_mgr.order_service.model.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")   // CORS resolution
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final IOrderService orderInterface;

    @PostMapping("/add")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        try{
            orderInterface.createOrder(order);
            logger.info("Order placed successfully");
            return ResponseEntity.ok("Order Created Successfully");
        } catch (Exception e){
            logger.error("Error while creating new order");
            return ResponseEntity.status(500).body("Order creation failed");
        }
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Order>> fetchAllOrders() {
//        try {
//            List<Order> orderList = orderInterface.fetchAllOrders();
//            return ResponseEntity.ok(orderList);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).build();
//        }
//    }

    @PatchMapping("/admin/update")
    public ResponseEntity<String> updateOrderStatus(@RequestParam int orderId, @RequestParam String status){
        try {
            Order updatedOrder = orderInterface.updateOrderStatus(orderId, status);

            // check if the status has been updated successfully
            if (updatedOrder.getStatus().toString().equals(status)){
                logger.info("Order status updated for the order id: {}", orderId);
                return ResponseEntity.ok("Updated Order Status");
            }

            throw new RuntimeException("Failed to update the order status");
        } catch (Exception e) {
            logger.error("failed to update the status for the order id: {}", orderId);
            return ResponseEntity.ok("Failed to Update Order Status");
        }
    }
}
