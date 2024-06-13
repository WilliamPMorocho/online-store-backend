package com.unir.orders.model.pojo.DTO;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.pojo.OrderDetail;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private int userId;
    private String name;
    private Date date;
    private String address;
    private String email;
    private String phone;
    private Double total;
    private int state;
    private List<OrderDetailDTO> products;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.name = order.getName();
        this.date = order.getDate();
        this.address = order.getAddress();
        this.email = order.getEmail();
        this.phone = order.getPhone();
        this.total = order.getTotal();
        this.state = order.getState();
        this.products = new ArrayList<>();
        for (OrderDetail orderDetail : order.getProducts()) {
            this.products.add(new OrderDetailDTO(orderDetail));
        }
    }
}
