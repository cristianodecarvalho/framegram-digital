package com.cristiano.api.framegram.configs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cristiano.api.framegram.models.User;
import com.cristiano.api.framegram.repositories.UserRepository;

@Component
public class AuthenticationByTokenFilter extends OncePerRequestFilter{

	private User loggedUser;
	
	private TokenService tokenService;
	private UserRepository userRepository;
	
	public AuthenticationByTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recoverToken(request);
		boolean isValid = tokenService.isValidToken(token);
		if(isValid) {
			authenticateUser(token);
			setLoggedUser(request);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void authenticateUser(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userRepository.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}
	
	private void setLoggedUser(HttpServletRequest request) {
		String token = recoverToken(request);
		Long userId = tokenService.getUserId(token);
		loggedUser = userRepository.findById(userId).get();
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	

}
