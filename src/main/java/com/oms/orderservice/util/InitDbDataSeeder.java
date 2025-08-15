package com.oms.orderservice.util;

import com.oms.orderservice.dao.OrderStatusRepository;
import com.oms.orderservice.model.OrderStatusModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Component
public class InitDbDataSeeder {

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public InitDbDataSeeder(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @PostConstruct
    public void init() {
        initOrderStatus();
    }

    private void initOrderStatus() {
        if (orderStatusRepository.count() > 0) return;
        orderStatusRepository.saveAll(
                Arrays.stream(OrderStatusNameEnum.values())
                        .map(orderStatusNameEnum -> {
                                    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
                                    return OrderStatusModel.builder()
                                            .name(orderStatusNameEnum)
                                            .createdAt(now)
                                            .updatedAt(now)
                                            .build();
                                }
                        )
                        .toList()
        );
    }

}