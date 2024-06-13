package com.unir.orders.service;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.OrderRequest;
import java.util.List;

public interface OrdersService {
	
	Order createOrder(OrderRequest request);

	Order getOrder(String id);

	List<Order> getOrders();

}
