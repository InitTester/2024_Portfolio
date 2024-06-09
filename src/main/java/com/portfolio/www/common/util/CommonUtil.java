package com.portfolio.www.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtil {
//	private final static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	public static Logger getLogMessage(Logger log, String methodName, String logKey, Object logValue) {
		log.info("["+methodName+ "] (" + logKey + ") : " + logValue.toString());
		
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
    
	/* properties 의 경로에서 특정 key로 값가져오기 */
    public static String getPropertiesValue(String properPath, String propertiesKey) {
    	Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {
			 // 클래스패스에서 파일 경로를 얻어옴
			String filePath = CommonUtil.class.getClassLoader().getResource(properPath).getFile();
	        File configFile = new File(filePath);
	         
		    input = new FileInputStream(configFile);		
			prop.load(input);  
    		 
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			String evnKey = System.getenv("APP_ENCRYPTION_PASSWORD");

			encryptor.setPassword(evnKey);
			
			String decry = prop.getProperty(propertiesKey);
			decry = decry.substring(4, decry.length()-1);
			
    		return encryptor.decrypt(decry);
    		 
    	} catch (IOException ioe) {
    		return ioe.getMessage();
    	} finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
