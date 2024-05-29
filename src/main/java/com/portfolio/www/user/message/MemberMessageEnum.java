package com.portfolio.www.user.message;

/*Enum 은 bean 등록이 필요 없다(싱글톤이라서 런타임 되면 JVM에 자동으로 올라간다)
 * 그래서 선언 필요 없고, 객체 생성 안해도 사용 가능 */
public enum MemberMessageEnum {
	SUCCESS("0000", "Request Success"),
	
	/* JOIN */
	SUCCESS_JOIN("0000", "Request Success"),
	EXISTS_JOIN_ID("0101", "이미 사용중인 아이디입니다. 다른 아이디를 사용해주세요."),
	
	SUCCESS_SEND_EMAIL("1000", "이메일 발송 : 성공하였습니다."),
	
	SUCCESS_AUTH_EMAIL("1000", "이메일 인증 : 성공하였습니다."),	
	FAIL_AUTHEMAIL_ERR("1001", "이메일 인증 : 실패하였습니다."),
	
	FAIL_AUTH_EMAIL_ERR1("1002", "이메일 인증 : URI 값이 올바르지 않습니다."),
	FAIL_AUTH_EMAIL_ERR2("1003", "이메일 인증 : 잘못된 URI 입니다."),
	FAIL_AUTH_EMAIL_ERR3("1003", "이메일 인증 : 인증메일 유효시간 만료되었습니다."),
	
	PAGEING_ERROR("9999","잘못된 페이지 접근입니다.");
	
	private String code;
	private String description;

	MemberMessageEnum(String code, String description) {
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