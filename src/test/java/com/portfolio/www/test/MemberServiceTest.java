package com.portfolio.www.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.user.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)	// junit5,
@ContextConfiguration(locations = {"classpath:context-beans-test.xml", "classpath:**/pf-servlet.xml"})
@Slf4j
class MemberServiceTest {
	private final static Logger log = LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Autowired
	private MemberService memberService;

	@Test
	void testJoin() {
		log.info("memberService : " + memberService);
		CommonUtil.getLogMessage(log, "testJoin", "memberService", memberService);
	}

	/*
	 * @Test void testEmailAuth() { fail("Not yet implemented"); }
	 * 
	 * @Test void testLogin() { fail("Not yet implemented"); }
	 * 
	 * @Test void testGetBCrypt() { fail("Not yet implemented"); }
	 */

}
