package com.portfolio.www.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	private final static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	public static Logger getLogMessage(Logger log, String methodName, String logKey, Object logValue) {
		log.info("["+methodName+ "] (" + logKey + ") : " + logValue);
		return log;
	}
	
    public static Cookie createCookie(String cookieKey, String cookieValue, int maxAge, String path){
    	
        String encodedValue = cookieValue;
        
//        try { encodedValue = URLEncoder.encode(cookieValue, "UTF-8"); }
//        catch (UnsupportedEncodingException e) { throw new RuntimeException(e); }
        
       getLogMessage(log, "createCookie", "encodedValue", encodedValue);
        
        Cookie cookie = new Cookie(cookieKey,encodedValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
    }
	
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열이 null이 아니고, 각 쿠키를 확인하여 원하는 쿠키의 값을 찾음
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if(cookie.getName().equals(cookieName) && cookieName.equals("user_profileImg"))
                    {
                        try {
                            return URLDecoder.decode(cookie.getValue(),"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        return cookie.getValue();
                    }
                }
            }
        }

        return null;
    }
}
