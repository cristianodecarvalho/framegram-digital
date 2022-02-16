package com.cristiano.api.framegram.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String login;
	@NotBlank
	private String password;
	
}
