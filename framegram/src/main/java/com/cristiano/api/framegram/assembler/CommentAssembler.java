package com.cristiano.api.framegram.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.cristiano.api.framegram.dtos.CommentCreateDto;
import com.cristiano.api.framegram.dtos.CommentDto;
import com.cristiano.api.framegram.dtos.CommentUpdateDto;
import com.cristiano.api.framegram.models.Comment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CommentAssembler {

	private ModelMapper modelMapper;
	
	public CommentDto toDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}
	
	public Page<CommentDto> toPageDto(Page<Comment> comments) {
		return comments.map(this::toDto);
	}
	
	public Comment toEntity(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}
	
	public Comment toEntity(CommentCreateDto commentCreateDto) {
		return modelMapper.map(commentCreateDto, Comment.class);
	}
	
	public Comment toEntity(CommentUpdateDto commentUpdateDto) {
		return modelMapper.map(commentUpdateDto, Comment.class);
	}
}
