package com.order_mgr.order_service.controller;

import com.order_mgr.order_service.model.Order;
import com.order_mgr.order_service.logic.IOrderService;
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
            return ResponseEntity.ok(ApiResponse.success("Order Created Successfully", order));
        } catch (Exception e){
            return ResponseEntity.status(500).body(ApiResponse.error("Order creation failed"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> fetchAllOrders(){
        try {
            List<Order> orderList = orderInterface.fetchAllOrders();
            return ResponseEntity.ok(ApiResponse.success("Order List", orderList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Failed to fetch orders"));
        }
    }

    @PatchMapping("/admin/update")
    public ResponseEntity<ApiResponse> updateOrderStatus(@RequestParam int orderId, @RequestParam String status){
        try {
            Order updatedOrder = orderInterface.updateOrderStatus(orderId, status);

            // check if the status has been updated successfully
            if (updatedOrder.getStatus().toString().equals(status)){
                return ResponseEntity.ok(ApiResponse.success("Updated Order Status"));
            }

            throw new RuntimeException("Failed to update the order status");
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error("Failed to Update Order Status"));
        }
    }
}
