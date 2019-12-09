package com.example.termProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.termProject.domain.dto.tag.GetTagDto;
import com.example.termProject.domain.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

	List<GetTagDto> findByTagname (String tagname);
}
