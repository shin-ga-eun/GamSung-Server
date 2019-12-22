package com.example.termProject.service.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.user.GetProfileDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.domain.entity.UserImage;
import com.example.termProject.repository.UserImageRepository;
import com.example.termProject.repository.UserRepository;

@Service
public class GetUserService {

	final private UserRepository userRepository;
	final private UserImageRepository userImageRepository;
	
	public GetUserService(UserRepository userRepository, UserImageRepository userImageRepository) {
		this.userRepository = userRepository;		
		this.userImageRepository = userImageRepository;
	}
	
	
	public byte[] getImageResource(Long uno) {
        UserImage userImage = userImageRepository.findByUser(userRepository.findByUno(uno));
        
        byte[] result = null;
        try{
            File file = new File(userImage.getImagePath());

            InputStream in = new FileInputStream(file);
            result = IOUtils.toByteArray(in);

            return result;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
		//마이프로필 - 유저 정보 (identity)
		public GetProfileDto getProfile (String identity) {
			User user = userRepository.findByIdentity(identity);
			GetProfileDto getProfile = new GetProfileDto();
			
			getProfile.setUno(user.getUno());
			getProfile.setIdentity(user.getIdentity());
			getProfile.setNickname(user.getNickname());
			getProfile.setProfileText(user.getProfileText());
			getProfile.setImageUrl("http://localhost:8080/user/image/"+getProfile.getUno());
			getProfile.setTotal(user.getTotal());
			getProfile.setToday(user.getToday());
			
			return getProfile;

		}
}
