package com.example.termProject.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tno; //태그넘버
	
	private String tagname; //태그명
	
	private LocalDate regDate; //등록날짜	
	
	private Long cno; //카드 넘버 -> 조인안함 
	

	public Long getTno() {
		return tno;
	}

	public void setTno(Long tno) {
		this.tno = tno;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public Long getCno() {
		return cno;
	}

	public void setCno(Long cno) {
		this.cno = cno;
	}
	
	
	
	
}
