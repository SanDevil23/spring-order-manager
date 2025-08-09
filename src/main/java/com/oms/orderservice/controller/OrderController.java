package com.oms.orderservice.controller;

import com.oms.orderservice.dto.OrderDto;
import com.oms.orderservice.service.IOrderService;
import com.oms.orderservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*") // CORS resolution **not recommended for production
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
@RestController
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderRequest) {
        return ResponseEntity.ok(
                ApiResponse.success("Order created!", orderService.create(orderRequest))
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> fetchOrderById(Long orderId) {
        return ResponseEntity.ok(
                ApiResponse.success("Order", orderService.fetchById(orderId))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> fetchAllOrders() {
        return ResponseEntity.ok(
                ApiResponse.success("Order list", orderService.fetchAll())
        );
    }

    @PatchMapping("/{orderId}/orderStatuses/{orderStatusId}")
    public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderId, @PathVariable Long orderStatusId) {
        return ResponseEntity.ok(
                ApiResponse.success("Order updated!", orderService.updateOrderStatus(orderId, orderStatusId))
        );
    }

}
