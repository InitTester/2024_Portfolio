package com.portfolio.www.user.dto;

import java.util.HashMap;

import org.apache.ibatis.type.Alias;

@Alias("MemberDto")
public class MemberDto {
	private Integer memberSeq;
	private String memberId;
	private String passwd;
	private String confirmPasswd;
	private String memberNm;
	private String email;
	private String authYn;
	private String pwdChngDtm;
	private String joinDtm;
	
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getConfirmPasswd() {
		return confirmPasswd;
	}
	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthYn() {
		return authYn;
	}
	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}
	public String getPwdChngDtm() {
		return pwdChngDtm;
	}
	public void setPwdChngDtm(String pwdChngDtm) {
		this.pwdChngDtm = pwdChngDtm;
	}
	public String getJoinDtm() {
		return joinDtm;
	}
	public void setJoinDtm(String joinDtm) {
		this.joinDtm = joinDtm;
	}
	
	public static MemberDto getMemberDto(HashMap<String, String> params) {
		
		MemberDto dto = new MemberDto();
		dto.setMemberId(params.get("memberId"));
		dto.setMemberNm(params.get("memberNm"));
		dto.setEmail(params.get("email"));
		dto.setPasswd(params.get("passwd"));
		dto.setConfirmPasswd(params.get("confirmPasswd"));
		
		return dto;
	}
}
