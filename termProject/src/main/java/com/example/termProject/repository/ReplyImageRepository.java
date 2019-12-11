package com.example.termProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.termProject.domain.entity.Reply;
import com.example.termProject.domain.entity.ReplyImage;

@Repository
public interface ReplyImageRepository extends JpaRepository<ReplyImage, Long>{

	Optional<ReplyImage> findByReply(Reply reply);

}
