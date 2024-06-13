package com.unir.orders.service.logic;

import com.unir.orders.model.pojo.OrderDetail;
import com.unir.orders.model.request.CreateOrderDetailRequest;
import com.unir.orders.model.request.UpdateOrderDetailRequest;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> searchOrderDetails(Long orderId, Long productId);

    List<OrderDetail> findByOrderId(Long orderId);

    OrderDetail findById(Long id);

    OrderDetail createOrderDetail(CreateOrderDetailRequest orderDetailRequest);

    OrderDetail updateOrderDetail(UpdateOrderDetailRequest orderDetailRequest);

    Boolean deleteOrderDetail(Long id);
}
