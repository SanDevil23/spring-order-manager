package com.oms.orderservice.controller;

import com.oms.orderservice.dto.OrderDto;
import com.oms.orderservice.exception.BusinessException;
import com.oms.orderservice.service.IOrderService;
import com.oms.orderservice.util.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*") // CORS resolution **not recommended for production
@Validated
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
@RestController
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody @Valid OrderDto orderRequest) throws BusinessException {
        return ResponseEntity.ok(
                ApiResponse.success("Order created!", orderService.create(orderRequest))
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> fetchOrderById(@PathVariable @Positive Long orderId) throws BusinessException {
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
    public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable @Positive Long orderId, @PathVariable @Positive Long orderStatusId) throws BusinessException {
        return ResponseEntity.ok(
                ApiResponse.success("Order updated!", orderService.updateOrderStatus(orderId, orderStatusId))
        );
    }

}
