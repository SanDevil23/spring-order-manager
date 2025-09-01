package com.order_mgr.order_service.utils;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Order status")
public enum OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
