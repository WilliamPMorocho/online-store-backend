package com.unir.products.model.pojo;

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
public class ProductOrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_order_detail_id", unique = true)
	private int productOrderDetailId;
	
	@Column(name = "product_order_id")
	private int productOrderId;
	
	@Column(name = "product_id")
	private int productId ;
	
	@Column(name = "state")
	private int state;



	public void update(ProductOrderDetailDto productOrderDetailDto) {
		this.productOrderDetailId = productOrderDetailDto.getProductOrderDetailId();
		this.productOrderId = productOrderDetailDto.getProductOrderId();
		this.productId = productOrderDetailDto.getProductId();
		this.state = productOrderDetailDto.getState();

	}

}
