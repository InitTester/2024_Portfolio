package com.portfolio.www.user.controller;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.user.dto.MemberDto;
import com.portfolio.www.user.message.MemberMessageEnum;
import com.portfolio.www.user.service.MemberService;

@RestController
public class RestLoginController {
	private final static Logger log = LoggerFactory.getLogger(RestLoginController.class);

	@Autowired
	private MemberService memberService;

	/* 아이디 찾기 */
	@PostMapping("/auth/findid.do")
	public ResponseEntity<MemberDto> findId(@RequestParam("memberNm") String memberNm,
			@RequestParam("email") String email) {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("memberNm", memberNm);
		params.put("email", email);
		
		MemberDto dto = MemberDto.getMemberDto(params);
		
		try {
			dto = memberService.findmemberID(params);
			
//			CommonUtil.getLogMessage(log, "findId", "memberIsd", dto.getMemberId());
			return ResponseEntity.ok(dto);
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info("EmptyResultDataAccessException : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info("Exception : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
		}
	}

	/* 비밀번호 찾기 이메일 전송 */
	@PostMapping("/auth/recoverPassSend.do")
	public ResponseEntity<Integer> recoverPass(@RequestParam("memberId") String memberId, @RequestParam("email") String email,
			HttpServletRequest request) {
		int result= -1;
		
		try {
			result = memberService.recoverPassSend(memberId, email, request);
			
			CommonUtil.getLogMessage(log, "recoverPass", "result", result);
			
			return ResponseEntity.ok(result);
			
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	

	@PostMapping("/auth/resetPass.do")
	public ResponseEntity<MemberDto> resetPass(@RequestParam("uri") String uri, @RequestParam("passwd") String passwd, 
			RedirectAttributes redirectAttributes) {
		
		MemberDto result = new MemberDto();
		 
		/*
		 * CommonUtil.getLogMessage(log, "resetPass", "uri", uri);
		 * CommonUtil.getLogMessage(log, "resetPass", "passwd", passwd);
		 */

		try {
			result = memberService.emailAuth(uri, passwd);
			CommonUtil.getLogMessage(log, "findId", "memberId", result.getMemberId());
			return ResponseEntity.ok(result);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info("[resetPass] (TimeoutException) : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info("[resetPass] (Exception) : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
			
			
}
