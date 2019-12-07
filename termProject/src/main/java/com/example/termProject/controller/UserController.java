package com.example.termProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.user.SignUpDto;
import com.example.termProject.service.user.UserSaveService;

@RestController
//@RequestMapping("/signUp")
public class UserController {

	final private UserSaveService userSaveService;
	
	public UserController(UserSaveService userSaveService) {
		this.userSaveService = userSaveService;
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public void saveUser (@RequestPart MultipartFile imageFile,@RequestParam("signUpDto") String json) throws Exception {
		
		SignUpDto userSave = objectMapper.readValue(json, SignUpDto.class);
		userSaveService.saveUserAndFile(userSave, imageFile);
		

	}
//	
//	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
//	public void saveUser (@RequestPart MultipartFile imageFile) throws Exception {
//		
//		userSaveService.saveUserAndFile(imageFile);
//		
//
//	}
//	
//	@PostMapping
//    public void saveArt(@RequestPart MultipartFile imageFile) throws Exception {
//       
//        userSaveService.saveUserAndFile(imageFile);
//        
//    }
}
