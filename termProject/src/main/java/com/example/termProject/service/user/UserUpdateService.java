package com.example.termProject.service.user;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.user.UserUpdateDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.repository.UserRepository;

@Service
public class UserUpdateService {

	final private UserRepository userRepository;
	
	public UserUpdateService (UserRepository userRepository) {
		this.userRepository  = userRepository;
	}
	
	public void updateUser(UserUpdateDto userUpdateDto) {
		User user = userRepository.findByIdentity(userUpdateDto.getIdentity());
		
		user.setProfileText(userUpdateDto.getProfileText());
		
		userRepository.save(user);
	}
}
