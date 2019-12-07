package com.example.termProject.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CardImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardImageNo;
	
	private String imageName;
	private String imageType;
	private String imagePath;
	private Long imageSize;
	private String imageUrl;
	
	@ManyToOne(targetEntity = Card.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;
	

	public Long getCardImageNo() {
		return cardImageNo;
	}

	public void setCardImageNo(Long cardImageNo) {
		this.cardImageNo = cardImageNo;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	

}
