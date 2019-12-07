package com.example.termProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.termProject.domain.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
