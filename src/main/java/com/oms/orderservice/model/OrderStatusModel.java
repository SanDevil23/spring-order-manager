package com.oms.orderservice.model;

import com.oms.orderservice.util.OrderStatusNameEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_status")
@Entity
public class OrderStatusModel {

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_status_id_sequence_generator"
    )
    @SequenceGenerator(
            name = "order_status_id_sequence_generator",
            sequenceName = "order_status_id_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatusNameEnum name;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY)
    private Set<OrderModel> orders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}