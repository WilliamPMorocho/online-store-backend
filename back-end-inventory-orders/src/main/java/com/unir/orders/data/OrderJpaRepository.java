package com.unir.orders.data;

import com.unir.orders.model.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findByUserId(Integer userId);

    List<Order> findByState(Integer state);

    List<Order> findByDate(Date date);
}
