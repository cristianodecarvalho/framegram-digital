package com.cristiano.api.framegram.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {
	
	private String message;
	private PostIdDto post;
	private AlbumIdDto album;
	
}
