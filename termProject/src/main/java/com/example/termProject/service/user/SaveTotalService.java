package com.example.termProject.service.user;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.user.GetUserNameDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.repository.UserRepository;

@Service
public class SaveTotalService {
	
	final private UserRepository userRepository;
	
	public SaveTotalService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void saveTotal (GetUserNameDto getUserNameDto) {
		User user = userRepository.findByIdentity(getUserNameDto.getIdentity());
		int total = user.getTotal()+1;
		
		user.setTotal(total);
		userRepository.save(user);		
	}

}
