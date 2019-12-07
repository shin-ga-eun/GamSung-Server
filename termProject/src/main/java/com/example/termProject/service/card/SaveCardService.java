package com.example.termProject.service.card;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.card.CardSaveDto;
import com.example.termProject.repository.CardRepository;

@Service
public class SaveCardService {

	final private CardRepository cardRepository;
	
	public SaveCardService (CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public void saveCard(CardSaveDto cardSaveDto) {
		
		
	}
}
