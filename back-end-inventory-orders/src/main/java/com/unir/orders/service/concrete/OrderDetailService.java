package com.unir.orders.service.concrete;

import com.unir.orders.data.OrderDetailRepository;
import com.unir.orders.data.OrderRepository;
import com.unir.orders.facade.ProductsFacade;
import com.unir.orders.model.Product;
import com.unir.orders.model.pojo.OrderDetail;
import com.unir.orders.model.request.CreateOrderDetailRequest;
import com.unir.orders.model.request.UpdateOrderDetailRequest;
import com.unir.orders.service.logic.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private OrderDetailRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
    private ProductsFacade productsFacade;

    @Override
    public List<OrderDetail> searchOrderDetails(Long orderId, Long productId) {
        return repository.search(orderId, productId);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    @Override
    public OrderDetail findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public OrderDetail createOrderDetail(CreateOrderDetailRequest orderDetailRequest) {
        if (orderDetailRequest != null &&
                orderDetailRequest.getProductId() > 0 &&
                orderDetailRequest.getQuantity() > 0 &&
                orderDetailRequest.getPrice() > 0 &&
                orderDetailRequest.getOrderId() > 0 &&
                orderDetailRequest.getState() > 0
        ) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = getProduct(orderDetailRequest.getProductId());
            if (product != null && product.getStock() > orderDetailRequest.getQuantity()) {
                int newStock = product.getStock() - orderDetailRequest.getQuantity();
                Product productUpdated = updateStock(orderDetailRequest.getProductId(), newStock);
                if (productUpdated != null && productUpdated.getStock() == newStock) {
                    orderDetail.setProductId(product.getProductId());
                    orderDetail.setQuantity(orderDetailRequest.getQuantity());
                    orderDetail.setPrice(orderDetailRequest.getPrice());
                    orderDetail.setState(orderDetailRequest.getState());
                    orderDetail.setOrder(orderRepository.getById(orderDetailRequest.getOrderId()));
                    return repository.save(orderDetail);
                }

            }
        }
        return null;
    }

    public Product getProduct(Integer productId) {
        return productsFacade.getProduct(String.valueOf(productId));
    }

    public Product updateStock(Integer productId, Integer countStock) {
        String body = "{\"stock\":" + countStock + "}";
        return productsFacade.patchProduct(String.valueOf(productId), body).block();
    }

    @Override
    public OrderDetail updateOrderDetail(UpdateOrderDetailRequest orderDetailRequest) {
        if (orderDetailRequest != null &&
                orderDetailRequest.getId() > 0 &&
                orderDetailRequest.getProductId() > 0 &&
                orderDetailRequest.getQuantity() > 0 &&
                orderDetailRequest.getPrice() > 0 &&
                orderDetailRequest.getOrderId() > 0 &&
                orderDetailRequest.getState() > 0) {
            OrderDetail orderDetail = repository.findById(orderDetailRequest.getId());
            if (orderDetail != null) {
                int orderQuantity = orderDetail.getQuantity();
                int requestQuantity = orderDetailRequest.getQuantity();
                if (orderQuantity != requestQuantity) {
                    Product product = getProduct(orderDetailRequest.getProductId());
                    if (orderQuantity < requestQuantity) {
                        int newStock = product.getStock() - (requestQuantity - orderQuantity);
                        if (newStock >= 0) {
                            updateStock(product.getProductId(), newStock);
                            orderDetail.setQuantity(requestQuantity);
                        }
                    } else {
                        int newStock = product.getStock() + (orderQuantity - requestQuantity);
                        updateStock(product.getProductId(), newStock);
                        orderDetail.setQuantity(requestQuantity);
                    }
                }
                orderDetail.setProductId(orderDetailRequest.getProductId());
                orderDetail.setPrice(orderDetailRequest.getPrice());
                orderDetail.setState(orderDetailRequest.getState());
                orderDetail.setOrder(orderRepository.getById(orderDetailRequest.getOrderId()));
                return repository.save(orderDetail);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteOrderDetail(Long id) {
        OrderDetail orderDetail = repository.findById(id);
        if (orderDetail != null) {
            Product product = getProduct(orderDetail.getProductId());
            int newStock = product.getStock() + orderDetail.getQuantity();
            updateStock(product.getProductId(), newStock);
            repository.delete(orderDetail);
            return true;
        }
        return false;
    }
}
