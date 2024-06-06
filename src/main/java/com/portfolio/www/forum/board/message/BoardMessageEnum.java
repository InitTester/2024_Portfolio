package com.portfolio.www.forum.board.message;

public enum BoardMessageEnum {
	SUCCESS("0000", "Request Success"),
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
