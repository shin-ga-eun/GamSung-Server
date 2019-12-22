package com.example.termProject.service.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.card.GetCardByTagDto;
import com.example.termProject.domain.dto.tag.GetCnoDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.domain.entity.Tag;
import com.example.termProject.repository.CardRepository;
import com.example.termProject.repository.TagRepository;

@Service
public class GetSearchTagService {
	
	final private TagRepository tagRepository;
	final private CardRepository cardRepository;
	
	public GetSearchTagService(TagRepository tagRepository, CardRepository cardRepository) {
		this.tagRepository = tagRepository;		
		this.cardRepository = cardRepository;
	}
	
	
	//태그검색 - keyword
	public List<GetCardByTagDto> getSearchTag (String keyword) {
		List<Tag> taglist = tagRepository.findAll();
		List<GetCnoDto> getCnoList = new ArrayList<>();
		
		List<Card> cardlist = new ArrayList<>();
		List<GetCardByTagDto> getCardList = new ArrayList<>();
		
		
		//tagName, cno 리스트 생성
		for(int i=0; i<taglist.size(); i++) {
			if(taglist.get(i).getTagname().contains(keyword)) {
				GetCnoDto getCno = new GetCnoDto();
				getCno.setCno(taglist.get(i).getCno());
				getCno.setTagname(taglist.get(i).getTagname());
				
				getCnoList.add(getCno);
			}
		}
		
		//카드 전체 리스트 생성 후, dto에 맞는 카드 데이터 리스트 생성
		for(int i=0; i<getCnoList.size(); i++) {
			Card c = cardRepository.findByCno(getCnoList.get(i).getCno());
			
			cardlist.add(c);
		}
		
		for(int i=0; i<cardlist.size(); i++) {
			GetCardByTagDto getCard = new GetCardByTagDto();
			
			getCard.setCno(cardlist.get(i).getCno());
			getCard.setContent(cardlist.get(i).getContent());
			getCard.setFontsize(cardlist.get(i).getFontsize());
			getCard.setImageUrl("http://localhost:8080/card/image/"+getCard.getCno());
			getCardList.add(getCard);
			
		}
		
		
		return getCardList;
	}
	
}
