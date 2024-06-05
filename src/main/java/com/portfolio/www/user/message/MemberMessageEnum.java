package com.portfolio.www.user.message;

/*Enum 은 bean 등록이 필요 없다(싱글톤이라서 런타임 되면 JVM에 자동으로 올라간다)
 * 그래서 선언 필요 없고, 객체 생성 안해도 사용 가능 */
public enum MemberMessageEnum {
	SUCCESS("0000", "Request Success"),
	
	/* JOIN */
//	SUCCESS_JOIN("0100", "회원가입 되셨습니다. 서비스를 사용하기 위해 인증메일을 확인해주세요.\n 인증을 하지 않으면 이용하실 수 없습니다."),
	SUCCESS_JOIN("0100", "회원가입 되셨습니다. 서비스를 사용하기 위해 인증메일을 확인해주세요. 인증을 하지 않으면 이용하실 수 없습니다."),
	FAIL_JOIN("0200", "회원가입 실패하셨습니다. 다시 가입해 주시길 바랍니다."),
	EXISTS_JOIN_ID("0101", "이미 사용중인 아이디입니다. 다른 아이디를 사용해주세요."),
	EXISTS_JOIN_EMAIL("0102", "이미 가입한 이메일주소 입니다. 이메일 주소를 확인해주세요."),
	
	SUCCESS_SEND_EMAIL("1000", "이메일 발송 : 성공하였습니다."),	
	SUCCESS_AUTH_EMAIL("1000", "이메일 인증 : 성공하였습니다."),	
	FAIL_AUTHEMAIL_ERR("1001", "이메일 인증 : 실패하였습니다."),
	
	FAIL_AUTH_EMAIL_ERR1("1002", "이메일 인증 : URI 값이 올바르지 않습니다."),
	FAIL_AUTH_EMAIL_ERR2("1003", "이메일 인증 : 잘못된 URI 입니다."),
	FAIL_AUTH_EMAIL_ERR3("1003", "이메일 인증 : 인증메일 유효시간 만료되었습니다."),
	
	SUCCESS_CHANGE_PASSWORD_SEND_EMAIL("1100", "인증 메일이 발송되었습니다. 이메일을 확인해 주세요."),
	
	USER_NOT_FOUND("0202", "존재하지 않는 사용자 입니다."),
	INVALID_ID_OR_PASSWORD("0203", "아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해 주세요."),
	INVALID_ID_OR_EMAIL("0204", "아이디 또는 이메일주소가 일치하지 않습니다. 다시 입력해 주세요."),
	CHECK_EMAIL("3000","이메일을 확인해주세요."),
	PAGEING_ERROR("9999","잘못된 페이지 접근입니다.")
	
	
	
	;
	
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