package com.example.termProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.termProject.domain.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

	List<Tag> findByTagname (String tagname);

	
	
}
