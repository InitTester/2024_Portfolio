package com.portfolio.www.forum.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.www.forum.board.dao.mybatis.BoardCommentRepository;
import com.portfolio.www.forum.board.dto.BoardCommentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardCommentService {
	
	@Autowired
	private BoardCommentRepository CommentRepository;

	/* 게시글 댓글 리스트 */
	public List<BoardCommentDto> getBoardCommentList(BoardCommentDto CommentDto){
		return CommentRepository.getBoardCommentList(CommentDto);
	}

	/* 게시글 댓글 추가 */
	public int newComment(BoardCommentDto boardCommentDto) {

		CommentRepository.newComment(boardCommentDto);
		Integer commemtSeq = boardCommentDto.getCommentSeq();
		log.info("commemtSeq : {}",commemtSeq);
		
//		if(boardCommentDto.getParentSeq() == null) {
//			return CommentRepository.updateParentComment(commemtSeq);
//		}
		
		return commemtSeq;
	}

	/* 게시글 댓글 수정 */
	public int editComment(BoardCommentDto boardCommentDto) {
		return CommentRepository.editComment(boardCommentDto);
	}
	
	/* 게시글 댓글 삭제 */
	public int deleteComment(BoardCommentDto boardCommentDto) {
		return CommentRepository.deleteComment(boardCommentDto);
	}
	
	
	
}
