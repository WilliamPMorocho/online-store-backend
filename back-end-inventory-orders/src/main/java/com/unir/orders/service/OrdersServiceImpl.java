package com.unir.orders.service;

import com.unir.orders.data.OrderJpaRepository;
import com.unir.orders.model.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.unir.orders.facade.ProductsFacade;
import com.unir.orders.model.Product;
import com.unir.orders.model.request.OrderRequest;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
    private ProductsFacade productsFacade;

    @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
    private OrderJpaRepository repository;

    @Override
    public Order createOrder(OrderRequest request) {

        return null;
    }

    @Override
    public Order getOrder(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = repository.findAll();
        return orders.isEmpty() ? null : orders;
    }
}
