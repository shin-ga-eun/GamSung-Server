package com.example.termProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.termProject.domain.entity.Card;
import com.example.termProject.domain.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{

	Reply findByRno (Long cno);

}
