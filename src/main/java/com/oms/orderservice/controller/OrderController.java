package com.oms.orderservice.controller;

import com.oms.orderservice.dto.OrderDto;
import com.oms.orderservice.service.IOrderService;
import com.oms.orderservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") // CORS resolution **not recommended for production
@Slf4j
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
@RestController
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderRequest) {
        OrderDto orderResponse = orderService.create(orderRequest);
        return ResponseEntity.ok(ApiResponse.success("Order Created Successfully", orderResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> fetchAllOrders() {
        List<OrderDto> orderResponse = orderService.fetchAll();
        return ResponseEntity.ok(ApiResponse.success("Order List", orderResponse));
    }

    @PatchMapping("/{orderId}/statuses/{statusId}")
    public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderId, @PathVariable Long statusId) {
        return ResponseEntity.ok().body(ApiResponse.success("Order updated", orderService.updateStatus(orderId, statusId)));
    }

}
