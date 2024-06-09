package com.portfolio.www.forum.board.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardVoteDto")
public class BoardVoteDto {
	
	private Integer boardSeq;
	private Integer boardTypeSeq;
	private Integer memberSeq;
	private String isLike;
	private String ip;
	private String regDtm;
	
	@Override
	public String toString() {
		return "BoardVoteDto [boardSeq=" + boardSeq + ", boardTypeSeq=" + boardTypeSeq + ", memberSeq=" + memberSeq
				+ ", isLike=" + isLike + ", ip=" + ip + ", regDtm=" + regDtm + "]";
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
	public Integer getmemberSeq() {
		return memberSeq;
	}
	public void setmemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	
	public static BoardVoteDto getBoardVoteDto(Integer boardTypeSeq, Integer boardSeq, Integer memberSeq, String isLike, String ip) {
		
		BoardVoteDto dto = new BoardVoteDto();
		dto.setBoardTypeSeq(boardTypeSeq);
		dto.setBoardSeq(boardSeq);
		dto.setmemberSeq(memberSeq);
		dto.setIp(ip);
		dto.setIsLike(isLike);
		
		return dto;
	}
}
