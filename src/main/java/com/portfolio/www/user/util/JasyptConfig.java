package com.portfolio.www.user.util;

import java.io.*;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.*;

import com.portfolio.www.common.util.CommonUtil;

public class JasyptConfig {
	private final static Logger log = LoggerFactory.getLogger(JasyptConfig.class);
	
	/* application.properties 생성 프로그램 
	 * JASYPT_ENCRYPTION_EMAIL : naver 계정
	 * JASYPT_ENCRYPTION_PWD : naver 계정 비밀번호
	 * APP_ENCRYPTION_PASSWORD : encryption password
	 * 
	 * 설명 
	 * 해당 클래스를 실행 시키면 naver mail 전송 id/pass 에 대한 properties 생성합니다.
	 * id/password는 개인정보라서 암호화처리 하였습니다.
	 * */
	public static void main(String[] args) {
		
		/*저장할 properties*/
		Properties properties = new Properties();
		
		/*필요한 변수 선언*/
		String evnEmail = System.getenv("JASYPT_ENCRYPTION_EMAIL");
		String evnEmailPwd = System.getenv("JASYPT_ENCRYPTION_PWD");
		String evnKey = System.getenv("APP_ENCRYPTION_PASSWORD");
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		CommonUtil.getLogMessage(log, "properties 생성", "evnKey", evnKey);
		CommonUtil.getLogMessage(log, "properties 생성", "evnEmail", evnEmail);
		CommonUtil.getLogMessage(log, "properties 생성", "evnEmailPwd", evnEmailPwd);
		
		/* 암호화에 쓰일 key 값 지정 */
		encryptor.setPassword(evnKey);

		String encryptEmail = encryptor.encrypt(evnEmail);
		String encryptEmailPwd = encryptor.encrypt(evnEmailPwd);

		CommonUtil.getLogMessage(log, "properties 생성", "encryptEmail", encryptEmail);
		CommonUtil.getLogMessage(log, "properties 생성", "encryptEmailPwd", encryptEmailPwd);
		
		CommonUtil.getLogMessage(log, "properties 생성", "encryptEmail -> decrypt", encryptor.decrypt(encryptEmail));
		CommonUtil.getLogMessage(log, "properties 생성", "encryptEmailPwd -> decrypt", encryptor.decrypt(encryptEmailPwd));
		
		/* 변환한 encrypt 값을 properties 값을 지정하고 저장하기 
		 * encrypt 값에는 "ENC(변환값)"으로 지정해 주어야 한다.
		 * */
		
		properties.setProperty("app.username", "ENC("+encryptEmail+")");
		properties.setProperty("app.password", "ENC("+encryptEmailPwd+")");
//		properties.setProperty("app.encryptKey", evnKey);		
		
		/*지정값 저장*/
		try {
			properties.store(new FileOutputStream("src/main/resources/application.properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info(e.getMessage());
		}
	}
}
