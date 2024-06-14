package com.unir.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
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
