package com.example.termProject.controller;

import org.springframework.http.MediaType;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.card.CardSaveDto;
import com.example.termProject.domain.dto.card.GetCardByTagDto;
import com.example.termProject.domain.dto.tag.TagSaveDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.service.card.GetCardService;
import com.example.termProject.service.card.SaveCardService;
import com.example.termProject.service.tag.SaveTagService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CardController {

	final private SaveCardService saveCardService;
	final private SaveTagService saveTagService;
	final private GetCardService getCardService;
	private ObjectMapper objectMapper;
	
	public CardController (SaveCardService saveCardService, SaveTagService saveTagService, GetCardService getCardService, ObjectMapper objectMapper) {
		this.saveCardService = saveCardService;
		this.saveTagService = saveTagService;
		this.getCardService = getCardService;
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
	
	@RequestMapping(value = "/getTag/{tagname}", method = RequestMethod.GET)
	public List<GetCardByTagDto> getTag (@PathVariable String tagname) {

		return getCardService.getListByTag(tagname);
	}
	
	//이미지에 대한 controller
	@RequestMapping(value = "/card/image/{cno}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long cno) {
    	
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(getCardService.getImageResource(cno));
    }

	
	
}
