package com.portfolio.www.index.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {
	
	private static final String[] checklist = {"/forum/*/*Page.do"};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest  = (HttpServletRequest)request;
		HttpServletResponse httpResponse  = (HttpServletResponse)response;

		String contextPath = httpRequest.getContextPath();
		String queryString = httpRequest.getQueryString();
		String requestURI = httpRequest.getRequestURI().replaceAll(contextPath, "");
		
		try {
//			log.info("[LoginCheckFilter] LoginCheck Filter Start : ({})", requestURI);
			
			if(isLoginCheckPath(requestURI)) {
				
				if(!queryString.isEmpty())
					requestURI = requestURI+"?"+queryString;
				
				log.info("[LoginCheckFilter] redirect >>> requestURI: ({})", requestURI);
				
				log.info("[LoginCheckFilter] LoginCheck Filter Logic Start : ({})", requestURI);
				HttpSession session = httpRequest.getSession(false);
				
				if(session == null || ObjectUtils.isEmpty(session.getAttribute("memberSeq"))) {
					
					log.info("[LoginCheckFilter] Not certified User Request : ({})", requestURI);
					httpResponse.sendRedirect(contextPath+"/auth/loginPage.do?redirectURL="+requestURI);
					return;
				}
			}
			
			chain.doFilter(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			log.info("[LoginCheckFilter] LoginCheck Filter End : ({})", requestURI);
		}
	}
	
	private boolean isLoginCheckPath(String requestURI) {
		return PatternMatchUtils.simpleMatch(checklist, requestURI);
	}
}
