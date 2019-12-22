package com.example.termProject.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.user.GetProfileDto;
import com.example.termProject.domain.dto.user.GetUserNameDto;
import com.example.termProject.domain.dto.user.LoginDto;
import com.example.termProject.domain.dto.user.LoginResponseDto;
import com.example.termProject.domain.dto.user.SignUpDto;
import com.example.termProject.domain.dto.user.UserImageUpdateDto;
import com.example.termProject.domain.dto.user.UserUpdateDto;
import com.example.termProject.service.user.GetSearchKeywordService;
import com.example.termProject.service.user.GetUserService;
import com.example.termProject.service.user.LoginService;
import com.example.termProject.service.user.SaveTotalService;
import com.example.termProject.service.user.UserImageUpdateService;
import com.example.termProject.service.user.UserSaveService;
import com.example.termProject.service.user.UserUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	final private LoginService loginService;
	final private UserSaveService userSaveService;
	final private UserUpdateService userUpdateService;
	final private UserImageUpdateService userImageUpdateService;
	final private GetUserService getUserService;
	final private GetSearchKeywordService getSearchKeywordService;
	final private SaveTotalService saveTotalService;
	private ObjectMapper objectMapper;
	
	public UserController(LoginService loginService, UserSaveService userSaveService, UserUpdateService userUpdateService, UserImageUpdateService userImageUpdateService, GetUserService getUserService, GetSearchKeywordService getSearchKeywordService, SaveTotalService saveTotalService, ObjectMapper objectMapper) {
		
		this.loginService = loginService;
		this.userSaveService = userSaveService;
		this.userUpdateService = userUpdateService;
		this.userImageUpdateService = userImageUpdateService;
		this.getUserService = getUserService;
		this.getSearchKeywordService = getSearchKeywordService;
		this.saveTotalService = saveTotalService;
		this.objectMapper = objectMapper;
	}
	
	//회원가입 -유저정보, 유저프로필이미지
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public void saveUser (@RequestPart MultipartFile imageFile, @RequestParam("signUpDto") String json) throws Exception {
		
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
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseDto login (@RequestBody LoginDto loginDto) {
		return loginService.login(loginDto);
		
	}
	
	
	//마이프로필 유저정보 출력 -identity
    @RequestMapping(value = "/getProfile/{identity}", method = RequestMethod.GET)
	public GetProfileDto getProfile (@PathVariable String identity) {

		return getUserService.getProfile(identity);
	}
    
    //유저 이미지에 대한 controller
  	@RequestMapping(value = "/user/image/{uno}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long uno) {
      	
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                 .body(getUserService.getImageResource(uno));
     }
    
    //유저 검색 리스트 -keyword
    @RequestMapping(value = "/getSearchKeyword/{keyword}", method = RequestMethod.GET)
	public List<GetUserNameDto> getSearchKeyword (@PathVariable String keyword) {

		return getSearchKeywordService.getSearchKeyword(keyword);
	}
    
    //유저 total 조회수 증가 -identity
  	@RequestMapping(value = "/saveTotal", method = RequestMethod.POST)
  	public void saveTotal (@RequestBody GetUserNameDto getUserNameDto) {
  		saveTotalService.saveTotal(getUserNameDto);
  	}
    

}
