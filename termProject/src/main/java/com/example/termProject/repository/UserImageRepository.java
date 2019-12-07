package com.example.termProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.termProject.domain.entity.User;
import com.example.termProject.domain.entity.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long>{


	UserImage findByUser(User user);
}
