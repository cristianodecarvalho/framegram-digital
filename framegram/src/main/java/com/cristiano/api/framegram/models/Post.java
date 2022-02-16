package com.cristiano.api.framegram.models;

import static java.util.Objects.isNull;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_POST")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String image;
	private String description;
	private String link;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(orphanRemoval = true, mappedBy = "post")
	private List<Comment> comments;
	
	@ManyToMany(mappedBy = "posts")
	private List<Album> albums;
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	@PrePersist
	@PreUpdate
	private void validateRequiredFields() {
		if(isNull(this.image) || isNull(this.description) || isNull(link)) {
			throw new RuntimeException("Required fields not filled!");
		}
	}

}
