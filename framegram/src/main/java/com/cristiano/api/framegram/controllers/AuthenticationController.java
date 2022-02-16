package com.cristiano.api.framegram.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristiano.api.framegram.configs.security.TokenService;
import com.cristiano.api.framegram.dtos.TokenDto;
import com.cristiano.api.framegram.forms.LoginForm;
import com.cristiano.api.framegram.models.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken loginData = form.convert();
		try {
			Authentication authentication = authenticationManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			var user = (User) authentication.getPrincipal();
			return ResponseEntity.ok(new TokenDto(token, "Bearer", user.getId()));	
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
