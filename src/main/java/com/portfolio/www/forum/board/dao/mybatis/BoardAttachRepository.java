package com.portfolio.www.forum.board.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.forum.board.dto.BoardAttachDto;

@Repository
public interface BoardAttachRepository {
	
	/* 첨부파일 추가 */
	public int addBoardAttach(BoardAttachDto attachDto);
	
	/* 첨부파일 리스트 */
	public List<BoardAttachDto> getBoardAttachAll(BoardAttachDto attachDto);
	
	/* 첨부파일 정보 */
	public BoardAttachDto getBoardAttach(BoardAttachDto attachDto);
}
