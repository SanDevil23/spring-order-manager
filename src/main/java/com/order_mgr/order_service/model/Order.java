package com.order_mgr.order_service.model;

import com.order_mgr.order_service.utils.OrderStatus;
//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderMgr")
//@Schema(description = "Order entity representing a customer's order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Schema(description = "Unique identifier for the order", example = "101")
    private int orderID;

//    @Schema(description = "ID of the user who placed the order", example = "501")
    private int userID;

    @Enumerated(EnumType.STRING)
//    @Schema(description = "Current status of the order", example = "PENDING")
    private OrderStatus status;

//    @Schema(description = "Timestamp when the order was placed", example = "2025-08-28T14:30:00")
    private LocalDateTime date;
}
