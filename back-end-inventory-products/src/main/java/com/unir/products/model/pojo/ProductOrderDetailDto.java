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

	private int productOrderDetailId;
	private int productOrderId;
	private int productId ;
	private int state;

}
