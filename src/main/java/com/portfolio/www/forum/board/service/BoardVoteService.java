package com.portfolio.www.forum.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.www.forum.board.dao.mybatis.BoardVoteRepository;
import com.portfolio.www.forum.board.dto.BoardVoteDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardVoteService {

	@Autowired
	private BoardVoteRepository boardVoteRepository;
	
	/* 좋아요/싫어요 상태*/
	public String getVote(BoardVoteDto boardVoteDto) {
		return boardVoteRepository.getVote(boardVoteDto);
	}
	
	/* 좋아요/싫어요 등록/수정/삭제 */
	@Transactional
	public int setVote(BoardVoteDto boardVoteDto) {
		
		/* 좋아요/싫어요 기능 
		 * 1. 값이 없을 때 insert
		 * 2. 값이 있으면서 다를때 update
		 * 3. 값이 있으면서 같을때 delete 
		 * */
		
		/* 1. 해당 정보에 값이 있는지 확인 
		 * 2. 없다면 추가
		 * 3. 있다면 저장 값비교 처리
		 * */
		
		int resultVlue= -1;
		
		String beforeisList = boardVoteRepository.getVote(boardVoteDto);
		
		log.info("beforeisList : {}",beforeisList);
		
		if(beforeisList == null) {
			boardVoteRepository.addVote(boardVoteDto);
			resultVlue = 1;
		} else if(!beforeisList.equals(boardVoteDto.getIsLike())){
			boardVoteRepository.updateVote(boardVoteDto);
			resultVlue = 2;
		} else {
			// update
			boardVoteRepository.deleteVote(boardVoteDto);
			resultVlue = 3;
		}
		
		return resultVlue;
	}
}
