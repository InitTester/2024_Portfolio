package com.portfolio.www.forum.board.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Alias("BoardCommentDto")
public class BoardCommentDto {
	private Integer commentSeq;
	private Integer lvl;
	private String content;
	private Integer boardSeq;
	private Integer boardTypeSeq;
	private Integer memberSeq;
	private Integer parentCommentSeq;
	private String pMemberNm;
	private String regDtm;
	private String formatRegDtm;
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
	
	public String getpMemberNm() {
		return pMemberNm;
	}
	public void setpMemberNm(String pMemberNm) {
		this.pMemberNm = pMemberNm;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
		
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            
            Date changeDtm = originalFormat.parse(regDtm);
            long changeTimeMillis = changeDtm.getTime();

            log.info("changeTimeMillis :",changeTimeMillis);
            long nowTimeMillis = Calendar.getInstance().getTimeInMillis();

            log.info("nowTimeMillis :",nowTimeMillis);
            long formatTimeMillis = nowTimeMillis - changeTimeMillis;
            
            long timeMillis = 0;
            String time = "";
            int seconds = 60, minutes = 60, hours = 24;
            
            log.info("formatTimeMillis :",formatTimeMillis);
            
            if(formatTimeMillis < seconds * 1000) {
                timeMillis = formatTimeMillis / 1000;
                time = timeMillis + "초 전";
            } else if(formatTimeMillis < minutes * seconds * 1000) {
                timeMillis = (formatTimeMillis/1000) / seconds;
                time = timeMillis + "분 전";
            } else if(formatTimeMillis < hours * minutes * seconds * 1000) {
                timeMillis = (formatTimeMillis/1000/seconds) / minutes;
                time = timeMillis + "시 전";
            } else {
                timeMillis = (formatTimeMillis/1000/seconds/minutes) / hours;
                time = timeMillis + "일 전";
            }
            
            if(timeMillis<0) {
            	time = "0초 전";
            }
            
            setFormatRegDtm(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }	
	}

	public String getFormatRegDtm() {
		return formatRegDtm;
	}
	public void setFormatRegDtm(String formatRegDtm) {
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
