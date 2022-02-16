package com.cristiano.api.framegram.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristiano.api.framegram.assembler.AlbumAssembler;
import com.cristiano.api.framegram.assembler.PostAssembler;
import com.cristiano.api.framegram.assembler.UserAssembler;
import com.cristiano.api.framegram.dtos.AlbumCreateDto;
import com.cristiano.api.framegram.dtos.AlbumDto;
import com.cristiano.api.framegram.dtos.AlbumUpdateDto;
import com.cristiano.api.framegram.models.Album;
import com.cristiano.api.framegram.models.Post;
import com.cristiano.api.framegram.models.User;
import com.cristiano.api.framegram.repositories.AlbumRepository;

@Service
public class AlbumService {

	
	private final AlbumRepository albumRepository;
	private final AlbumAssembler albumAssembler;
	private final UserService userService;
	private final UserAssembler userAssembler;
	private final PostService postService;
	private final PostAssembler postAssembler;
	
	public AlbumService(AlbumRepository albumRepository,
			AlbumAssembler albumAssembler,
			UserService userService,
			UserAssembler userAssembler,
			PostService postService,
			PostAssembler postAssembler) {
		this.albumRepository = albumRepository;
		this.albumAssembler = albumAssembler;
		this.userService = userService;
		this.userAssembler = userAssembler;
		this.postService = postService;
		this.postAssembler = postAssembler;
	}

	@Transactional
	public AlbumDto save(AlbumCreateDto albumCreateDto) {
		var loggedUser = userService.getLoggedUser();
		var user = new User();
		user = userAssembler.toEntity(loggedUser);
		var album = albumAssembler.toEntity(albumCreateDto);
		album.setUser(user);
		return albumAssembler.toDto(albumRepository.save(album));
	}
	
	public Page<AlbumDto> findAll(Pageable pageable) {
		Page<Album> lista = albumRepository.findAll(pageable);
		return albumAssembler.toPageDto(lista);
	}

	public AlbumDto findById(UUID id) {
		Optional<Album> albumOptional = albumRepository.findById(id);
		if(!albumOptional.isPresent()) {
			throw new RuntimeException("Album not found");
		}
		return albumAssembler.toDto(albumOptional.get());
	}
	
	@Transactional
	public AlbumDto update(UUID id, AlbumUpdateDto albumUpdateDto) {
		Optional<Album> albumOptional = albumRepository.findById(id);
		if(!albumOptional.isPresent()) {
			throw new RuntimeException("Album not found");			
		}
		albumOptional.get().setDescription(albumUpdateDto.getDescription());
		return albumAssembler.toDto(albumRepository.save(albumOptional.get()));
	}

	@Transactional
	public void delete(UUID id) {
		Optional<Album> albumOptional = albumRepository.findById(id);
		if(!albumOptional.isPresent()) {
			throw new RuntimeException("Album not found");
		}
		var album = albumOptional.get();
		for (Post post: album.getPosts()) {
			post.getAlbums().remove(album);
		}
		albumRepository.delete(albumOptional.get());
	}

	public AlbumDto addPost(UUID albumId, UUID postId) {
		var albumOptional = albumRepository.findById(albumId);
		if(!albumOptional.isPresent()) {
			throw new RuntimeException("Album not found!");
		}
		var postDto = postService.findById(postId);
		var post = postAssembler.toEntity(postDto);
		albumOptional.get().getPosts().add(post);
		return albumAssembler.toDto(albumRepository.save(albumOptional.get()));
	}
	
}
