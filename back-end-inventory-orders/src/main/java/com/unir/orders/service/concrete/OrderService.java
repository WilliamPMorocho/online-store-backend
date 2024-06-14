package com.unir.orders.service.concrete;

import com.unir.orders.data.OrderRepository;
import com.unir.orders.model.pojo.DTO.OrderDTO;
import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.pojo.OrderDetail;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.model.request.UpdateOrderRequest;
import com.unir.orders.service.logic.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public List<Order> getOrders() {
        return repository.getOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Boolean deleteOrder(Long id) {
        Order order = repository.getById(id);
        if (order != null) {
            for (OrderDetail orderDetail : order.getProducts())
                orderDetailService.deleteOrderDetail(orderDetail.getId());
            repository.delete(order);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Order createOrder(CreateOrderRequest orderRequest) {
        if (orderRequest != null &&
                StringUtils.hasLength(orderRequest.getName().trim()) &&
                orderRequest.getDate() != null &&
                StringUtils.hasLength(orderRequest.getAddress().trim()) &&
                StringUtils.hasLength(orderRequest.getEmail().trim()) &&
                StringUtils.hasLength(orderRequest.getPhone().trim()) &&
                orderRequest.getTotal() != null && orderRequest.getTotal() > 0 &&
                orderRequest.getProducts() != null && !orderRequest.getProducts().isEmpty()) {

            Order order = new Order();
            order.setUserId(orderRequest.getUserId());
            order.setName(orderRequest.getName());
            order.setDate(orderRequest.getDate());
            order.setAddress(orderRequest.getAddress());
            order.setEmail(orderRequest.getEmail());
            order.setPhone(orderRequest.getPhone());
            order.setTotal(orderRequest.getTotal());
            order.setState(orderRequest.getState());
            order = repository.save(order);
            Long orderId = order.getId();
            List<OrderDetail> details = orderRequest.getProducts().stream().map(detailRequest -> {
                detailRequest.setOrderId(orderId);
                return orderDetailService.createOrderDetail(detailRequest);
            }).filter(Objects::nonNull).collect(Collectors.toList());
            order.setProducts(details);
            return order;
        } else {
            return null;
        }
    }

    @Override
    public Order updateOrder(UpdateOrderRequest orderRequest) {
        if (orderRequest != null &&
                orderRequest.getId() > 0 &&
                orderRequest.getUserId() > 0 &&
                StringUtils.hasLength(orderRequest.getName().trim()) &&
                orderRequest.getDate() != null &&
                StringUtils.hasLength(orderRequest.getAddress().trim()) &&
                StringUtils.hasLength(orderRequest.getEmail().trim()) &&
                StringUtils.hasLength(orderRequest.getPhone().trim()) &&
                orderRequest.getTotal() != null && orderRequest.getTotal() > 0) {
            Order order = repository.getById(orderRequest.getId());
            if (order != null) {
                order.setName(orderRequest.getName());
                order.setUserId(orderRequest.getUserId());
                order.setDate(orderRequest.getDate());
                order.setAddress(orderRequest.getAddress());
                order.setEmail(orderRequest.getEmail());
                order.setPhone(orderRequest.getPhone());
                order.setTotal(orderRequest.getTotal());
                order.setState(orderRequest.getState());
                return repository.save(order);
            }
        }
        return null;
    }

    @Override
    public List<Order> searchOrders(Integer userId, Integer state) {
        return repository.search(userId, state);
    }
}
