package com.example.termProject.service.tag;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.tag.TagSaveDto;
import com.example.termProject.domain.entity.Tag;
import com.example.termProject.repository.TagRepository;

@Service
public class SaveTagService {

	final private TagRepository tagRepository;
	
	public SaveTagService (TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	public void saveTag(TagSaveDto tagSaveDto) {
		Tag tag = new Tag();
		LocalDate regDate = LocalDate.now();
		
		tag.setTagname(tagSaveDto.getTagname());
		tag.setRegDate(regDate);
		tag.setCno(tagSaveDto.getCno());
		
		tagRepository.save(tag);
	}
}
