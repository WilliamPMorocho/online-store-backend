package com.unir.orders.model.pojo.DTO;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.pojo.OrderDetail;
import jakarta.persistence.*;

public class OrderDetailDTO {
    private Long id;
    private long orderId;
    private int productId;
    private int state;
    private double price;
    private int quantity;

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrder().getId();
        this.productId = orderDetail.getProductId();
        this.state = orderDetail.getState();
        this.price = orderDetail.getPrice();
        this.quantity = orderDetail.getQuantity();
    }
}
