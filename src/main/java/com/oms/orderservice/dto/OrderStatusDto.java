package com.oms.orderservice.dto;

import com.oms.orderservice.model.OrderStatusModel;
import com.oms.orderservice.util.OrderStatusNameEnum;
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
public class OrderStatusDto {

    @Positive
    @NotNull
    private Long id;
    private OrderStatusNameEnum name;
    

    public static OrderStatusDto toDto(OrderStatusModel orderStatusModel) {
        return new OrderStatusDto(
                orderStatusModel.getId(),
                orderStatusModel.getName()
        );
    }

}