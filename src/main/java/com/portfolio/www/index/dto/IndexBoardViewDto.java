package com.portfolio.www.index.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("IndexBoardViewDto")
public class IndexBoardViewDto {

	private int boardSeq;
	private Integer boardTypeSeq;
	private String boardTypeNm;
	private String title;
	private String content;
	private Integer hit;
	private String delYn;
	private String regDtm;
	private Date formatRegDtm;
	private Integer regMemberSeq;
	private String regMemberId;
	private String regMemberNm;
	private String updateDtm;
	private Integer updateMemberSeq;
	
	public int getBoardSeq() {
		return boardSeq;
	}
	public Integer getBoardTypeSeq() {
		return boardTypeSeq;
	}
	public String getBoardTypeNm() {
		return boardTypeNm;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Integer getHit() {
		return hit;
	}
	public String getDelYn() {
		return delYn;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public Date getFormatRegDtm() {
		return formatRegDtm;
	}
	public Integer getRegMemberSeq() {
		return regMemberSeq;
	}
	public String getRegMemberId() {
		return regMemberId;
	}
	public String getRegMemberNm() {
		return regMemberNm;
	}
	public String getUpdateDtm() {
		return updateDtm;
	}
	public Integer getUpdateMemberSeq() {
		return updateMemberSeq;
	}
	
}
