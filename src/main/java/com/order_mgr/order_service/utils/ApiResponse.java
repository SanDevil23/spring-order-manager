package com.order_mgr.order_service.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;
}
