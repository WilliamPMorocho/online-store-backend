package com.unir.orders.data;

import com.unir.orders.data.utils.SearchCriteria;
import com.unir.orders.data.utils.SearchOperation;
import com.unir.orders.data.utils.SearchStatement;
import com.unir.orders.model.pojo.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailRepository {
    private final OrderDetailJpaRepository repository;

    public OrderDetail findById(final Long id) {
        return repository.findById(id).orElse(null);
    }

    public OrderDetail save(final OrderDetail orderDetail) {
        return repository.save(orderDetail);
    }

    public void delete(final OrderDetail orderDetail) {
        repository.delete(orderDetail);
    }

    public List<OrderDetail> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    public List<OrderDetail> search(Long orderId, Long productId) {
        SearchCriteria<OrderDetail> spec = new SearchCriteria<>();
        if (orderId != null) {
            spec.add(new SearchStatement("orderId", orderId, SearchOperation.EQUAL));
        }
        if (productId != null) {
            spec.add(new SearchStatement("productId", productId, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }
}
