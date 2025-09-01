package com.order_mgr.order_service.utils;

import com.order_mgr.order_service.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

//@AllArgsConstructor
@Data
@Schema(description = "Generic API response wrapper")
public class ApiResponse<T> {

    @Schema(description = "Message describing the result")
    private String message;

    @Schema(description = "Payload of the response", nullable = true)
    private Order data;

    public ApiResponse() {}

    public ApiResponse(String message, Order data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message) {
        this.message = message;
        this.data = null;
    }

    // getters and setters
}

