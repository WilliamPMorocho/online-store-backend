package com.unir.products.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "product_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_order_id", unique = true)
	private int productOrderId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "name")
	private String name;

	@Column(name = "date")
	private Date date;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone ;
	
	@Column(name = "total")
	private DecimalFormat total;

	@Column(name = "state")
	private int state;

	public void update(ProductOrderDto productDto) {
		this.productOrderId = productDto.getProductOrderId();
		this.userId = productDto.getUserId();
		this.name = productDto.getName();
		this.date = productDto.getDate();
		this.address = productDto.getAddress();
		this.email = productDto.getEmail();
		this.phone = productDto.getPhone();
		this.total = productDto.getTotal();
		this.state = productDto.getState();
	}

}
