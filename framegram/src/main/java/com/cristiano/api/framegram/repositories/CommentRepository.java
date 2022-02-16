package com.cristiano.api.framegram.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristiano.api.framegram.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID>{

}
