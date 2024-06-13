package com.unir.orders.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private int userId;
    private String name;
    private Date date;
    private String address;
    private String email;
    private String phone;
    private Double total;
    private int state;
    private List<CreateOrderDetailRequest> products;
}
