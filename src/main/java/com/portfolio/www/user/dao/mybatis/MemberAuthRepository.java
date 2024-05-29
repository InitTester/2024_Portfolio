package com.portfolio.www.user.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.portfolio.www.user.dto.MemberAuthDto;

@Repository
public interface MemberAuthRepository {

	public int addMemberAuth(MemberAuthDto authDto);
	
	public int updateMemberAuthYN(String auth_uri);
		
	public MemberAuthDto getMemberAuth(String uri) ;
}
