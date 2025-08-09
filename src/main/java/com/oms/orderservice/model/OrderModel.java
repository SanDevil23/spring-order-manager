package com.oms.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatusModel orderStatus;

    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}