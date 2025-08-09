package com.ordermgr.orderservice.service;

import com.ordermgr.orderservice.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto create(OrderDto orderRequest);
    OrderDto fetchById(Long orderId);
    List<OrderDto> fetchAll();
    OrderDto updateStatus(Long orderId, Long statusId);

}