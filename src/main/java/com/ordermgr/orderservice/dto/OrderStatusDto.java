package com.ordermgr.orderservice.dto;

import com.ordermgr.orderservice.model.OrderStatusModel;
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

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderStatusDto toDto(OrderStatusModel orderStatusModel) {
        return new OrderStatusDto(
                orderStatusModel.getId(),
                orderStatusModel.getName().name(),
                orderStatusModel.getCreatedAt(),
                orderStatusModel.getUpdatedAt()
        );
    }

}