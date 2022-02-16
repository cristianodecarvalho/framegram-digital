package com.cristiano.api.framegram.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cristiano.api.framegram.configs.security.AuthenticationByTokenFilter;
import com.cristiano.api.framegram.dtos.UserDto;
import com.cristiano.api.framegram.dtos.UserSimpleDto;
import com.cristiano.api.framegram.models.User;
import com.cristiano.api.framegram.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private final AuthenticationByTokenFilter authenticationByTokenFilter;
	
	public UserService(UserRepository userRepository, AuthenticationByTokenFilter authenticationByTokenFilter) {
		this.userRepository = userRepository;
		this.authenticationByTokenFilter = authenticationByTokenFilter;
	}

	public UserDto save(UserDto userDto) {
		var user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		var savedUser = userRepository.save(user);
		var savedUserDto = new UserDto();
		BeanUtils.copyProperties(savedUser, savedUserDto);
		return savedUserDto;
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public UserSimpleDto getLoggedUser() {
		User user = authenticationByTokenFilter.getLoggedUser();
		UserSimpleDto userSimpleDto = new UserSimpleDto();
		userSimpleDto.setId(user.getId());
		userSimpleDto.setName(user.getName());
		userSimpleDto.setLogin(user.getLogin());
		return userSimpleDto;
	}
	
}
