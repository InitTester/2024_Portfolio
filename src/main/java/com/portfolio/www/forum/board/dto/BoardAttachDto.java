package com.portfolio.www.forum.board.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.ToString;

@Alias("BoardAttachDto")
@ToString
public class BoardAttachDto {
	
	private Integer attachSeq;
	private Integer boardSeq;
	private Integer boardTypeSeq;
	private String orgFileNm;
	private String savePath;
	private String chngFileNm;
	private long fileSize;
	private String fileType;
	private String accessUri;
	private String regDtm;
	
	public Integer getAttachSeq() {
		return attachSeq;
	}
	public void setAttachSeq(Integer attachSeq) {
		this.attachSeq = attachSeq;
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
	public String getOrgFileNm() {
		return orgFileNm;
	}
	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getChngFileNm() {
		return chngFileNm;
	}
	public void setChngFileNm(String chngFileNm) {
		this.chngFileNm = chngFileNm;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getAccessUri() {
		return accessUri;
	}
	public void setAccessUri(String accessUri) {
		this.accessUri = accessUri;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	
	public static BoardAttachDto setBoardAttachDto(Integer boardTypeSeq, Integer boardSeq) {
		
		BoardAttachDto attachDto = new BoardAttachDto();
		attachDto.setBoardTypeSeq(boardTypeSeq);
		attachDto.setBoardSeq(boardSeq);
		
		return attachDto;
	}
	
	
}
