package com.order_mgr.order_service.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse {

    private Integer status;
    private String message;
    private Object data;

    private ApiResponse() {}

    private ApiResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    private ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    private ApiResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public static ApiResponse success(int status, String message, Object data) {
       return new ApiResponse()
               .setStatus(status)
               .setMessage(message)
               .setData(data);
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse()
                .setMessage(message)
                .setData(data);
    }

    public static ApiResponse success(String message) {
        return new ApiResponse()
                .setMessage(message);
    }

    public static ApiResponse error(int status, String message) {
        return new ApiResponse()
                .setStatus(status)
                .setMessage(message);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse()
                .setMessage(message);
    }

}
