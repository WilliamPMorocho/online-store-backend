package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {

	private int categoryId;
	private String name;
}
