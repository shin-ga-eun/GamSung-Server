package com.example.termProject.service.card;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.card.CardDeleteDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.domain.entity.CardImage;
import com.example.termProject.domain.entity.Reply;
import com.example.termProject.domain.entity.ReplyImage;
import com.example.termProject.domain.entity.Tag;
import com.example.termProject.repository.CardImageRepository;
import com.example.termProject.repository.CardRepository;
import com.example.termProject.repository.ReplyImageRepository;
import com.example.termProject.repository.ReplyRepository;
import com.example.termProject.repository.TagRepository;

@Service
public class DeleteCardService {

	final private CardRepository cardRepository;
	final private CardImageRepository cardImageRepository;
	final private TagRepository tagRepository;
	final private ReplyRepository replyRepository;
	final private ReplyImageRepository replyImageRepository;
	
	public DeleteCardService(CardRepository cardRepository, CardImageRepository cardImageRepository, TagRepository tagRepository, ReplyRepository replyRepository, ReplyImageRepository replyImageRepository) {
		this.cardRepository = cardRepository;
		this.cardImageRepository = cardImageRepository;
		this.tagRepository = tagRepository;
		this.replyRepository = replyRepository;
		this.replyImageRepository = replyImageRepository;
	}
	
	//삭제 시 고려사항 -> 카드, 카드이미지, 카드의 해시태그들, 댓글들, 댓글들이미지
	public void deleteCard(CardDeleteDto cardDeleteDto) {
		Card card = cardRepository.findByCno(cardDeleteDto.getCno());
		CardImage cardImage = cardImageRepository.findByCard(card).get();
		List<Tag> taglist = tagRepository.findByCno(cardDeleteDto.getCno());
		List<Reply> replylist = replyRepository.findByCard(card);
		
		for(int i=0; i<taglist.size(); i++)
			tagRepository.delete(taglist.get(i));
		
		for(int i=0; i<replylist.size(); i++) {
			ReplyImage replyImage = replyImageRepository.findByReply(replylist.get(i)).get();
			replyImageRepository.delete(replyImage);
			replyRepository.delete(replylist.get(i));
		}
		
		cardImageRepository.delete(cardImage);
		cardRepository.delete(card);
	}
	
}
