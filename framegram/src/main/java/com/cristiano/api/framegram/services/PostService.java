package com.cristiano.api.framegram.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cristiano.api.framegram.assembler.PostAssembler;
import com.cristiano.api.framegram.assembler.UserAssembler;
import com.cristiano.api.framegram.dtos.PostCreateDto;
import com.cristiano.api.framegram.dtos.PostDto;
import com.cristiano.api.framegram.dtos.PostUpdateDto;
import com.cristiano.api.framegram.models.Album;
import com.cristiano.api.framegram.models.Post;
import com.cristiano.api.framegram.models.User;
import com.cristiano.api.framegram.repositories.PostRepository;

@Service
public class PostService {

//	private static String imagePath = "./";
	
	private final PostRepository postRepository;
	private final PostAssembler postAssembler;
	private final UserService userService;
	private final UserAssembler userAssembler;

	public PostService(PostRepository postRepository,
			PostAssembler postAssembler,
			UserService userService,
			UserAssembler userAssembler) {
		this.postRepository = postRepository;
		this.postAssembler = postAssembler;
		this.userService = userService;
		this.userAssembler = userAssembler;
	}

	@Transactional
	public PostDto save(PostCreateDto postCreateDto, MultipartFile image) {
		var loggedUser = userService.getLoggedUser();
		var user = new User();
		user = userAssembler.toEntity(loggedUser);
		var post = postAssembler.toEntity(postCreateDto);
		String imageName = StringUtils.cleanPath(image.getOriginalFilename());
		post.setImage(imageName);
		post.setUser(user);
		var postSaved = postRepository.save(post); 
		
		try {
			if(image != null) {
				String uploadDir = "src/main/resources/static/images/" + postSaved.getId();
				saveFile(uploadDir, imageName, image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return postAssembler.toDto(postSaved);
	}
	
	public Page<PostDto> findAll(Pageable pageable) {
		return postAssembler.toPageDto(postRepository.findAll(pageable));
	}
	
	public Page<PostDto> findAllMyPosts(Pageable pageable) {
		var loggedUser = userService.getLoggedUser();
		Long userId = loggedUser.getId();
		return postAssembler.toPageDto(postRepository.findByUserId(userId, pageable));
	}

	public PostDto findById(UUID id) {
		 Optional<Post> postOptional = postRepository.findById(id);
		 if(!postOptional.isPresent()) {
			 throw new RuntimeException("Post not found");
		 }
		 var post = postOptional.get();
		 return postAssembler.toDto(post);
	}
	
	@Transactional
	public PostDto update(UUID id, PostUpdateDto postUpdateDto) {
		Optional<Post> postOptional = postRepository.findById(id);
		if(!postOptional.isPresent()) {
			 throw new RuntimeException("Post not found");
		}
		postOptional.get().setImage(postUpdateDto.getImage());
		postOptional.get().setDescription(postUpdateDto.getDescription());
		postOptional.get().setLink(postUpdateDto.getLink());
		return postAssembler.toDto(postRepository.save(postOptional.get()));
	}

	@Transactional
	public void delete(UUID id) {
		Optional<Post> postOptional = postRepository.findById(id);
		if(!postOptional.isPresent()) {
			 throw new RuntimeException("Post not found");
		}
		var post = postOptional.get();
		for (Album album: post.getAlbums()) {
			album.getPosts().remove(post);
		}
		postRepository.delete(postOptional.get());
	}

	public Page<PostDto> getPostsByAlbum(UUID albumId, Pageable pageable) {
		return postAssembler.toPageDto(postRepository.findByAlbumsId(albumId, pageable));
	}
	
	public void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
	
}
