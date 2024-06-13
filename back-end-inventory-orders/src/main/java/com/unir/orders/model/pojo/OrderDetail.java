package com.unir.orders.model.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    @JsonIgnore
    private Order order;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "state")
    private int state;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;
}
