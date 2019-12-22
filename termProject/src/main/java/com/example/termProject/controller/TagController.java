package com.example.termProject.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.termProject.domain.dto.card.GetCardByTagDto;
import com.example.termProject.domain.dto.tag.GetTagDto;
import com.example.termProject.service.tag.GetSearchTagService;
import com.example.termProject.service.tag.GetTagService;

@RestController
public class TagController {

	final private GetTagService getTagService;
	final private GetSearchTagService getSearchTagService;
	
	public TagController(GetTagService getTagService, GetSearchTagService getSearchTagService) {
		this.getTagService = getTagService;
		this.getSearchTagService = getSearchTagService;
	}
	
	//메인홈 -인기있는 탭 리스트
	@RequestMapping(value = "/getPopular", method = RequestMethod.GET)
	public List<GetTagDto> getPopular(){
		return getTagService.getPopularTag();
	}
	
	//메인홈 -새로운 탭 리스트x
	@RequestMapping(value = "/getNew", method = RequestMethod.GET)
	public List<GetTagDto> getNew(){
		return getTagService.getNewTag();
	}
	
	//태그검색 리스트 -keyword, 반환값 -해시태그리스트
    @RequestMapping(value = "/getSearchTag/{keyword}", method = RequestMethod.GET)
	public List<GetCardByTagDto> getSearchTag (@PathVariable String keyword) {

   		return getSearchTagService.getSearchTag(keyword);
	}
    
    
}
