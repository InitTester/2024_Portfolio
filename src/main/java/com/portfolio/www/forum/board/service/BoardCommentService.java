package com.portfolio.www.forum.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.www.forum.board.dao.mybatis.BoardCommentRepository;
import com.portfolio.www.forum.board.dto.BoardCommentDto;

@Service
public class BoardCommentService {
	
	@Autowired
	private BoardCommentRepository CommentRepository;

	/* 게시글 댓글 리스트 */
	public List<BoardCommentDto> getBoardCommentList(BoardCommentDto CommentDto){
		return CommentRepository.getBoardCommentList(CommentDto);
	}

	/* 게시글 댓글 추가 */
	public int addComment(BoardCommentDto boardCommentDto) {
		return CommentRepository.addComment(boardCommentDto);
	}
	
	
	
}
