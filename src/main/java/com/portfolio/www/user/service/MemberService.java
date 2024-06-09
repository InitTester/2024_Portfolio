package com.portfolio.www.user.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.user.dao.mybatis.MemberAuthRepository;
import com.portfolio.www.user.dao.mybatis.MemberRepository;
import com.portfolio.www.user.dto.EmailDto;
import com.portfolio.www.user.dto.MemberAuthDto;
import com.portfolio.www.user.dto.MemberDto;
import com.portfolio.www.user.message.MemberMessageEnum;
import com.portfolio.www.user.util.EmailUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class MemberService {
	private final static Logger log = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberAuthRepository authRepository;

	@Autowired
	private EmailUtil emailUtil;

	/* 회원가입 */
	public int join(HashMap<String, String> params, HttpServletRequest request) {

		String encPasswd = getBCrypt(params.get("passwd"));
//		String email = getBCrypt(params.get("email"));
		String email = params.get("email");

		/* 암호화된 값 저장 */
		params.put("passwd", encPasswd);
		params.put("email", email);

		String memberId = params.get("memberId");
		int memberIdCnt = memberRepository.getMemberIdCnt(memberId);
		int memberEmailCnt = memberRepository.getMemberEmailCnt(email);

		CommonUtil.getLogMessage(log, "join", "memberId", memberId);
		CommonUtil.getLogMessage(log, "join", "memberCnt", memberIdCnt);
		CommonUtil.getLogMessage(log, "join", "memberEmailCnt", memberEmailCnt);

		/* id 중복체크 */
		if (memberIdCnt > 0) {
			return Integer.parseInt(MemberMessageEnum.EXISTS_JOIN_ID.getCode());
		}

		/* email 중복체크 */
		if (memberEmailCnt > 0) {
			return Integer.parseInt(MemberMessageEnum.EXISTS_JOIN_EMAIL.getCode());
		}

		int cnt = memberRepository.join(params);
		int memberSeq = memberRepository.getMemberSeq(params.get("memberId"));

		/* 가입 성공 row 1 */
		if (cnt == 1) {
			/* 인증 메일 구조 */
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 30);
			
			MemberAuthDto authDto = MemberAuthDto.setMemberAuthDto(memberSeq, uuid, calendar.getTimeInMillis());

			/* db 추가 */
			try {
				authRepository.addMemberAuth(authDto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
			
			// from 가져오기, 실제 값 가져오기			
			String from = CommonUtil.getPropertiesValue("application.properties", "app.username") + "@naver.com";
			
			CommonUtil.getLogMessage(log, "join", "from( ",from+ " )");
			CommonUtil.getLogMessage(log, "join", "contextroot", request.getContextPath());

			String html = "<a href='http://localhost:8080" + request.getContextPath() + "/emailAuth.do?uri="
					+ authDto.getAuthUri() + "'>인증하기</a>";

			CommonUtil.getLogMessage(log, "join", "html", html);

			/* 인증 메일 발송 */
			EmailDto emailDto = EmailDto.setEmailDto(from, email, MemberMessageEnum.SUCCESS_JOIN.getDescription(), html);			
			emailUtil.sendMail(emailDto, true);
		}
		return cnt;
	}

	/* 메일 인증 처리 */
	public boolean emailAuth(String uri) throws TimeoutException {

		/* uri 가 null 또는 값이 없을 경우 */
		if (uri == null || uri.isEmpty()) {
			throw new NullPointerException();
		}

		/* 해당 uri가 auth 테이블에 있는지 확인 */
		MemberAuthDto dto = authRepository.getMemberAuth(uri);
		String dtoUri = dto.getAuthUri();

		if (dtoUri == null || dtoUri.isEmpty()) {
			throw new IllegalArgumentException();
		}

		int memberSeq = dto.getMemberSeq();
		long expireDtm = dto.getExpireDtm();
		String authNum = dto.getAuthNum();

		log.info("getMemberSeq : " + memberSeq + ", getAuthYn : " + dto.getAuthYn() + ", getExpireDtm : " + expireDtm);

		long now = Calendar.getInstance().getTimeInMillis(); // long
		boolean result = now < expireDtm;

		log.info("resutl : " + result);

		/* 시간 초과 */
		if (!result) {
			throw new TimeoutException();
		}

		memberRepository.updateMemberYN(memberSeq);
		authRepository.updateMemberAuthYN(uri);

		return result;
	}
	
	public MemberDto emailAuth(String uri, String passwd) throws TimeoutException {

		/* uri 가 null 또는 값이 없을 경우 */
		if (uri == null || uri.isEmpty()) {
			throw new NullPointerException();
		}

		/* 해당 uri가 auth 테이블에 있는지 확인 */
		MemberAuthDto dto = authRepository.getMemberAuth(uri);
		String dtoUri = dto.getAuthUri();

		if (dtoUri == null || dtoUri.isEmpty()) {
			throw new IllegalArgumentException();
		}

		int memberSeq = dto.getMemberSeq();
		long expireDtm = dto.getExpireDtm();
		String authNum = dto.getAuthNum();

		log.info("getMemberSeq : " + memberSeq + ", getAuthYn : " + dto.getAuthYn() + ", getExpireDtm : " + expireDtm);

		long now = Calendar.getInstance().getTimeInMillis(); // long
		boolean result = now < expireDtm;

		log.info("resutl : " + result);

		/* 시간 초과 */
		if (!result) {
			throw new TimeoutException();
		}
		
		passwd = getBCrypt(passwd);
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("member_id", memberSeq+"");
		param.put("passwd", passwd);
		
		memberRepository.updateMemberPass(param);
		authRepository.updateMemberAuthYN(uri);
		
		MemberDto memberdto = new MemberDto();
		String memberId = memberRepository.getMemberId(memberSeq);
		
		memberdto = memberRepository.getMemberInfo(memberId);

		return memberdto;
	}	

	/* 로그인 */
	public MemberDto login(HashMap<String, String> params) {

			String memberId = params.get("memberId");
			CommonUtil.getLogMessage(log, "login", "memberId", memberId);

			MemberDto memberDto = memberRepository.getMemberInfo(memberId);

			String passwd = params.get("passwd");
			CommonUtil.getLogMessage(log, "login", "passwd", passwd);

			String dbPasswd = memberDto.getPasswd();
			CommonUtil.getLogMessage(log, "login", "dbPasswd", memberDto.getPasswd());

			BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(), dbPasswd);

			if(result.verified) {
				return memberDto;
			}else {
				throw new EmptyResultDataAccessException(0202);
			}
	}

	/* bcry 변환 */
	public static String getBCrypt(String beforeData) {

		CommonUtil.getLogMessage(log, "join", "beforeData", beforeData);

		/* email,pwd 암호화 */
		String encData = BCrypt.withDefaults().hashToString(12, beforeData.toCharArray());
		CommonUtil.getLogMessage(log, "join", "encData", encData);

		BCrypt.Result result = BCrypt.verifyer().verify(beforeData.toCharArray(), encData);
		CommonUtil.getLogMessage(log, "join", "result.verified", result.verified);

		return encData;
	}

	/* 아이디찾기 */
	public String getMemberId(Integer memberSeq) {
		return memberRepository.getMemberId(memberSeq);
	}
	
	public MemberDto findmemberID(HashMap<String, String> params) {
		return memberRepository.findmemberID(params);
	}
	
	/* 회원 번호 */
	public Integer getMemberSeq(String memberId) {
		return memberRepository.getMemberSeq(memberId);
	}
	

	/* 비밀번호 찾기 */
	public int recoverPassSend(String memberId, String email, HttpServletRequest request) {
		
		/* 인증 메일 구조 */
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		int memberSeq = memberRepository.getMemberSeq(memberId);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 30);
		
		MemberAuthDto authDto = MemberAuthDto.setMemberAuthDto(memberSeq, uuid, calendar.getTimeInMillis());
		authDto.setAuthNum("passwordChange");
		
		/* db 추가 */
		try {
			authRepository.addMemberAuth(authDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		int emailCnt = memberRepository.getMemberEmailCnt(email);
		int memberIdCnt = memberRepository.getMemberIdCnt(memberId);
		
		CommonUtil.getLogMessage(log, "recoverPassSend", "emailCnt",emailCnt);
		CommonUtil.getLogMessage(log, "recoverPassSend", "memberIdCnt",memberIdCnt);
		
		if (emailCnt == 1 && memberIdCnt==1) {

			String from = CommonUtil.getPropertiesValue("application.properties", "app.username") + "@naver.com";
			
			CommonUtil.getLogMessage(log, "recoverPassSend", "from( ",from + " )");
			CommonUtil.getLogMessage(log, "recoverPassSend", "contextroot", request.getContextPath());
		
			String html = "<a href='http://localhost:8080" + request.getContextPath() + "/auth/resetPassPage.do?uri="
					+ authDto.getAuthUri() + "'>비밀번호 재설정하기</a>";

			CommonUtil.getLogMessage(log, "recoverPassSend", "html", html);

			/* 인증 메일 발송 */
			EmailDto emailDto = EmailDto.setEmailDto(from, email, MemberMessageEnum.CHECK_EMAIL.getDescription(), html);			
			emailUtil.sendMail(emailDto, true);
			 
			return memberIdCnt;
		} else {
			throw new EmptyResultDataAccessException(0203);
		}
	}
}
