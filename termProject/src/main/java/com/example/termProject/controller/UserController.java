package com.example.termProject.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.user.SignUpDto;
import com.example.termProject.domain.dto.user.UserImageUpdateDto;
import com.example.termProject.domain.dto.user.UserUpdateDto;
import com.example.termProject.service.user.UserImageUpdateService;
import com.example.termProject.service.user.UserSaveService;
import com.example.termProject.service.user.UserUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	final private UserSaveService userSaveService;
	final private UserUpdateService userUpdateService;
	final private UserImageUpdateService userImageUpdateService;
	private ObjectMapper objectMapper;
	
	public UserController(UserSaveService userSaveService, UserUpdateService userUpdateService, UserImageUpdateService userImageUpdateService, ObjectMapper objectMapper) {
		this.userSaveService = userSaveService;
		this.userUpdateService = userUpdateService;
		this.userImageUpdateService = userImageUpdateService;
		this.objectMapper = objectMapper;
	}
	
	//회원가입 -유저정보, 유저프로필이미지
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public void saveUser (@RequestPart MultipartFile imageFile,@RequestParam("signUpDto") String json) throws Exception {
		
		SignUpDto userSave = objectMapper.readValue(json, SignUpDto.class);
		userSaveService.saveUserAndFile(userSave, imageFile);
		
	}
	
	//회원정보수정 -유저정보수정
	@RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
	public void updateUser (@RequestBody UserUpdateDto userUpdateDto) {
		userUpdateService.updateUser(userUpdateDto);
	}
	
	//회원정보수정 -유저이미지수정
	@RequestMapping(value = "/userImageUpdate", method = RequestMethod.POST)
	public void updateUserImage (@RequestPart MultipartFile imageFile,@RequestParam("userImageUpdateDto") String json) throws Exception {
		
		UserImageUpdateDto userImageUpdate = objectMapper.readValue(json, UserImageUpdateDto.class);
		userImageUpdateService.updateUserImage(imageFile, userImageUpdate);
		
	}
	

}
