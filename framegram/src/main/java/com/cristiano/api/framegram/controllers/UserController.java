package com.cristiano.api.framegram.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristiano.api.framegram.dtos.UserDto;
import com.cristiano.api.framegram.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
		return  ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
	}
	
}
