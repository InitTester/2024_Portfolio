package com.portfolio.www.forum.board.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("BoardCommentDto")
public class BoardCommentDto {
	private Integer commentSeq;
	private Integer lvl;
	private String content;
	private Integer boardSeq;
	private Integer boardTypeSeq;
	private Integer memberSeq;
	private Integer parentCommentSeq;
	private String regDtm;
	private Date formatRegDtm;
	private String updateDtm;
	private String deleteDtm;
	private String memberNm;
	
	public Integer getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(Integer commentSeq) {
		this.commentSeq = commentSeq;
	}
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Integer boardSeq) {
		this.boardSeq = boardSeq;
	}
	public Integer getBoardTypeSeq() {
		return boardTypeSeq;
	}
	public void setBoardTypeSeq(Integer boardTypeSeq) {
		this.boardTypeSeq = boardTypeSeq;
	}
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public Integer getParentCommentSeq() {
		return parentCommentSeq;
	}
	public void setParentCommentSeq(Integer parentCommentSeq) {
		this.parentCommentSeq = parentCommentSeq;
	}
	
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
		
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            setFormatRegDtm(originalFormat.parse(regDtm));
        } catch (ParseException e) {
            e.printStackTrace();
        }	
	}

	public Date getFormatRegDtm() {
		return formatRegDtm;
	}
	public void setFormatRegDtm(Date formatRegDtm) {
		this.formatRegDtm = formatRegDtm;
	}
	
	public String getUpdateDtm() {
		return updateDtm;
	}
	public void setUpdateDtm(String updateDtm) {
		this.updateDtm = updateDtm;
	}
	public String getDeleteDtm() {
		return deleteDtm;
	}
	public void setDeleteDtm(String deleteDtm) {
		this.deleteDtm = deleteDtm;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	
	public static BoardCommentDto setBoardCommentDto(Integer boardTypeSeq, Integer boardSeq) {
		
		BoardCommentDto commentDto = new BoardCommentDto();
		commentDto.setBoardTypeSeq(boardTypeSeq);
		commentDto.setBoardSeq(boardSeq);
		
		return commentDto;
	}
}
