package com.example.termProject.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uno;
	
	@Column(unique = true)
	private String identity; //아이디
	
	private String password; //비밀번호
	private String nickname; //닉네임
	private String profileText; //프로필자기소개
	private int today; //오늘방문수
	private int total; //전체방문수
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getToday() {
		return today;
	}
	public void setToday(int today) {
		this.today = today;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
