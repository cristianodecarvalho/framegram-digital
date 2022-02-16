package com.cristiano.api.framegram.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristiano.api.framegram.assembler.CommentAssembler;
import com.cristiano.api.framegram.assembler.UserAssembler;
import com.cristiano.api.framegram.dtos.CommentCreateDto;
import com.cristiano.api.framegram.dtos.CommentDto;
import com.cristiano.api.framegram.dtos.CommentUpdateDto;
import com.cristiano.api.framegram.models.Comment;
import com.cristiano.api.framegram.models.User;
import com.cristiano.api.framegram.repositories.CommentRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final CommentAssembler commentAssembler;
	private final UserService userService;
	private final UserAssembler userAssembler;

	public CommentService(CommentRepository commentRepository,
			CommentAssembler commentAssembler,
			UserService userService,
			UserAssembler userAssembler) {
		this.commentRepository = commentRepository;
		this.commentAssembler = commentAssembler;
		this.userService = userService;
		this.userAssembler = userAssembler;
	}
	
	@Transactional
	public CommentDto save(CommentCreateDto commentCreateDto) {
		var loggedUser = userService.getLoggedUser();
		var user = new User();
		user = userAssembler.toEntity(loggedUser);
		var comment = commentAssembler.toEntity(commentCreateDto);
		comment.setUser(user);
		return commentAssembler.toDto(commentRepository.save(comment));
	}
	
	public Page<CommentDto> findAll(Pageable pageable) {
		return commentAssembler.toPageDto(commentRepository.findAll(pageable));
	}
	
	public CommentDto findById(UUID id) {
		Optional<Comment> comment = commentRepository.findById(id); 
		if(!comment.isPresent()) {
			throw new RuntimeException("Comment not found");	
		}
		return commentAssembler.toDto(comment.get());
	}
	
	@Transactional
	public CommentDto update(UUID id, CommentUpdateDto commentUpdateDto) {
		Optional<Comment> commentOptional = commentRepository.findById(id); 
		if(!commentOptional.isPresent()) {
			throw new RuntimeException("Comment not found");	
		}
		commentOptional.get().setMessage(commentUpdateDto.getMessage());
		return commentAssembler.toDto(commentRepository.save(commentOptional.get()));
	}
	
	@Transactional
	public CommentDto delete(UUID id) {
		Optional<Comment> commentOptional = commentRepository.findById(id); 
		if(!commentOptional.isPresent()) {
			throw new RuntimeException("Comment not found");	
		}
		commentRepository.delete(commentOptional.get());
		return  commentAssembler.toDto(commentOptional.get());
	}
	
}
