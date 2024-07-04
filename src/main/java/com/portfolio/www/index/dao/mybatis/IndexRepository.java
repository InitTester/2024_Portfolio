package com.portfolio.www.index.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.index.dto.IndexAttachViewDto;
import com.portfolio.www.index.dto.IndexBoardViewDto;
import com.portfolio.www.index.dto.IndexContactMeDto;

@Repository
public interface IndexRepository {
	
	public List<IndexAttachViewDto> getIndexAttachView();
	
	public List<IndexBoardViewDto> getIndexBoardView();
	
	public int addContactMe(IndexContactMeDto contactMeDto);
}
