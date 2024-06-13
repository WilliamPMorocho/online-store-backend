package com.unir.orders.service.logic;

import com.unir.orders.model.Product;
import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.model.request.OrderRequest;
import com.unir.orders.model.request.UpdateOrderRequest;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();

    Order getOrderById(Long id);

    Boolean deleteOrder(Long id);

    Order createOrder(CreateOrderRequest orderRequest);

    Order updateOrder(UpdateOrderRequest orderRequest);

    List<Order> searchOrders(Integer userId, Integer state);
}