package com.order_mgr.order_service.controller;

import com.order_mgr.order_service.model.Order;
import com.order_mgr.order_service.service.IOrderService;
import com.order_mgr.order_service.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")   // CORS resolution
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
//    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final IOrderService orderInterface;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order){
        try{
            orderInterface.createOrder(order);
//            logger.info("Order placed successfully");
            return ResponseEntity.ok(new ApiResponse("Order Created Successfully", order));
        } catch (Exception e){
//            logger.error("Error while creating new order");
            return ResponseEntity.status(500).body(new ApiResponse("Order creation failed", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> fetchAllOrders(){
        try {
            List<Order> orderList = orderInterface.fetchAllOrders();
            return ResponseEntity.ok(new ApiResponse("Order List", orderList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to fetch orders", null));
        }
    }

}
