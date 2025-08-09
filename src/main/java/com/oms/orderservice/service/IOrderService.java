package com.oms.orderservice.service;

import com.oms.orderservice.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto create(OrderDto orderRequest);
    OrderDto fetchById(Long orderId);
    List<OrderDto> fetchAll();
    OrderDto updateStatus(Long orderId, Long orderStatusId);

}