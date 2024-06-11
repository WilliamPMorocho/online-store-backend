package com.unir.products.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoleDto {

	private Long roleId;
	private String name;
}
