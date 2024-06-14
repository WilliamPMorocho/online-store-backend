package com.unir.orders.service.concrete;

import com.unir.orders.data.OrderDetailRepository;
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
    private OrderService orderService;

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
            Product product  =  obtenerProducto(orderDetailRequest.getProductId());
                if(product !=null){
                    orderDetail.setProductId(product.getProductId());
                    orderDetail.setQuantity(orderDetailRequest.getQuantity());
                    orderDetail.setPrice(orderDetailRequest.getPrice());
                    orderDetail.setState(orderDetailRequest.getState());
                    orderDetail.setOrder(orderService.getOrderById(orderDetailRequest.getOrderId()));
                        if(product.getStock()>orderDetail.getQuantity()) {
                            disminuirStock(orderDetailRequest.getProductId(), (product.getStock() - orderDetail.getQuantity()));
                        }
                    return repository.save(orderDetail);
                }
        }
        return null;
    }

public Product obtenerProducto(Integer producId){
    return  productsFacade.getProduct(String.valueOf(producId));
}

    public Product disminuirStock(Integer producId,  Integer countStock){
        String body =  "{\"stock\":"+countStock+"}";
        return  productsFacade.patchProduct(String.valueOf(producId), body);
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
            orderDetail.setProductId(orderDetailRequest.getProductId());
            orderDetail.setQuantity(orderDetailRequest.getQuantity());
            orderDetail.setPrice(orderDetailRequest.getPrice());
            orderDetail.setState(orderDetailRequest.getState());
            orderDetail.setOrder(orderService.getOrderById(orderDetailRequest.getOrderId()));
            return repository.save(orderDetail);
        }
        return null;
    }

    @Override
    public Boolean deleteOrderDetail(Long id) {
        OrderDetail orderDetail = repository.findById(id);
        if (orderDetail != null) {
            repository.delete(orderDetail);
            return true;
        }
        return false;
    }
}
