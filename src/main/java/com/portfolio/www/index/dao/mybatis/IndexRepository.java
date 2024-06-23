package com.portfolio.www.index.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.index.dto.IndexAttachViewDto;

@Repository
public interface IndexRepository {
	
	public List<IndexAttachViewDto> getIndexAttachView();

}
