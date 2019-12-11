package com.example.termProject.domain.dto.card.reply;

public class ReplySaveDto {

	private String identity; //작성자 아이디
	private Long cno; //해당 카드 넘버
	
	private String content; //댓글카드 내용
	private int fontsize; //댓글카드 폰트크기
	
	

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

	public Long getCno() {
		return cno;
	}

	public void setCno(Long cno) {
		this.cno = cno;
	}
	
	
	
}
