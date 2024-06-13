package com.portfolio.www.forum.board.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("BoardListDto")
public class BoardListDto {
	
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
	private Integer attachCnt;
	private Integer commentCnt;
	
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public Integer getBoardTypeSeq() {
		return boardTypeSeq;
	}
	public void setBoardTypeSeq(Integer boardTypeSeq) {
		this.boardTypeSeq = boardTypeSeq;
	}
	public String getBoardTypeNm() {
		return boardTypeNm;
	}
	public void setBoardTypeNm(String boardTypeNm) {
		this.boardTypeNm = boardTypeNm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
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
	
	public Integer getRegMemberSeq() {
		return regMemberSeq;
	}
	public void setRegMemberSeq(Integer regMemberSeq) {
		this.regMemberSeq = regMemberSeq;
	}
	public String getRegMemberId() {
		return regMemberId;
	}
	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}
	public String getRegMemberNm() {
		return regMemberNm;
	}
	public void setRegMemberNm(String regMemberNm) {
		this.regMemberNm = regMemberNm;
	}
	public String getUpdateDtm() {
		return updateDtm;
	}
	public void setUpdateDtm(String updateDtm) {
		this.updateDtm = updateDtm;
	}
	public Integer getUpdateMemberSeq() {
		return updateMemberSeq;
	}
	public void setUpdateMemberSeq(Integer updateMemberSeq) {
		this.updateMemberSeq = updateMemberSeq;
	}
	
	public Integer getAttachCnt() {
		return attachCnt;
	}
	public void setAttachCnt(Integer attachCnt) {
		this.attachCnt = attachCnt;
	}
	public Integer getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}
	public static BoardListDto setBoardDto(Integer boardTypeSeq,Integer boardSeq, String title, String content, Integer memberSeq) {
		
		BoardListDto boardDto = new BoardListDto();
		boardDto.setBoardTypeSeq(boardTypeSeq);
		boardDto.setBoardSeq(boardSeq);
		boardDto.setTitle(title);
		boardDto.setContent(content);
		boardDto.setRegMemberSeq(memberSeq);
		
		return boardDto;
	}
	
}
