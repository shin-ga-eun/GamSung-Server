package com.example.termProject.domain.dto.user;

public class UserUpdateDto {

	private String identity; //아이디
	
	private String profileText; //프로필자기소개
	
	
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getProfileText() {
		return profileText;
	}
	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}
	
	
}
