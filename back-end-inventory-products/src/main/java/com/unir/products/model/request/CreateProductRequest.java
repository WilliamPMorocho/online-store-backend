package com.unir.products.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

	private String name;
	private String description;
	private double price ;
	private int rating;
	private int categoryId;
	private String images;
	private int state;
	private Integer stock;
}
