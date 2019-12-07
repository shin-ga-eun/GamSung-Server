package com.example.termProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.termProject.domain.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
