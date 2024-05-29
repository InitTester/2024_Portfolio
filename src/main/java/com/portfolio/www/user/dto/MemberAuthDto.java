package com.portfolio.www.user.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberAuthDto")
public class MemberAuthDto {
	private Integer authSeq;
	private Integer memberSeq;
	private String authNum;
	private String authUri;
	private String regDtm;
	private long expireDtm; // 시간
	private String authYn;
	
	public Integer getAuthSeq() {
		return authSeq;
	}
	public void setAuthSeq(Integer authSeq) {
		this.authSeq = authSeq;
	}
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getAuthNum() {
		return authNum;
	}
	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}
	public String getAuthUri() {
		return authUri;
	}
	public void setAuthUri(String authUri) {
		this.authUri = authUri;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public long getExpireDtm() {
		return expireDtm;
	}
	public void setExpireDtm(long expireDtm) {
		this.expireDtm = expireDtm;
	}
	public String getAuthYn() {
		return authYn;
	}
	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}
	
	public static MemberAuthDto setMemberAuthDto(int memberSeq, String uuid) {

		MemberAuthDto authDto = new MemberAuthDto();
		authDto.setMemberSeq(memberSeq);
		authDto.setAuthUri(uuid);
		
		return authDto;
	}
	
}
