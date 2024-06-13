package com.portfolio.www.forum.board.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.forum.board.dto.BoardCommentDto;

@Repository
public interface BoardCommentRepository {
	
	/* 게시글 댓글 리스트 */
	public List<BoardCommentDto> getBoardCommentList(BoardCommentDto CommentDto);
	
	/* 게시글 댓글 추가 */
	public int addComment(BoardCommentDto boardCommentDto);
	
}
