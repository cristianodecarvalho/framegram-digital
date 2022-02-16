package com.cristiano.api.framegram.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristiano.api.framegram.models.Album;

public interface AlbumRepository extends JpaRepository<Album, UUID>{
	
	

}
