package com.order_mgr.order_service.service;

import com.order_mgr.order_service.model.Order;
import com.order_mgr.order_service.repo.OrderRepository;
import com.order_mgr.order_service.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    // create new orders
    @Override
    public void createOrder(Order order){
        orderRepository.save(order);
        return;
    }

    // fetch Orders by ID
    @Override
    public Order fetchOrderById(int orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    // fetch the list of all orders
    @Override
    public List<Order> fetchAllOrders(){
        return orderRepository.findAll();
    }

    // update the order status
    @Override
    public Order updateOrderStatus(int orderID, String status){
        OrderStatus orderStatus = OrderStatus.valueOf(status);

        // need to take care of the null pointer exception
        Order order = fetchOrderById(orderID);

        if (order == null){
            // define a separate class for error (utils)
            return null;
        }
        order.setStatus(orderStatus);
        return order;
    }

}
