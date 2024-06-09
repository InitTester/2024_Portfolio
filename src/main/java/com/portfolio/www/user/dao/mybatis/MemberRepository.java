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
	
	/* 회원번호 */
	public int getMemberSeq(String memberId);
	
	/* 회원아이디 */
	public String getMemberId(Integer memberSeq);
	
	/* 회원정보 */
	public MemberDto getMemberInfo(String memberId);
	
	/* 회원가입 */
	public int join(HashMap<String,String> params);
	
	/* 회원가입 테이블 인증 업데이트 */
	public int updateMemberYN(Integer memberSeq);	
	
	/* id 찾기 */
	public MemberDto findmemberID(HashMap<String,String> params);
	
	/* 비밀번호 변경 */
	public int updateMemberPass(HashMap<String,String> params);	
}
