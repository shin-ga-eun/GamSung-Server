package com.example.termProject.domain.dto.card;

public class GetCardByTagDto {

	private Long cno; //카드넘버
	
	private String content; //카드 글 내용
	private int fontsize; //카드 폰트크기
	
	private String imageUrl; //카드 이미지
	
	
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
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
	
	
	
	
	
}
