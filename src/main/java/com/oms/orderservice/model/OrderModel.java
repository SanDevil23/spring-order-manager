package com.oms.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_order")
@Entity
public class OrderModel {

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "_order_id_sequence_generator"
    )
    @SequenceGenerator(
            name = "_order_id_sequence_generator",
            sequenceName = "_order_id_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    private Long orderId;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatusModel orderStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}