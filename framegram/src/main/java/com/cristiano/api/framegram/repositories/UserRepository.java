package com.cristiano.api.framegram.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.api.framegram.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByLogin(String login);
	
}
