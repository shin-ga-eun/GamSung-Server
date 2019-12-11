package com.example.termProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.card.reply.GetReplyByCnoDto;
import com.example.termProject.domain.dto.card.reply.ReplySaveDto;
import com.example.termProject.domain.entity.Reply;
import com.example.termProject.service.card.reply.GetReplyService;
import com.example.termProject.service.card.reply.SaveReplyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReplyController {

	final private SaveReplyService saveReplyService;
	final private GetReplyService getReplyService;
	
	private ObjectMapper objectMapper;
	
	public ReplyController (SaveReplyService saveReplyService, GetReplyService getReplyService, ObjectMapper objectMapper) {
		this.saveReplyService = saveReplyService;
		this.getReplyService = getReplyService;
		this.objectMapper = objectMapper;
	}
	
	//댓글 저장
	@RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	public void saveReply (@RequestPart MultipartFile imageFile,@RequestParam("replySaveDto") String json) throws Exception {
		Reply reply = new Reply();
		
		ReplySaveDto replySave = objectMapper.readValue(json, ReplySaveDto.class);
		reply = saveReplyService.saveReplyAndFile(replySave, imageFile);
		
	}
	
	//댓글 리스트 출력 (cno)
	@RequestMapping(value = "/getReply/{cno}", method = RequestMethod.GET)
	public List<GetReplyByCnoDto> getReply (@PathVariable Long cno) {

		return getReplyService.getReply(cno);
	}
	
	//댓글카드 이미지에 대한 controller
	@RequestMapping(value = "/card/reply/image/{rno}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long rno) {
    	
    	System.out.println("controller rno >>>>>>>>>>>>>>>>"+rno);
    	
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(getReplyService.getImageResource(rno));
    }
    
}
