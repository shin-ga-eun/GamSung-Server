package com.example.termProject.service.card.reply;

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

import com.example.termProject.domain.dto.card.reply.ReplySaveDto;
import com.example.termProject.domain.entity.Card;
import com.example.termProject.domain.entity.CardImage;
import com.example.termProject.domain.entity.Reply;
import com.example.termProject.domain.entity.ReplyImage;
import com.example.termProject.repository.CardRepository;
import com.example.termProject.repository.ReplyImageRepository;
import com.example.termProject.repository.ReplyRepository;

@Service
public class SaveReplyService {
	
	private static final String IMAGE_PATH = "resources.image-locations";
	
	final private ReplyRepository replyRepository;
	final private ReplyImageRepository replyImageRepository;
	final private CardRepository cardRepository;
	
	private Environment environment;
	
	public SaveReplyService(ReplyRepository replyRepository, ReplyImageRepository replyImageRepository, CardRepository cardRepository, Environment environment) {
		this.replyRepository = replyRepository;
		this.replyImageRepository = replyImageRepository;
		this.cardRepository = cardRepository;
		this.environment = environment;
	}
	
	public Reply saveReplyAndFile(ReplySaveDto replySaveDto, MultipartFile file) throws IOException {
		Reply reply = new Reply();
		Reply saveReply = new Reply(); //최종저장카드 변수
		LocalDate regDate = LocalDate.now();
		
		reply.setIdentity(replySaveDto.getIdentity());
		reply.setCard(cardRepository.findByCno(replySaveDto.getCno()));
		reply.setContent(replySaveDto.getContent());
		reply.setFontsize(replySaveDto.getFontsize());
		reply.setRegDate(regDate);
		
		saveReply = replyRepository.save(reply);
		
		ReplyImage replyImage = saveImage(file, reply);
		replyImageRepository.save(replyImage);
		
		return saveReply;
	}
	
	
	
	public ReplyImage saveImage(MultipartFile file, Reply reply) throws IOException {
		ReplyImage replyImage = new ReplyImage();
		
		UUID uid = UUID.randomUUID();
        String fileName = uid + "_" + file.getOriginalFilename();
        String savePath = makePath(environment.getProperty(IMAGE_PATH));
        File destinationFile = new File(environment.getProperty(IMAGE_PATH) + savePath, fileName);

        file.transferTo(destinationFile);

        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/reply/image/"+reply.getRno())
                .toUriString(); //모르겠음

        replyImage.setImageName(file.getOriginalFilename());
        replyImage.setImageSize(file.getSize());
        replyImage.setImageType(file.getContentType());
        replyImage.setImageUrl(imageUrl);
        replyImage.setImagePath(environment.getProperty(IMAGE_PATH) + savePath + File.separator + fileName);
        replyImage.setReply(reply);
		
		
		
		return replyImage;
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
