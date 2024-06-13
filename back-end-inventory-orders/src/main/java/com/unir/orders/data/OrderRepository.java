package com.unir.orders.data;
import com.unir.orders.data.utils.SearchCriteria;
import com.unir.orders.data.utils.SearchOperation;
import com.unir.orders.data.utils.SearchStatement;
import com.unir.orders.model.pojo.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final OrderJpaRepository repository;

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public void delete(Order order) {
        repository.delete(order);
    }

    public List<Order> search(Integer userId, Integer state) {
        SearchCriteria<Order> spec = new SearchCriteria<>();

        if (userId != null) {
            spec.add(new SearchStatement("userId", userId, SearchOperation.EQUAL));
        }
        if (state != null) {
            spec.add(new SearchStatement("state", state, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }
}
