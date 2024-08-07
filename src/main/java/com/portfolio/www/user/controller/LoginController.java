package com.portfolio.www.user.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.READER;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.www.user.dto.MemberDto;
import com.portfolio.www.user.message.MemberMessageEnum;
import com.portfolio.www.user.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import com.portfolio.www.common.util.CommonUtil;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	/* 로그인 페이지 */
	@GetMapping("/auth/loginPage.do")
	public ModelAndView loginPage(@RequestParam HashMap<String, String> params,
//							      @RequestParam(required=false) String boardTypeSeq,
								  HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		if(session.getAttribute("memberSeq")!=null) {		

			Integer memberSeq =  Integer.parseInt(session.getAttribute("memberSeq").toString());   //  Integer.parseInt(CommonUtil.getCookieValue(request, "memberSeq"));
			String memberId = memberService.getMemberId(memberSeq);
			
			mv.addObject("memberId",memberId);
		}		
		
		log.info("request.getQueryString() : {}",request.getQueryString());
		mv.addObject("redirectURL",(request.getQueryString()==null ? "" : request.getQueryString().split("redirectURL=")[1]));
		mv.setViewName("auth/login");
		
		return mv;
	}
	
	/* 로그인 실행 */
	@PostMapping("/auth/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params,
			@RequestParam(name="rememberId", required = false, defaultValue = "") String rememberId,
		    @RequestParam(name ="redirectURL", required=false) String redirectURL,
			HttpServletRequest request,	
			HttpServletResponse response,
		    RedirectAttributes redirectAttributes){
		
		ModelAndView mv = new ModelAndView();
		MemberDto dto = MemberDto.getMemberDto(params);

		try {
			MemberDto memberDto = memberService.login(params);

			String memberId = memberDto.getMemberId();
			String memberNm = memberDto.getMemberNm();
			Integer memberSeq = memberService.getMemberSeq(memberId);

			//TODO 추후 개발 예정
//			String profileImg = memberDto.getMemberNm();			
//			CommonUtil.getLogMessage(log, "login", "memberSeq", memberSeq);
			
			if(!ObjectUtils.isEmpty(memberDto)) {

				// 세션 				
				HttpSession session = request.getSession();
				session.setAttribute("memberSeq", memberSeq);
				
				// 쿠키 
				response.addCookie(CommonUtil.createCookie("memberId",memberId,-1,"/"));
				response.addCookie(CommonUtil.createCookie("memberSeq",String.valueOf(memberSeq),-1,"/"));
				response.addCookie(CommonUtil.createCookie("memberNm",memberNm,-1,"/"));
//				setCookie("profileImg","profileImg",-1,"/");
				
				/* id 기억 쿠키 */
				if(!rememberId.toString().isEmpty()){
					response.addCookie(CommonUtil.createCookie("rememberSeq",String.valueOf(memberSeq),-1,"/"));
				}else {
					response.addCookie(CommonUtil.createCookie("rememberSeq",String.valueOf(memberSeq),0,"/"));
				}
				
				mv.addObject("key", Calendar.getInstance().getTimeInMillis());	
				CommonUtil.getLogMessage(log, "login", "redirectURL", redirectURL);
				mv.setViewName("redirect:" + (redirectURL=="" ? "/index.do" : redirectURL));
			}
			return mv;
			
		} catch (EmptyResultDataAccessException e) {
			//TODO 에러 처리 세분화 하기
			mv.addObject("code",MemberMessageEnum.INVALID_ID_OR_PASSWORD.getCode());
			mv.addObject("msg",MemberMessageEnum.INVALID_ID_OR_PASSWORD.getDescription());
			mv.addObject("dto",dto);
			mv.addObject("redirectURL", redirectURL);
			mv.setViewName("auth/login");
			return mv;
		} catch (Exception e) {
			mv.addObject("code",e.getCause()); 
			mv.addObject("msg", e.getMessage());
		  	mv.addObject("dto",dto);
		  	mv.addObject("redirectURL", redirectURL);
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
        response.addCookie(CommonUtil.createCookie("memberSeq","",0,"/"));
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

		log.info("params :",params);
		
		mv.setViewName("auth/reset-password");
		
		return mv;
	}	
}
