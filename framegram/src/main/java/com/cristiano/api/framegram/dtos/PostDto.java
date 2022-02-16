package com.cristiano.api.framegram.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private UUID id;
	private String image;
	private String description;
	private String link;
	private UserSimpleDto user;
	private List<CommentSimpleDto> comments;
	
}