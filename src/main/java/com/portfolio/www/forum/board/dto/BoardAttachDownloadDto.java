package com.portfolio.www.forum.board.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardAttachDownloadDto")
public class BoardAttachDownloadDto {
	
	private Integer attachSeq;
	private Integer boardSeq;
	private Integer boardTypeSeq;
	private String orgFileNm;
	private String savePath;
	
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
}
