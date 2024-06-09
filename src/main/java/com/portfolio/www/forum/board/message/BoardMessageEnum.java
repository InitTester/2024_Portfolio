package com.portfolio.www.forum.board.message;

public enum BoardMessageEnum {
	SUCCESS("0000", "게시글 작성이 완료 되었습니다"),
	FAIL("9999", "게시글 작성이 실패 되었습니다"),
	PAGEING_ERROR("9999","잘못된 페이지 접근입니다.");

	private String code;
	private String description;

	BoardMessageEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "MessageEnum{" + "Code='" + code + '\'' + ", Description='"
			+ description + '\'' + '}';
	}
	
}
