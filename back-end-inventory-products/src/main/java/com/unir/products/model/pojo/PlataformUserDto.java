package com.unir.products.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlataformUserDto {

	private int userId;
	private int rolId;
	private String password ;
	private String userName ;
	private Boolean state ;
}
