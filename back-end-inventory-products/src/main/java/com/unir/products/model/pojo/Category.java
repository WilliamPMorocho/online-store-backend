package com.unir.products.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", unique = true)
	private int categoryId;
	
	@Column(name = "name")
	private String name;

	public void update(CategoryDto categoryDto) {
		this.categoryId = categoryDto.getCategoryId();
		this.name = categoryDto.getName();
	}

}
