package com.order_mgr.order_service.repo;

import com.order_mgr.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)   // to stop JPA from automatically exposing repository-based endpoints
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
