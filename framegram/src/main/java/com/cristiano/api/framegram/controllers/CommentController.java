package com.cristiano.api.framegram.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristiano.api.framegram.dtos.CommentCreateDto;
import com.cristiano.api.framegram.dtos.CommentDto;
import com.cristiano.api.framegram.dtos.CommentUpdateDto;
import com.cristiano.api.framegram.services.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody CommentCreateDto commentCreateDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(commentCreateDto));
	}
	
	@GetMapping
	public ResponseEntity<Page<CommentDto>> getAll(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable UUID id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(commentService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error searching comment.");
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody CommentUpdateDto commentUpdateDto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(commentService.update(id, commentUpdateDto));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error updating comment.");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(commentService.delete(id));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error deleting comment.");
		}
	}
	
	
	
}
