package com.example.termProject.service.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.card.GetCardByCnoDto;
import com.example.termProject.domain.dto.card.GetCardByTagDto;
import com.example.termProject.domain.dto.tag.GetCnoDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.domain.entity.CardImage;
import com.example.termProject.domain.entity.Tag;
import com.example.termProject.repository.CardImageRepository;
import com.example.termProject.repository.CardRepository;
import com.example.termProject.repository.TagRepository;

@Service
public class GetCardService {
	
	final private CardRepository cardRepository;
	final private CardImageRepository cardImageRepository;
	final private TagRepository tagRepository;
	
	public GetCardService (CardRepository cardRepository, CardImageRepository cardImageRepository, TagRepository tagRepository) {
		this.cardRepository = cardRepository;
		this.cardImageRepository = cardImageRepository;
		this.tagRepository = tagRepository;
	}
	
	public byte[] getImageResource(Long cno) {
        CardImage cardImage = cardImageRepository.findByCard(cardRepository.findByCno(cno)).get();
        
        byte[] result = null;
        try{
            File file = new File(cardImage.getImagePath());

            InputStream in = new FileInputStream(file);
            result = IOUtils.toByteArray(in);

            return result;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
	//태그 카드 조인을 안해서 태그에서 cno 찾은 후 해당 카드를 리스트로 받기.
	public List<GetCardByTagDto> getListByTag (String tagname) {
		List<Tag> taglist = tagRepository.findByTagname(tagname);
		List<GetCnoDto> getCnoList = new ArrayList<>();
		
		List<Card> cardlist = new ArrayList<>();
		List<GetCardByTagDto> getCardList = new ArrayList<>();
		
		
		//tagName, cno 리스트 생성
		for(int i=0; i<taglist.size(); i++) {
			GetCnoDto getCno = new GetCnoDto();
			getCno.setCno(taglist.get(i).getCno());
			getCno.setTagname(taglist.get(i).getTagname());
			
			getCnoList.add(getCno);
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
			//System.out.println(getImageResource(cardlist.get(i).getCno()));
			
		}
		
		
		return getCardList;
	}
	
	
	//카드상세보기 (cno) (1번) + 댓글리스트 가져오기 GetReplyByCno
	public GetCardByCnoDto getCardByCno (Long cno) {
		Card card = cardRepository.findByCno(cno);
		GetCardByCnoDto getCard = new GetCardByCnoDto();
		
		getCard.setCno(card.getCno());
		getCard.setIdentity(card.getUser().getIdentity());
		getCard.setContent(card.getContent());
		getCard.setFontsize(card.getFontsize());
		getCard.setImageUrl("http://localhost:8080/card/image/"+getCard.getCno());
		getCard.setRegDate(card.getRegDate());
		getCard.setHeart(card.getHeart());
		
		return getCard;
		
	}
	
	
	
	
}
