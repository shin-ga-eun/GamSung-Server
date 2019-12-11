package com.example.termProject.domain.dto.user;

public class GetProfileDto {
	
	private Long uno; //마이프로필  유저넘버
	
	private String identity; //마이프로필 유저아이디
	private String nickname; //마이프로필 유저닉네임
	private String profileText; //마이프로필 자기소개글
	//private List<GetCardByIdentityDto> cards;
	private String imageUrl; //마이프로필 사진
	private int total; //전체 방문자수
	private int today; //오늘 방문자수
	
	
	
	
	public Long getUno() {
		return uno;
	}
	public void setUno(Long uno) {
		this.uno = uno;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileText() {
		return profileText;
	}
	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getToday() {
		return today;
	}
	public void setToday(int today) {
		this.today = today;
	}

	
}
