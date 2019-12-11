package com.example.termProject.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReplyImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replyImageNo;
	
	private String imageName;
	private String imageType;
	private String imagePath;
	private Long imageSize;
	private String imageUrl;
	
	@ManyToOne(targetEntity = Reply.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "reply_id")
	private Reply reply;

	public Long getReplyImageNo() {
		return replyImageNo;
	}

	public void setReplyImageNo(Long replyImageNo) {
		this.replyImageNo = replyImageNo;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getImageSize() {
		return imageSize;
	}

	public void setImageSize(Long imageSize) {
		this.imageSize = imageSize;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	
}
