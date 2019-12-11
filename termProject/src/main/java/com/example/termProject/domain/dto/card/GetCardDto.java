package com.example.termProject.domain.dto.card;

import java.time.LocalDate;

public class GetCardDto {

	private Long cno; //카드 넘버
	
	private String identity; //작성자
	private String content; //카드 내용
	private int fontsize; //카드 폰트 크기
	private String imageUrl; //카드 이미지
	private LocalDate regDate; //카드 등록 날짜
	private int heart; //카드 공감 수
	
	
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFontsize() {
		return fontsize;
	}
	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
	}
	
	
	
}
