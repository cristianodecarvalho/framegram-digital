package com.cristiano.api.framegram.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cristiano.api.framegram.models.Post;

public interface PostRepository extends JpaRepository<Post, UUID>{

	Page<Post> findByAlbumsId(UUID albumId, Pageable pageable);
	
	Page<Post> findByUserId(Long userId, Pageable pageable);
	
}
