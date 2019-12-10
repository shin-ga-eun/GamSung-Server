package com.example.termProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.termProject.domain.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

	Card findByCno (Long cno);
}
