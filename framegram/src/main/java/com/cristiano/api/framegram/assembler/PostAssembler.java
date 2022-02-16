package com.cristiano.api.framegram.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.cristiano.api.framegram.dtos.PostCreateDto;
import com.cristiano.api.framegram.dtos.PostDto;
import com.cristiano.api.framegram.dtos.PostUpdateDto;
import com.cristiano.api.framegram.models.Post;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PostAssembler {

	private ModelMapper modelMapper;
	
	public PostDto toDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}
	
	public Page<PostDto> toPageDto(Page<Post> posts) {
		return posts.map(this::toDto);
	}
	
	public Post toEntity(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}
	
	public Post toEntity(PostCreateDto postCreateDto) {
		return modelMapper.map(postCreateDto, Post.class);
	}
	
	public Post toEntity(PostUpdateDto postUpdateDto) {
		return modelMapper.map(postUpdateDto, Post.class);
	}
}
