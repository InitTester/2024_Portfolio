package com.portfolio.www.forum.board.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.portfolio.www.forum.board.dto.BoardVoteDto;

@Repository
public interface BoardVoteRepository {
	
	/* 좋아요/싫어요 값확인 */
	public String getVote(BoardVoteDto boardVoteDto);
	
	/* 좋아요/싫어요 추가 */
	public int addVote(BoardVoteDto boardVoteDto);

	/* 좋아요/싫어요 수정 */
	public int updateVote(BoardVoteDto boardVoteDto);

	/* 좋아요/싫어요 삭제 */
	public int deleteVote(BoardVoteDto boardVoteDto);
	
	
	
}
