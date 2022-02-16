package com.cristiano.api.framegram.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSimpleDto {

	private UUID id;
	private String message;	
	private UserSimpleDto user;
}
