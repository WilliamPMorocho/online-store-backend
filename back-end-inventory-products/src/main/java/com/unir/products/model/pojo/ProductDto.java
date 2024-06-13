package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {
	private int productId;
	private String name;
	private String description;
	private double price ;
	private int rating;
	private int categoryId;
	private String images;
	private int state;
	private Integer stock;
}
