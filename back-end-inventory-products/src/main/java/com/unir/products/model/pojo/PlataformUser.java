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
@Table(name = "platform_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlataformUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true)
	private long userId;
	
	@Column(name = "rol_id")
	private int rolId;
	
	@Column(name = "password")
	private String password ;


	public void update(PlataformUserDto plataformUserDto) {
		this.userId = plataformUserDto.getUserId();
		this.rolId = plataformUserDto.getRolId();
		this.password = plataformUserDto.getPassword();
	}

}
