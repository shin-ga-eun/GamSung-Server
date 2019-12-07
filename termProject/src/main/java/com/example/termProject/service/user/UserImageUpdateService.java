package com.example.termProject.service.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.termProject.domain.dto.user.UserImageUpdateDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.domain.entity.UserImage;
import com.example.termProject.repository.UserImageRepository;
import com.example.termProject.repository.UserRepository;

@Service
public class UserImageUpdateService {

	final private UserRepository userRepository;
	final private UserImageRepository userImageRepository;
	final private UserSaveService userSaveService;
	
	public UserImageUpdateService(UserRepository userRepository, UserImageRepository userImageRepository, UserSaveService userSaveService) {
		this.userImageRepository = userImageRepository;
		this.userSaveService = userSaveService;
		this.userRepository = userRepository;
	}
	
	public void updateUserImage (MultipartFile imageFile, UserImageUpdateDto userImageUpdateDto) throws IOException {
		User user = userRepository.findByIdentity(userImageUpdateDto.getIdentity()); //아이디값을 가진 유저
		UserImage userImage = userImageRepository.findByUser(user);
		UserImage updateImage = new UserImage();
		
		updateImage = userSaveService.saveImage(imageFile, userImage.getUser());
		userImage.setImageName(updateImage.getImageName());
		userImage.setImagePath(updateImage.getImagePath());
		userImage.setImageSize(updateImage.getImageSize());
		userImage.setImageType(updateImage.getImageType());
		userImage.setImageUrl(updateImage.getImageUrl());
		

		userImageRepository.save(userImage);
		
	}
}
