package com.portfolio.www.user.util;

import java.io.*;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.*;

import com.portfolio.www.common.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JasyptConfig {

	static String evnKey = System.getenv("APP_ENCRYPTION_PASSWORD");


	/*
	 * *.properties 생성 프로그램 JASYPT_ENCRYPTION_EMAIL : naver 계정
	 * JASYPT_ENCRYPTION_PWD : naver 계정 비밀번호 APP_ENCRYPTION_PASSWORD : encryption
	 * password DATA_USER : Datasource 계정 DATA_PWD : Datasource 비밀번호
	 * 
	 * 설명 해당 클래스를 실행 시키면 naver mail 전송 id/pass 에 대한 properties 생성합니다. id/password는
	 * 개인정보라서 암호화처리 하였습니다.
	 */
	public static void main(String[] args) {

//		setProperties("resource-common", evnKey);
//		setProperties("resource-dev", evnKey);
		setProperties("resource-local", evnKey);
		
		
	}

	// make  Properties local/dev/common
	public static void setProperties(String type, String evnKey) {
		
		/* 저장할 properties */
		Properties properties = new Properties();

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(evnKey);
		String fileName = "";
		
		if(type.equals("resource-common")) {	
			fileName = "email_config";
			String evnEmail = System.getenv("JASYPT_ENCRYPTION_EMAIL");
			String evnEmailPwd = System.getenv("JASYPT_ENCRYPTION_PWD");

			String encryptEmail = encryptor.encrypt(evnEmail);
			String encryptEmailPwd = encryptor.encrypt(evnEmailPwd);

			properties.setProperty("app.username", "ENC(" + encryptEmail + ")");
			properties.setProperty("app.password", "ENC(" + encryptEmailPwd + ")");

//		}else if(type.equals("resource-local")) {
		}else {
			fileName = "db_config";
			String evndbUrl = System.getenv("DB_URL");
			String evndbUserName = System.getenv("DB_USERNAME");
			String evndbPassWord = System.getenv("DB_USERPASSWORD");

			String encryptDBurl = encryptor.encrypt(evndbUrl);
			String encryptDBusername = encryptor.encrypt(evndbUserName);
			String encryptDBpassword = encryptor.encrypt(evndbPassWord);

			properties.setProperty("db.url", "ENC(" + encryptDBurl + ")");
			properties.setProperty("db.username", "ENC(" + encryptDBusername + ")");
			properties.setProperty("db.password", "ENC(" + encryptDBpassword + ")");
		}

		try {
			properties.store(new FileOutputStream("src/main/"+type+"/"+fileName+".properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Exception {}", e.getMessage());
			
		}
	}
}
