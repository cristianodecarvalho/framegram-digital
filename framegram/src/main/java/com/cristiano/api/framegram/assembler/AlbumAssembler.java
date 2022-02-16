package com.cristiano.api.framegram.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.cristiano.api.framegram.dtos.AlbumCreateDto;
import com.cristiano.api.framegram.dtos.AlbumDto;
import com.cristiano.api.framegram.dtos.AlbumUpdateDto;
import com.cristiano.api.framegram.models.Album;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AlbumAssembler {

	private ModelMapper modelMapper;
	
	public AlbumDto toDto(Album Album) {
		return modelMapper.map(Album, AlbumDto.class);
	}
	
	public Page<AlbumDto> toPageDto(Page<Album> albums) {
		return albums.map(this::toDto);
	}
	
	public Album toEntity(AlbumDto albumDto) {
		return modelMapper.map(albumDto, Album.class);
	}
	
	public Album toEntity(AlbumCreateDto albumCreateDto) {
		return modelMapper.map(albumCreateDto, Album.class);
	}
	
	public Album toEntity(AlbumUpdateDto albumUpdateDto) {
		return modelMapper.map(albumUpdateDto, Album.class);
	}
}
