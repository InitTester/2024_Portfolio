package com.portfolio.www.forum.board.dto;

import org.apache.ibatis.type.Alias;

@Alias("BoardVoteDto")
public class BoardVoteDto {
	
	private Integer boardSeq;
	private Integer boardTypeSeq;
	private String memberId;
	private String isLike;
	private String ip;
	private String regDtm;
	
	@Override
	public String toString() {
		return "BoardVoteDto [boardSeq=" + boardSeq + ", boardTypeSeq=" + boardTypeSeq + ", memberId=" + memberId
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
	public String getmemberId() {
		return memberId;
	}
	public void setmemberId(String memberId) {
		this.memberId = memberId;
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
	
	public static BoardVoteDto getBoardVoteDto(Integer boardTypeSeq, Integer boardSeq, String memberId, String isLike, String ip) {
		
		BoardVoteDto dto = new BoardVoteDto();
		dto.setBoardTypeSeq(boardTypeSeq);
		dto.setBoardSeq(boardSeq);
		dto.setmemberId(memberId);
		dto.setIp(ip);
		dto.setIsLike(isLike);
		
		return dto;
	}
}
