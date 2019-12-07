package com.example.termProject.domain.dto.card;

public class CardSaveDto {
	private String identity; //작성자 아이디
	private String content; // 글 내용
	private int fontsize; //글 폰트크기 
	
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
	
	
	
	

}
