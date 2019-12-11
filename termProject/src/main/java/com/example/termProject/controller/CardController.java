package com.example.termProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
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
import com.example.termProject.domain.dto.card.GetCardByIdentityDto;
import com.example.termProject.domain.dto.card.GetCardByTagDto;
import com.example.termProject.domain.dto.card.GetCardDto;
import com.example.termProject.domain.dto.tag.TagSaveDto;
import com.example.termProject.domain.dto.user.GetProfileDto;
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
	
	//카드 저장
	@RequestMapping(value = "/saveCard", method = RequestMethod.POST)
	public Long saveCard (@RequestPart MultipartFile imageFile,@RequestParam("cardSaveDto") String json) throws Exception {
		Long cno; //방금 저장한 카드의 넘버 -> 해시저장시 사용할 예정.
		Card card = new Card();
		
		CardSaveDto cardSave = objectMapper.readValue(json, CardSaveDto.class);
		card = saveCardService.saveCardAndFile(cardSave, imageFile);
		cno =  card.getCno();
		
		return cno;
	}
	
	//카드 저장 후, 태그 저장할 때 사용해야함 -> 카드저장에서 받은 cno로 저장.
	@RequestMapping(value = "/saveTag", method = RequestMethod.POST)
	public void saveTag (@RequestBody TagSaveDto tagSaveDto) {
		saveTagService.saveTag(tagSaveDto);
	}
	
	//tagName 상세보기 (해당 태그에 해당하는 카드 리스트 출력)
	@RequestMapping(value = "/getTag/{tagname}", method = RequestMethod.GET)
	public List<GetCardByTagDto> getTag (@PathVariable String tagname) {

		return getCardService.getListByTag(tagname);
	}
	
    
    //하나의 카드 상세보기-cno
    @RequestMapping(value = "/getCard/{cno}", method = RequestMethod.GET)
	public GetCardDto getCardByCno (@PathVariable Long cno) {

		return getCardService.getCardByCno(cno);
    }
    
    //마이프로필 카드 리스트 출력 -identity
    @RequestMapping(value = "/getProfileCard/{identity}", method = RequestMethod.GET)
	public List<GetCardByIdentityDto> getCardByIdentity (@PathVariable String identity) {

		return getCardService.getListByIdentity(identity);
	}
    
    
    //카드 이미지에 대한 controller
  	@RequestMapping(value = "/card/image/{cno}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long cno) {
      	
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                 .body(getCardService.getImageResource(cno));
     }
      

	
}
