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
	public Integer getBoardSeq() {
		return boardSeq;
	}
	public Integer getBoardTypeSeq() {
		return boardTypeSeq;
	}
	public String getOrgFileNm() {
		return orgFileNm;
	}
	public String getSavePath() {
		return savePath;
	}
	public String getChngFileNm() {
		return chngFileNm;
	}
	public long getFileSize() {
		return fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public String getAccessUri() {
		return accessUri;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public int getHit() {
		return hit;
	}
	
}
