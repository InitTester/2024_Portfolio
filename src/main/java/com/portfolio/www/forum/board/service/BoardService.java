package com.portfolio.www.forum.board.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dao.mybatis.BoardRepository;
import com.portfolio.www.forum.board.dao.mybatis.BoardVoteRepository;
import com.portfolio.www.forum.board.dto.BoardDto;
import com.portfolio.www.forum.board.dto.BoardVoteDto;

@Service
public class BoardService {
	private final static Logger log = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardVoteRepository boardVoteRepository;

	public String getBoardTypeNm(Integer boardTypeSeq) {
		return boardRepository.getBoardTypeNm(boardTypeSeq);
	}
	
	/* 게시글 전체가져오기 */
	public List<BoardDto> getBoardList(HashMap<String, Object> params){
		return boardRepository.getBoardList(params);
	}
	
	/* 게시글 조회수 */
	public int updateHit(String boardSeq) {
		return boardRepository.updateHit(boardSeq);
	}
	
	/* 게시글 상세조회 */
	public BoardDto getBoardDetail(String boardSeq) {
		return boardRepository.getBoardDetail(boardSeq);
	}
	
	public String getVote(HashMap<String, String> params) {
		
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq").toString());
		Integer boardSeq = Integer.parseInt(params.get("boardSeq").toString());
		String memberId= params.get("memberId").toString();
		
		BoardVoteDto boardVoteDto = new BoardVoteDto().getBoardVoteDto(boardTypeSeq, boardSeq, memberId, "", "");
		
		CommonUtil.getLogMessage(log, "getVote", "boardVoteDto", boardVoteDto);
		System.out.println(boardVoteDto);
		
		return boardVoteRepository.getVote(boardVoteDto);
	}
	
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
	
	
	
	
	
	
	
	
	
	/* Util */	
	/* 페이징 처리를 위한 전체 페이지 */
	public HashMap<String, Integer> getBoardPageInfo(Integer boardTypeSeq, Integer size, Integer page) {

		HashMap<String, Integer> pageHandler = new HashMap<String, Integer>();

		/*
		 * pageHandler에 필요한 변수 선언 
		 * totalPage 전체 게시물 개수 
		 * page 현재 페이지 번호 
		 * size 페이지당 게시물 수
		 * begin 시작페이지 
		 * end 끝 페이지
		 * prev 이전 화살표 
		 * next 다음 화살표
		 */

		int totalPage = boardRepository.getBoardTotalCnt(boardTypeSeq);
		int totalPageSize = totalPage / size;

		int begin = page == 0 ? 1 : ((page - 1) / size) * size + 1;
		int end = Math.min(begin + size - 1, totalPageSize);

		int prev = begin;
		int next = end != totalPage ? end : totalPage;

		pageHandler.put("totalPage", totalPage);
		pageHandler.put("totalPageSize", totalPageSize);
		pageHandler.put("begin", begin);
		pageHandler.put("end", end);
		pageHandler.put("size", size);
		pageHandler.put("prev", prev);
		pageHandler.put("next", next);

		return pageHandler;
	}
}
