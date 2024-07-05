package com.portfolio.www.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.www.index.dao.mybatis.IndexRepository;
import com.portfolio.www.index.dto.IndexAttachViewDto;
import com.portfolio.www.index.dto.IndexBoardViewDto;
import com.portfolio.www.index.dto.IndexContactMeDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class IndexService {
	
	@Autowired
	private IndexRepository indexrepository;
	
	public List<IndexAttachViewDto> getindexAttachView() {
		return indexrepository.getIndexAttachView();
	}
	
	public List<IndexBoardViewDto> getIndexBoardView() {
		return indexrepository.getIndexBoardView();
	}
	
	public int addContactMe(IndexContactMeDto contactMeDto) {
		return indexrepository.addContactMe(contactMeDto);
	};

}
