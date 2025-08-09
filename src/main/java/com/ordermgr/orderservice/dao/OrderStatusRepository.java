package com.ordermgr.orderservice.dao;

import com.ordermgr.orderservice.model.OrderStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusModel, Long> {
}