package com.portfolio.www.index.dto;

import org.apache.ibatis.type.Alias;

import com.portfolio.www.forum.board.dto.BoardDto;

import lombok.ToString;

@ToString
@Alias("IndexContactMeDto")
public class IndexContactMeDto {

	private Integer seq;
	private String name;
	private String email;
	private String inquiry;
	private String message;
	private String regDtm;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInquiry() {
		return inquiry;
	}
	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

	public static IndexContactMeDto setIndexContactMe(String name, String email, String inquiry, String message) {
		
		IndexContactMeDto contactMeDto = new IndexContactMeDto();
		contactMeDto.setName(name);
		contactMeDto.setEmail(email);
		contactMeDto.setInquiry(inquiry);
		contactMeDto.setMessage(message);
		
		return contactMeDto;
	}
	
}
