package com.unir.orders.data;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {
    List<OrderDetail> findByOrderId(Long orderId);

    List<OrderDetail> findByProductId(Long productId);
}
