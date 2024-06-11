package com.unir.products.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductOrderDetailDto {
	
	private Long productId;
	private String name;
	private double price;
	private Long rating;
	private Long categoryId;
	private String images;

}
