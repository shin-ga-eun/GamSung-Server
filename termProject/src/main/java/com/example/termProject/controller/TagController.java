package com.example.termProject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.termProject.domain.dto.tag.GetTagDto;
import com.example.termProject.service.tag.GetTagService;

@RestController
public class TagController {

	final private GetTagService getTagService;
	
	public TagController(GetTagService getTagService) {
		this.getTagService = getTagService;
	}
	
	//인기있는 탭 리스트
	@RequestMapping(value = "/getPopular", method = RequestMethod.GET)
	public List<GetTagDto> getPopular(){
		return getTagService.getPopularTag();
	}
	
	//새로운 탭 리스트
	@RequestMapping(value = "/getNew", method = RequestMethod.GET)
	public List<GetTagDto> getNew(){
		return getTagService.getNewTag();
	}
}
