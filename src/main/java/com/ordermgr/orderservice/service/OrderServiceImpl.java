package com.ordermgr.orderservice.service;

import com.ordermgr.orderservice.dao.OrderStatusRepository;
import com.ordermgr.orderservice.dto.OrderDto;
import com.ordermgr.orderservice.model.OrderModel;
import com.ordermgr.orderservice.dao.OrderRepository;
import com.ordermgr.orderservice.model.OrderStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds business logic for order related operations
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    /**
     * Creates a new order
     * @param orderRequest
     */
    @Override
    public OrderDto create(OrderDto orderRequest) {
        OrderStatusModel orderStatus = orderStatusRepository.findById(orderRequest.getStatus().getId()).orElseThrow(() -> new RuntimeException("Order status Not Found"));
        OrderModel orderToCreate = new OrderModel();
        orderToCreate.setUserId(orderRequest.getUserId());
        orderToCreate.setOrderStatus(orderStatus);
        orderToCreate.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        orderToCreate.setUpdatedAt(orderToCreate.getCreatedAt());
        OrderModel createdOrder = orderRepository.save(orderToCreate);
        return OrderDto.toDto(createdOrder);
    }

    /**
     * Fetches order by its id i.e. identifier in DB
     * @param orderId
     * @return OrderDto
     */
    @Override
    public OrderDto fetchById(Long orderId) {
        OrderModel fetchedOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
        return OrderDto.toDto(fetchedOrder);
    }

    /**
     * Fetches the list of all orders
     * @return OrderDto
     */
    @Override
    public List<OrderDto> fetchAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDto::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates order status
     * @param orderId
     * @param orderStatusId
     * @return OrderDto
     */
    @Override
    public OrderDto updateStatus(Long orderId, Long orderStatusId) {
        OrderStatusModel orderStatus = orderStatusRepository.findById(orderStatusId).orElseThrow(() -> new RuntimeException("Order Status Not Found"));
        OrderModel fetchedOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
        fetchedOrder.setOrderStatus(orderStatus);
        fetchedOrder.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        OrderModel updatedOrder = orderRepository.save(fetchedOrder);
        return OrderDto.toDto(updatedOrder);
    }

}
