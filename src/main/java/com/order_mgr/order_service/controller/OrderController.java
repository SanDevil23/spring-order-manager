package com.order_mgr.order_service.controller;

import com.order_mgr.order_service.model.Order;
import com.order_mgr.order_service.service.IOrderService;
import com.order_mgr.order_service.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")   // CORS resolution **not recommended for production
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    private final IOrderService orderInterface;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order){
        try{
            orderInterface.createOrder(order);
            return ResponseEntity.ok(new ApiResponse("Order Created Successfully", order));
        } catch (Exception e){
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

    @PatchMapping("/admin/update")
    public ResponseEntity<ApiResponse> updateOrderStatus(@RequestParam int orderId, @RequestParam String status){
        try {
            Order updatedOrder = orderInterface.updateOrderStatus(orderId, status);

            // check if the status has been updated successfully
            if (updatedOrder.getStatus().toString().equals(status)){
                return ResponseEntity.ok(new ApiResponse("Updated Order Status", null));
            }

            throw new RuntimeException("Failed to update the order status");
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse("Failed to Update Order Status", null));
        }
    }
}
