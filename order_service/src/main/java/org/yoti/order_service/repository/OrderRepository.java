package org.yoti.order_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.yoti.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
