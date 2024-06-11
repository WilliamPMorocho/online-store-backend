package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true)
	private int productId;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price ;
	
	@Column(name = "rating")
	private int rating;

	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "images")
	private String images;

	@Column(name = "state")
	private int state;

	public void update(ProductDto productDto) {
		this.productId = productDto.getProductId();
		this.name = productDto.getName();
		this.description = productDto.getDescription();
		this.price = productDto.getPrice();
		this.rating = productDto.getRating();
		this.categoryId = productDto.getCategoryId();
		this.images = productDto.getImages();
		this.state = productDto.getState();
	}

}
