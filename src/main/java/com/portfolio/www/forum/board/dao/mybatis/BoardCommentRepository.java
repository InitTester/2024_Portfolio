package com.portfolio.www.forum.board.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.forum.board.dto.BoardCommentDto;

@Repository
public interface BoardCommentRepository {
	
	/* 게시글 댓글 리스트 */
	public List<BoardCommentDto> getBoardCommentList(BoardCommentDto CommentDto);
	
	/* 게시글 댓글 추가 */
	public int newComment(BoardCommentDto boardCommentDto);
	
	/* 게시글 parentComment 업데이트 */
	public int updateParentComment(Integer commentSeq);
	
	/* 게시글 댓글 수정 */
	public int editComment(BoardCommentDto boardCommentDto);
	
	/* 게시글 댓글 삭제 */
	public int deleteComment(BoardCommentDto boardCommentDto);
}
