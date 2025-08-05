package com.order_mgr.order_service.service;

import com.order_mgr.order_service.model.Order;
import com.order_mgr.order_service.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(Order order){
        orderRepository.save(order);
        return;
    }

    // TODO: code logic yet to be written
//    public Order fetchOrderById(int orderId){
//        Optional<Order> order =  orderRepository.findById(orderId);
//        return order;
//    }

    public List<Order> fetchAllOrders(){
        return orderRepository.findAll();
    }
}
