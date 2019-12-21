package com.example.termProject.service.card.reply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.card.reply.GetReplyByCnoDto;
import com.example.termProject.domain.entity.Reply;
import com.example.termProject.domain.entity.ReplyImage;
import com.example.termProject.repository.ReplyImageRepository;
import com.example.termProject.repository.ReplyRepository;

@Service
public class GetReplyService {
	
	final private ReplyRepository replyRepository;
	final private ReplyImageRepository replyImageRepository;	
	
	public GetReplyService (ReplyRepository replyRepository, ReplyImageRepository replyImageRepository) {
		this.replyRepository = replyRepository;
		this.replyImageRepository = replyImageRepository;
	}
	
	
	public byte[] getImageResource(Long rno) {
		ReplyImage replyImage = replyImageRepository.findByReply(replyRepository.findByRno(rno)).get();
        
        byte[] result = null;
        try{
            File file = new File(replyImage.getImagePath());

            InputStream in = new FileInputStream(file);
            result = IOUtils.toByteArray(in);

            return result;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<GetReplyByCnoDto> getReply (Long cno){
		List<Reply> getReply = replyRepository.findAll();
		List<GetReplyByCnoDto> getReplyByCno = new ArrayList<>();
				
		for(int i=0; i<getReply.size(); i++) {
			if(getReply.get(i).getCard().getCno() == cno) {
				GetReplyByCnoDto reply = new GetReplyByCnoDto();
				
				LocalDate ld = getReply.get(i).getRegDate();
				String localDateToString = ld.getYear()+"."+ld.getMonthValue()+"."+ld.getDayOfMonth();
				
				System.out.println("/////////////////////////"+localDateToString);
				reply.setRno(getReply.get(i).getRno());
				reply.setContent(getReply.get(i).getContent());
				reply.setFontsize(getReply.get(i).getFontsize());
				reply.setIdentity(getReply.get(i).getIdentity());
				reply.setRegDate(localDateToString);
				reply.setImageUrl("http://localhost:8080/card/reply/image/"+reply.getRno());				
				getReplyByCno.add(reply);
			}
		}
		
		return getReplyByCno;
			
	}
	
	
}
