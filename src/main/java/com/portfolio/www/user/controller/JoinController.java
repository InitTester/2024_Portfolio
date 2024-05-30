package com.portfolio.www.user.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.user.dto.MemberDto;
import com.portfolio.www.user.message.MemberMessageEnum;
import com.portfolio.www.user.service.MemberService;

@Controller
public class JoinController {
	private final static Logger log = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private MemberService memberService;
	
	/* 회원가입 페이지 */
	@GetMapping("/auth/joinPage.do")
	public ModelAndView joinPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/join");
		
		return mv;
	}
	
	/* 회원가입 실행 */
	@PostMapping("/auth/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		
		getLogMessage(log,"join","로그","로그인 페이지 접속");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		String passwd = params.get("passwd");
		
		int result = memberService.join(params, request);	
		MemberDto dto = new MemberDto().getMemberDto(params);
		dto.setPasswd(passwd);
		
		mv.addObject("result", result);
		
		getLogMessage(log, "join", "result", result);
		getLogMessage(log, "join", "result==101", result==101);
		
		if(result == 101) {			
			mv.addObject("code",MemberMessageEnum.EXISTS_JOIN_ID.getCode());
			mv.addObject("msg",MemberMessageEnum.EXISTS_JOIN_ID.getDescription());	
			mv.addObject("dto", dto);
			mv.setViewName("auth/join");			
		}else if(result == 102) {
			mv.addObject("code",MemberMessageEnum.EXISTS_JOIN_EMAIL.getCode());
			mv.addObject("msg",MemberMessageEnum.EXISTS_JOIN_EMAIL.getDescription());	
			mv.addObject("dto", dto);
			mv.setViewName("auth/join");			
		}else {
			mv.addObject("code", result == 1 ? MemberMessageEnum.SUCCESS_JOIN.getCode() : MemberMessageEnum.FAIL_JOIN.getCode());
			mv.addObject("msg", result == 1 ? MemberMessageEnum.SUCCESS_JOIN.getDescription() : MemberMessageEnum.FAIL_JOIN.getDescription());
			mv.setViewName("redirect:/auth/loginPage.do");
		}
			
		return mv;
	}

	/* 이메일 인증 실행 */
	@GetMapping("/emailAuth.do")
	public ModelAndView emailAuth(@RequestParam("uri") String uri) {
		ModelAndView mv = new ModelAndView();
		
		boolean result = false;
		
		/* Exception 각각 처리 */
		try {
			result = memberService.emailAuth(uri);
			
			mv.addObject("result",result);
			mv.addObject("code",MemberMessageEnum.SUCCESS_AUTH_EMAIL.getCode());
			mv.addObject("msg",MemberMessageEnum.SUCCESS_AUTH_EMAIL.getDescription());
			mv.setViewName("redirect:/auth/loginPage.do");
//			mv.addObject("msg","인증되었습니다");	
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			mv.addObject("result",result);
			mv.addObject("code",MemberMessageEnum.FAIL_AUTH_EMAIL_ERR1.getCode());
			mv.addObject("msg",MemberMessageEnum.FAIL_AUTH_EMAIL_ERR1.getDescription());
			mv.setViewName("index");
			/* mv.addObject("msg","uri 값이 올바르지 않습니다."); */
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			mv.addObject("result",result);
			mv.addObject("code",MemberMessageEnum.FAIL_AUTH_EMAIL_ERR2.getCode());
			mv.addObject("msg",MemberMessageEnum.FAIL_AUTH_EMAIL_ERR2.getDescription());
			mv.setViewName("index");
//			mv.addObject("msg","잘못된 uri 입니다.");
			
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			mv.addObject("result",result);
			mv.addObject("code",MemberMessageEnum.FAIL_AUTH_EMAIL_ERR3.getCode());
			mv.addObject("msg",MemberMessageEnum.FAIL_AUTH_EMAIL_ERR3.getDescription());
//			mv.addObject("msg","인증메일 유효시간 만료");
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	public static Logger getLogMessage(Logger log, String methodName, String logKey, Object logValue) {
		log.info("["+methodName+ "] (" + logKey + ") : " + logValue);
		return log;
	}
}