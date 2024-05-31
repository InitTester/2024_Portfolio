package com.portfolio.www.user.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

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
		if(memberIdCnt > 0) {
			return Integer.parseInt(MemberMessageEnum.EXISTS_JOIN_ID.getCode());
		}
		
		/* email 중복체크 */
		if(memberEmailCnt > 0) {
			return Integer.parseInt(MemberMessageEnum.EXISTS_JOIN_EMAIL.getCode());
		}
		
		int cnt = memberRepository.join(params);
		int memberSeq = memberRepository.getMemberSeq(params.get("memberId"));
		
		/* 가입 성공 row 1 */
		if(cnt == 1) {
			/* 인증 메일 구조 */
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			MemberAuthDto authDto = new MemberAuthDto().setMemberAuthDto(memberSeq, uuid);
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 30); 
			
			authDto.setExpireDtm(calendar.getTimeInMillis());
			
			CommonUtil.getLogMessage(log,"join","getExpireDtm",authDto.getExpireDtm());
			
			/* db 추가 */
			try {
				authRepository.addMemberAuth(authDto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}

			CommonUtil.getLogMessage(log,"join","System.getenv( ",System.getenv("JASYPT_ENCRYPTION_EMAIL")+"@naver.com" + " )");
			/* 인증 메일 발송 */
			EmailDto emailDto = new EmailDto();
			emailDto.setFrom(System.getenv("JASYPT_ENCRYPTION_EMAIL")+"@naver.com");
			emailDto.setReceiver(email);
			emailDto.setSubject("회원 가입 인증 확인해주세요.");
			
			/* 인증 확인을 위한 url 링크 처리 
			 * 현재 로컬에서 하기 때문에 주소는 localhost 사용이지만
			 * 추후 실제로 사용될 서버 주소로 변경하면 된다.*/			
			CommonUtil.getLogMessage(log,"join","contextroot",request.getContextPath());
			
			String html = "<a href='http://localhost:8080"+request.getContextPath()+"/emailAuth.do?uri="+authDto.getAuthUri()+"'>인증하기</a>";

			CommonUtil.getLogMessage(log,"join","html",html);
			
			emailDto.setText(html);			
			emailUtil.sendMail(emailDto,true);
		}
		return cnt;	
	}
	
	/*메일 인증 처리 */
	public boolean emailAuth(String uri) throws TimeoutException {
		
		/*uri 가 null 또는 값이 없을 경우*/
		if(uri==null||uri.isEmpty()) {
			throw new NullPointerException();
		}
		
		/*해당 uri가 auth 테이블에 있는지 확인*/
		MemberAuthDto dto = authRepository.getMemberAuth(uri);
		String dtoUri = dto.getAuthUri();
		
		if(dtoUri ==null || dtoUri.isEmpty()) {
			throw new IllegalArgumentException();
		}

		int memberSeq = dto.getMemberSeq();
		long expireDtm = dto.getExpireDtm();
		
		log.info("getMemberSeq : " + memberSeq + ", getAuthYn : " + dto.getAuthYn()+ ", getExpireDtm : " + expireDtm);
		
		long now = Calendar.getInstance().getTimeInMillis(); // long 
		boolean result = now < expireDtm;
		
		log.info("resutl : " + result);
		
		/*시간 초과*/
		if(!result) {
			throw new TimeoutException();
		}
		
		/*member ,memberAuth 테이블 yn 처리*/
		memberRepository.updateMemberYN(memberSeq);
		authRepository.updateMemberAuthYN(uri);
		
		return result;		
	}
	
	/* 로그인 */
	public MemberDto login(HashMap<String, String> params) {
		
		try {
			String memberId = params.get("memberId");			
			CommonUtil.getLogMessage(log, "login", "memberId", memberId);
			
			MemberDto memberDto = memberRepository.getMemberInfo(memberId);
			
			String passwd = params.get("passwd");
			CommonUtil.getLogMessage(log, "login", "passwd", passwd);
			
			String dbPasswd = memberDto.getPasswd();
			CommonUtil.getLogMessage(log, "login", "dbPasswd", memberDto.getPasswd());
			
			BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(),dbPasswd);
			
			return result.verified ? memberDto : null ;
			
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			throw new EmptyResultDataAccessException(0202);
			/* throw new EmptyResultDataAccessException("존재하지 않는 사용자 입니다.",0202); */
		}
	}
	
	/*
	 * public static void main(String[] args) { String get = getBCrypt("j123123!");
	 * System.out.println(get); }
	 */
	public static String getBCrypt(String beforeData) {
		
		CommonUtil.getLogMessage(log, "join", "beforeData", beforeData);
		
		/* email,pwd 암호화 */
		String encData  = BCrypt.withDefaults().hashToString(12,beforeData.toCharArray());		
		CommonUtil.getLogMessage(log, "join", "encData", encData);
		
		BCrypt.Result result = BCrypt.verifyer().verify(beforeData.toCharArray(), encData);		
		CommonUtil.getLogMessage(log, "join", "result.verified", result.verified);
		
		return encData;
	}
}
