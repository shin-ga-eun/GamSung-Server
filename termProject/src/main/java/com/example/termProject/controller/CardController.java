package com.example.termProject.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.card.CardSaveDto;
import com.example.termProject.domain.dto.tag.TagSaveDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.service.card.SaveCardService;
import com.example.termProject.service.tag.SaveTagService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CardController {

	final private SaveCardService saveCardService;
	final private SaveTagService saveTagService;
	private ObjectMapper objectMapper;
	
	public CardController (SaveCardService saveCArdService, SaveTagService saveTagService, ObjectMapper objectMapper) {
		this.saveCardService = saveCArdService;
		this.saveTagService = saveTagService;
		this.objectMapper = objectMapper;
	}
	
	@RequestMapping(value = "/saveCard", method = RequestMethod.POST)
	public Long saveCard (@RequestPart MultipartFile imageFile,@RequestParam("cardSaveDto") String json) throws Exception {
		Long cno; //방금 저장한 카드의 넘버 -> 해시저장시 사용할 예정.
		Card card = new Card();
		
		CardSaveDto cardSave = objectMapper.readValue(json, CardSaveDto.class);
		card = saveCardService.saveCardAndFile(cardSave, imageFile);
		cno =  card.getCno();
		
		return cno;
	}
	
	@RequestMapping(value = "/saveTag", method = RequestMethod.POST)
	public void saveTag (@RequestBody TagSaveDto tagSaveDto) {
		saveTagService.saveTag(tagSaveDto);
	}
	
}
