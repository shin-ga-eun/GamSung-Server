package com.example.termProject.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.user.GetUserNameDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.repository.UserRepository;

@Service
public class GetSearchKeywordService {

	final private UserRepository userRepository;
	
	public GetSearchKeywordService(UserRepository userRepository) {
		this.userRepository = userRepository;		
	}
	
	//유저검색 - keyword
	public List<GetUserNameDto> getSearchKeyword (String keyword) {
		List<User> userlist = userRepository.findAll();
		List<GetUserNameDto> getlist = new ArrayList<>();
		
		for(int i=0; i<userlist.size(); i++) {
			if(userlist.get(i).getIdentity().contains(keyword)) {
				GetUserNameDto getUserName = new GetUserNameDto();
				getUserName.setIdentity(userlist.get(i).getIdentity());
				
				getlist.add(getUserName);
			}
				
		}
		
		return getlist;

	}
}
