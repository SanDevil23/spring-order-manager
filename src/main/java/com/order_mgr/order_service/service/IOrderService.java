package com.order_mgr.order_service.service;

import com.order_mgr.order_service.model.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(Order order);
    List<Order> fetchAllOrders();
}
