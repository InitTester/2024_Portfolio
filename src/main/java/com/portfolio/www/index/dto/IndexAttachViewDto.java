package com.portfolio.www.index.dto;

import org.apache.ibatis.type.Alias;

@Alias("IndexAttachViewDto")
public class IndexAttachViewDto {

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
	private int hit;
	
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	

}
