package com.oms.orderservice.service;

import com.oms.orderservice.dto.OrderDto;
import com.oms.orderservice.exception.BusinessException;

import java.util.List;

public interface IOrderService {

    OrderDto create(OrderDto orderRequest) throws BusinessException;
    OrderDto fetchById(Long orderId) throws BusinessException;
    List<OrderDto> fetchAll();
    OrderDto updateOrderStatus(Long orderId, Long orderStatusId) throws BusinessException;

}