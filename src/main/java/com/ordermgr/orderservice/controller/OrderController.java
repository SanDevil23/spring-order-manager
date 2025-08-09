package com.ordermgr.orderservice.controller;

import com.ordermgr.orderservice.dto.OrderDto;
import com.ordermgr.orderservice.service.IOrderService;
import com.ordermgr.orderservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")   // CORS resolution **not recommended for production
@Slf4j
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
@RestController
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderRequest) {
        try {
            OrderDto orderResponse = orderService.create(orderRequest);
            return ResponseEntity.ok(ApiResponse.success("Order Created Successfully", orderResponse));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Order creation failed"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> fetchAllOrders() {
        try {
            List<OrderDto> orderResponse = orderService.fetchAll();
            return ResponseEntity.ok(ApiResponse.success("Order List", orderResponse));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to fetch orders"));
        }
    }

    @PatchMapping("/{orderId}/statuses/{statusId}")
    public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderId, @PathVariable Long statusId) {
        try {
            return ResponseEntity.ok().body(ApiResponse.success("Order updated", orderService.updateStatus(orderId, statusId)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to Update Order Status"));
        }
    }

}
