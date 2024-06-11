package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import lombok.*;

import java.text.DecimalFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductOrderDto {

	private int productOrderId;
	private int userId;
	private String name;
	private Date date;
	private String address;
	private String email;
	private String phone ;
	private DecimalFormat total;
	private int state;

}
