package com.portfolio.www.user.controller;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.user.dto.MemberDto;
import com.portfolio.www.user.message.MemberMessageEnum;
import com.portfolio.www.user.service.MemberService;

import com.portfolio.www.common.util.CommonUtil;

@Controller
public class LoginController {
	private final static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	
	/* 로그인 페이지 */
	@GetMapping("/auth/loginPage.do")
	public ModelAndView loginPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		HttpSession session = request.getSession();
		
//		String memberId = (String)session.getAttribute("memberId");
//		mv.addObject("memberId",)
		
		mv.setViewName("auth/login");
		
		return mv;
	}
	
	/* 로그인 실행 */
	@PostMapping("/auth/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params,
			@RequestParam(name="rememberId", required = false, defaultValue = "") String rememberId,
			HttpServletRequest request,	HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView();
		MemberDto dto = new MemberDto().getMemberDto(params);
		
		try {
			CommonUtil.getLogMessage(log, "login", "로그", "login 메서드 접속");
			
			MemberDto memberDto = memberService.login(params);
			
			String memberId = memberDto.getMemberId();
			String memberSeq = memberDto.getMemberSeq().toString();
			String memberNm = memberDto.getMemberNm();
			//TODO 추후 개발 예정
//			String profileImg = memberDto.getMemberNm();
			
			CommonUtil.getLogMessage(log, "login", "rememberId", rememberId);
			
			if(!ObjectUtils.isEmpty(memberDto)) {
				
				// 세션 				
				HttpSession session = request.getSession();
				session.setAttribute("memberId", memberId);
				
				// 쿠키 
//				response.addCookie(CommonUtil.createCookie("memberId",memberId,-1,"/"));
				response.addCookie(CommonUtil.createCookie("memberSeq",memberSeq,-1,"/"));
				response.addCookie(CommonUtil.createCookie("memberNm",memberNm,-1,"/"));
//				setCookie("profileImg","profileImg",-1,"/");
				
				CommonUtil.getLogMessage(log, "login", "rememberId.isEmpty()", rememberId.toString().isEmpty());
				
				/* id 기억 쿠키 */
				if(!rememberId.toString().isEmpty()){
					response.addCookie(CommonUtil.createCookie("rememberId",memberId,-1,"/"));
				}else {
					response.addCookie(CommonUtil.createCookie("rememberId",memberId,0,"/"));
				}
				
				mv.addObject("key", Calendar.getInstance().getTimeInMillis());		
				mv.setViewName("redirect:/index.do");
				
			}
			
			/*
			 * else { // id,pwd 틀릴 시
			 * mv.addObject("code",MemberMessageEnum.INVALID_ID_OR_PASSWORD.getCode());
			 * mv.addObject("msg",MemberMessageEnum.INVALID_ID_OR_PASSWORD.getDescription())
			 * ; mv.addObject("dto",dto); mv.setViewName("auth/login"); }
			 */
			
			return mv;
			
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			mv.addObject("code",MemberMessageEnum.INVALID_ID_OR_PASSWORD.getCode());
			mv.addObject("msg",MemberMessageEnum.INVALID_ID_OR_PASSWORD.getDescription());	
			mv.addObject("dto",dto);
			mv.setViewName("auth/login");
			return mv;
		}
	}
	
	/*로그아웃*/
	@GetMapping("/auth/logout.do")
	public ModelAndView logOut(@RequestParam HashMap<String, String> params,
								 HttpServletResponse response, HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
        response.addCookie(CommonUtil.createCookie("memberId","",0,"/"));
        response.addCookie(CommonUtil.createCookie("profileImg","",0,"/"));
        response.addCookie(CommonUtil.createCookie("memberNm","",0,"/"));
        
        session.invalidate();
        
        // 로그 아웃 이후 처리는? 
        mv.setViewName("redirect:/index.do");
//		mv.setViewName("auth/login");
		
		return mv;
	}

	/* 아이디 찾기 */
	@GetMapping("/auth/findIdPage.do")
	public ModelAndView findIdPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/find-id");
		
		return mv;
	}	
	
	/* 비밀번호 재설정(id,이메일 확인 후 메일 전송) */
	@GetMapping("/auth/recoverPassPage.do")
	public ModelAndView recoverPassPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/recover-pass");
		
		return mv;
	}

	/* 비밀번호 재설정(비밀번호 재설정) */
	@GetMapping("/auth/resetPassPage.do")
	public ModelAndView resetPassPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/reset-password");
		
		return mv;
	}	
	
}
