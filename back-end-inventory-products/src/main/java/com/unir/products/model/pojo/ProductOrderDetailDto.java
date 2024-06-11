package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductOrderDetailDto {

	private long productOrderDetailId;
	private long productOrderId;
	private long productId ;
	private int productStatus;

}
