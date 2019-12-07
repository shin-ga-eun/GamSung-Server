package com.example.termProject.service.user;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.user.LoginDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.repository.UserRepository;

@Service
public class LoginService {

	final private UserRepository userRepository;
	
	public LoginService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//로그인
	public String login (LoginDto loginDto) {
		String identity = "";
		User user = userRepository.findByIdentity(loginDto.getIdentity());
		
		if(user.getUno() == null) {
			System.out.println("/////////////////////////////////////////등록되지 않은 아이디입니다.");
		}
		else {
			if(user.getPassword().equals(loginDto.getPassword())) {
				System.out.println("///////////////////////////////////////////로그인되었습니다.");
				identity = user.getIdentity();
			}				
		}
		
		return identity;
	}
	
	
}
