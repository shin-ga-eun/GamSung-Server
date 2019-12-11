package com.example.termProject.domain.dto.card.reply;

import java.time.LocalDate;

public class GetReplyByCnoDto {
	
	private Long rno; //댓글카드 넘버
	private String identity; //댓글 작성자
	private String content; //댓글 카드 내용
	private int fontsize; //댓글 카드 폰트 크기
	private LocalDate regDate; //댓글 카드 작성 날짜
	private String imageUrl; //댓글 이미지 url
	
	
	
	
	public Long getRno() {
		return rno;
	}
	public void setRno(Long rno) {
		this.rno = rno;
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
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	

}
