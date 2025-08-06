package com.order_mgr.order_service.service;

import com.order_mgr.order_service.model.Order;
import com.order_mgr.order_service.utils.OrderStatus;

import java.util.List;

public interface IOrderService {
    void createOrder(Order order);
    Order fetchOrderById(int orderId);
    List<Order> fetchAllOrders();
    Order updateOrderStatus(int orderID, String status);
}