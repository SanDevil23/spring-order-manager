package com.oms.orderservice.dto;

import com.oms.orderservice.model.OrderModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @Positive
    @NotNull
    private Long userId;

    @NotNull
    @Valid
    private OrderStatusDto orderStatus;



    public static OrderDto toDto(OrderModel orderModel) {
        return new OrderDto(
                orderModel.getOrderId(),
                orderModel.getUserId(),
                OrderStatusDto.toDto(orderModel.getOrderStatus())
        );
    }

}