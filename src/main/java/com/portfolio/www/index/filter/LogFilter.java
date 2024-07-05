package com.portfolio.www.index.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
//, servletNames = { "pf" })
public class LogFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("[LogFilter] init ({})", "log filter init");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		String requestURI = httpRequest.getRequestURI();
		String uuid =UUID.randomUUID().toString();
		
		try {
			log.info("[LogFilter] REQUEST (uuid : {} , requestURI : {})", uuid, requestURI);
			chain.doFilter(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("[LogFilter] Exception ({},{})", e.getStackTrace(), e.getMessage());
		} finally{

			log.info("[LogFilter] RESPONSE (uuid : {} , requestURI : {})", uuid, requestURI);
		}
	}
	
	@Override
	public void destroy() {
		log.info("[LogFilter] destroy ({})", "log filter destroy");
	}
}
