package com.order_mgr.order_service.logic;

import com.order_mgr.order_service.model.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(Order order);
    Order fetchOrderById(int orderId);
    List<Order> fetchAllOrders();
    Order updateOrderStatus(int orderID, String status);
}