package com.order_mgr.order_service.model;

import com.order_mgr.order_service.utils.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderMgr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    private int userID;

    @Enumerated(EnumType.STRING)        // mapping enum into varchar
    private OrderStatus status;

    private Date date;
}
