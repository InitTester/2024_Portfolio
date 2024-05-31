package com.portfolio.www.user.dao.mybatis;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.portfolio.www.user.dto.MemberDto;

@Repository
public interface MemberRepository {
	
	/* 회원 가입 여부 id 중복체크*/
	public int getMemberIdCnt(String memberId);
	
	/* 회원 가입 여부 email 중복체크*/
	public int getMemberEmailCnt(String email);
	
	public int getMemberSeq(String memberId);
	
	/* 회원정보 */
	public MemberDto getMemberInfo(String memberId);
	
	/* 회원가입 */
	public int join(HashMap<String,String> params);
	
	/* 회원가입 테이블 인증 업데이트 */
	public int updateMemberYN(Integer memberSeq);	
}