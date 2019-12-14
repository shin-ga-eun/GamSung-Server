package com.example.termProject.service.user;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.user.LoginDto;
import com.example.termProject.domain.dto.user.LoginResponseDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.repository.UserRepository;

@Service
public class LoginService {

	final private UserRepository userRepository;
	
	public LoginService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//로그인
	public LoginResponseDto login (LoginDto loginDto) {
		LoginResponseDto identity = new LoginResponseDto();
		
		
		User user = userRepository.findByIdentity(loginDto.getIdentity());
		
		if(user == null) {
			System.out.println("/////////////////////////////////////////등록되지 않은 아이디입니다.");
			identity.setIdentity("wrond identity");
			return identity;
		}
		else if(!user.getPassword().equals(loginDto.getPassword())) {
			System.out.println("/////////////////////////////////////////잘못된 비밀번호입니다.");
			identity.setIdentity("wrond password");
			return identity;	
		}
		
		System.out.println("///////////////////////////////////////////로그인되었습니다.");
		identity.setIdentity(user.getIdentity());
		return identity;
	
	}
	
	
}
