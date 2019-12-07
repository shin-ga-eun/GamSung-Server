package com.example.termProject.service.card;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.termProject.domain.dto.card.CardSaveDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.domain.entity.CardImage;
import com.example.termProject.repository.CardImageRepository;
import com.example.termProject.repository.CardRepository;
import com.example.termProject.repository.UserRepository;

@Service
public class SaveCardService {
	
	private static final String IMAGE_PATH = "resources.image-locations";

	final private CardRepository cardRepository;
	final private CardImageRepository cardImageRepository;
	final private UserRepository userRepository;
	
	private Environment environment;
	
	public SaveCardService (CardRepository cardRepository, CardImageRepository cardImageRepository, UserRepository userRepository, Environment environment) {
		this.cardRepository = cardRepository;
		this.cardImageRepository = cardImageRepository;
		this.userRepository = userRepository;
		this.environment= environment;
	}
	
	
	
	public Card saveCardAndFile(CardSaveDto cardSaveDto, MultipartFile file) throws IOException {
		Card card = new Card();
		Card saveCard = new Card(); //최종저장카드 변수
		LocalDate regDate = LocalDate.now();
		
		card.setUser(userRepository.findByIdentity(cardSaveDto.getIdentity()));
		card.setContent(cardSaveDto.getContent());
		card.setFontsize(cardSaveDto.getFontsize());
		card.setRegDate(regDate);
		
		saveCard = cardRepository.save(card);
		
		CardImage cardImage = saveImage(file, card);
		cardImageRepository.save(cardImage);
		
		return saveCard;
	}
	
	
	
	public CardImage saveImage(MultipartFile file, Card card) throws IOException {
		CardImage cardrImage = new CardImage();
		
		UUID uid = UUID.randomUUID();
        String fileName = uid + "_" + file.getOriginalFilename();
        String savePath = makePath(environment.getProperty(IMAGE_PATH));
        File destinationFile = new File(environment.getProperty(IMAGE_PATH) + savePath, fileName);

        file.transferTo(destinationFile);

        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/image/" + card.getCno())
                .toUriString(); //모르겠음

        cardrImage.setImageName(file.getOriginalFilename());
        cardrImage.setImageSize(file.getSize());
        cardrImage.setImageType(file.getContentType());
        cardrImage.setImageUrl(imageUrl);
        cardrImage.setImagePath(environment.getProperty(IMAGE_PATH) + savePath + File.separator + fileName);
        cardrImage.setCard(card);
		
		
		
		return cardrImage;
	}
	
	private String makePath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();

        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH)+1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;

    }
	
	
	private void makeDir(String uploadPath, String... paths) {

        if(new File(uploadPath + paths[paths.length - 1]).exists()) {
            return;
        }

        for( String path : paths){
            File dirPath = new File(uploadPath + path);

            if(!dirPath.exists()){
                dirPath.mkdir();
            }
        }
    }
	
	
}
