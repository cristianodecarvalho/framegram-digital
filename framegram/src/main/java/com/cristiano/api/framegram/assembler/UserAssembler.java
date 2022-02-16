package com.cristiano.api.framegram.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cristiano.api.framegram.dtos.UserDto;
import com.cristiano.api.framegram.dtos.UserSimpleDto;
import com.cristiano.api.framegram.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserAssembler {
private ModelMapper modelMapper;
	
	public UserDto toDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	public User toEntity(UserSimpleDto userSimpleDto) {
		return modelMapper.map(userSimpleDto, User.class);
	}
}
