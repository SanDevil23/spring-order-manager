package com.ordermgr.orderservice.dto;

import com.ordermgr.orderservice.model.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private OrderStatusDto status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderDto toDto(OrderModel orderModel) {
        return new OrderDto(
                orderModel.getOrderId(),
                orderModel.getUserId(),
                OrderStatusDto.toDto(orderModel.getOrderStatus()),
                orderModel.getCreatedAt(),
                orderModel.getUpdatedAt()
        );
    }

}