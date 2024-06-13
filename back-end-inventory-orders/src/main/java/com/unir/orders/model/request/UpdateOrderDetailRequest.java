package com.unir.orders.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDetailRequest {
    private Long id;
    private int productId;
    private int state;
    private double price;
    private int quantity;
    private long orderId;
}
