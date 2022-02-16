package com.cristiano.api.framegram.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cristiano.api.framegram.dtos.PostCreateDto;
import com.cristiano.api.framegram.dtos.PostDto;
import com.cristiano.api.framegram.dtos.PostUpdateDto;
import com.cristiano.api.framegram.services.PostService;

@RestController
@RequestMapping("post")
public class PostController {
	
	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<Object> save(
			@RequestPart(value = "post") PostCreateDto postCreateDto,
			@RequestPart MultipartFile image
			) throws IOException{
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postCreateDto, image));
	}
	
	@GetMapping
	public ResponseEntity<Page<PostDto>> getAll(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(postService.findAll(pageable));
	}
	
	@GetMapping("/my-posts")
	public ResponseEntity<Page<PostDto>> getAllMyPosts(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(postService.findAllMyPosts(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable UUID id){
		try {
			var post = postService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(post);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error searching post.");
		}
	}
	
	@GetMapping("/album/{albumId}")
	public ResponseEntity<Page<PostDto>> getPostsByAlbum(@PathVariable UUID albumId,
			@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable){
		try {
			var post = postService.getPostsByAlbum(albumId, pageable);
			return ResponseEntity.status(HttpStatus.OK).body(post);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id ,@RequestBody PostUpdateDto postUpdateDto){
		try {
			var post = postService.update(id, postUpdateDto);
			return ResponseEntity.status(HttpStatus.OK).body(post);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error updating post.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id){
		try {
			postService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error deleting post.");
		}
	}

}
