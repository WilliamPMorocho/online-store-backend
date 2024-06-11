package com.unir.products.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true)
	private Long productId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price ;
	
	@Column(name = "rating")
	private Long rating;

	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "images")
	private String images;

	public void update(ProductDto productDto) {
		this.name = productDto.getName();
		this.price = productDto.getPrice();
		this.rating = productDto.getRating();
		this.categoryId = productDto.getCategoryId();
		this.images = productDto.getImages();
	}

}
