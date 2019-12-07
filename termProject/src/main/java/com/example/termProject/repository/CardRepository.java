package com.example.termProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.termProject.domain.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
