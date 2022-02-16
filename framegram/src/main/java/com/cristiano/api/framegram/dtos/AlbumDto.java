package com.cristiano.api.framegram.dtos;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumDto {
	
	private UUID id;
	private String description;
	private UserDto user;
	private List<PostDto> posts;
	private List<CommentSimpleDto> comments;
	private Timestamp createdAt;	
	private Timestamp updatedAt;

}
