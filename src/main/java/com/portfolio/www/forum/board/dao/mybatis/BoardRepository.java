package com.portfolio.www.forum.board.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.forum.board.dto.BoardDto;

@Repository 
public interface BoardRepository {

	/* 게시판타입 이름 */
	public String getBoardTypeNm(Integer boardTypeSeq);
	
	/* 게시판별 총 갯수 */
	public int getBoardTotalCnt(Integer boardTypeSeq);
	
	/* 게시판별 총 정보 */
	public List<BoardDto> getBoardList(HashMap<String, Object> params);
	
	/* 게시글 상세 조회 */
	public BoardDto getBoardDetail(String boardSeq);
	
	/* 게시판 조회수 */
	public int updateHit(String boardSeq);
	
	
	
}
