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
	public BoardAttachDto getBoardAttach(Integer attachSeq);

	/* 다운로드 수 */
	public int viewsDownloadHit(Integer attachSeq);
	
	/* 선택 게시글 첨부파일 여부 */
	public int getBoardAttachEmpty(BoardAttachDto boardDto);

	/* 선택 게시글 첨부파일 중복체크 */
	public int getBoardAttachOneEmpty(BoardAttachDto boardDto);
	
	/* 선택 게시글 첨부파일 전체 삭제 */
	public int deleteBoardAttachAll(BoardAttachDto boardDto);
}
