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

import com.cristiano.api.framegram.dtos.AlbumCreateDto;
import com.cristiano.api.framegram.dtos.AlbumDto;
import com.cristiano.api.framegram.dtos.AlbumUpdateDto;
import com.cristiano.api.framegram.services.AlbumService;

@RestController
@RequestMapping("album")
public class AlbumController {

	private final AlbumService albumService;
	
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody AlbumCreateDto albumCreateDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(albumService.save(albumCreateDto));
	}
	
	@GetMapping
	public ResponseEntity<Page<AlbumDto>> getAll(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(albumService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@PathVariable UUID id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(albumService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error searching album.");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody AlbumUpdateDto albumUpdateDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(albumService.update(id, albumUpdateDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error updating album.");
		}
	}
	
	@PutMapping("/{albumId}/post/{postId}")
	public ResponseEntity<Object> addPost(@PathVariable UUID albumId, @PathVariable UUID postId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(albumService.addPost(albumId, postId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error updating album.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id) {
		try {
			albumService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an error deleting album.");
		}
	}
}
