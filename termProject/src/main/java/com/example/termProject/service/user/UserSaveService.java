package com.example.termProject.service.user;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.termProject.domain.dto.user.SignUpDto;
import com.example.termProject.domain.entity.User;
import com.example.termProject.domain.entity.UserImage;
import com.example.termProject.repository.UserImageRepository;
import com.example.termProject.repository.UserRepository;

@Service
public class UserSaveService {
	
	private static final String IMAGE_PATH = "resources.image-locations";

	final private UserRepository userRepository;
	final private UserImageRepository userImageRepository;

	private Environment environment;
	
	public UserSaveService(UserRepository userRepository, UserImageRepository userImageRepository, Environment environment) {
		this.userRepository = userRepository;
		this.userImageRepository = userImageRepository;
		this.environment = environment;
	}
	
	
	public void saveUserAndFile(SignUpDto signUpDto, MultipartFile file) throws IOException {
		User user = new User();
		
		user.setIdentity(signUpDto.getIdentity());
		user.setPassword(signUpDto.getPassword());
		user.setNickname(signUpDto.getNickname());
		
		userRepository.save(user);
		
		UserImage userImage = saveImage(file, user);
		
		userImageRepository.save(userImage);
		
	
	}
	
	
	public UserImage saveImage(MultipartFile file, User user) throws IOException {
		UserImage userImage = new UserImage();
		
		UUID uid = UUID.randomUUID();
        String fileName = uid + "_" + file.getOriginalFilename();
        String savePath = makePath(environment.getProperty(IMAGE_PATH));
        File destinationFile = new File(environment.getProperty(IMAGE_PATH) + savePath, fileName);

        file.transferTo(destinationFile);

        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/image/" + user.getUno())
                .toUriString(); //모르겠음

        userImage.setImageName(file.getOriginalFilename());
        userImage.setImageSize(file.getSize());
        userImage.setImageType(file.getContentType());
        userImage.setImageUrl(imageUrl);
		userImage.setImagePath(environment.getProperty(IMAGE_PATH) + savePath + File.separator + fileName);
		userImage.setUser(user);
		
		
		
		return userImage;
	}

//	public void saveUserAndFile(MultipartFile file) throws IOException {
//		
//		
//		UserImage userImage = saveImage(file);
//		
//		userImageRepository.save(userImage);
//		
//	
//	}
//	public UserImage saveImage(MultipartFile file) throws IOException {
//		UserImage userImage = new UserImage();
//		
//		UUID uid = UUID.randomUUID();
//        String fileName = uid + "_" + file.getOriginalFilename();
//        String savePath = makePath(environment.getProperty(IMAGE_PATH));
//        File destinationFile = new File(environment.getProperty(IMAGE_PATH) + savePath, fileName);
//
//        file.transferTo(destinationFile);
//
//        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/artSharing/art/image/")
//                .toUriString(); //모르겠음
//
//        userImage.setImageName(file.getOriginalFilename());
//        userImage.setImageSize(file.getSize());
//        userImage.setImageType(file.getContentType());
//        userImage.setImageUrl(imageUrl);
//		userImage.setImagePath(environment.getProperty(IMAGE_PATH) + savePath + File.separator + fileName);
//		
//		
//		
//		return userImage;
//	}
	
	
	private String makePath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();

        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH)+1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;

    }
	
	
	private void makeDir(String uploadPath, String... paths) {

        if(new File(uploadPath + paths[paths.length - 1]).exists()) {
            return;
        }

        for( String path : paths){
            File dirPath = new File(uploadPath + path);

            if(!dirPath.exists()){
                dirPath.mkdir();
            }
        }
    }
	
	
	
	
}
