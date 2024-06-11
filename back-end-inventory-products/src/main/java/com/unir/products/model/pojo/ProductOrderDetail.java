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
	private long productOrderDetailId;
	
	@Column(name = "product_order_id")
	private long productOrderId;
	
	@Column(name = "product_id")
	private long productId ;
	
	@Column(name = "product_status")
	private int productStatus;



	public void update(ProductOrderDetailDto productOrderDetailDto) {
		this.productOrderDetailId = productOrderDetailDto.getProductOrderDetailId();
		this.productOrderId = productOrderDetailDto.getProductOrderId();
		this.productId = productOrderDetailDto.getProductId();
		this.productStatus = productOrderDetailDto.getProductStatus();

	}

}
