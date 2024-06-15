package com.portfolio.www.forum.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.www.forum.board.dto.BoardCommentDto;
import com.portfolio.www.forum.board.service.BoardCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardCommentController {
	
	
	@Autowired
	private BoardCommentService commentService;
	
	@PostMapping("/forum/board/comment.do")
	public ResponseEntity<Integer> newComment(@RequestBody BoardCommentDto commentDto,
						  HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		try {
			
			Integer memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());			
			commentDto.setMemberSeq(memberSeq);
			
			int result =commentService.newComment(commentDto); 
			
			return ResponseEntity.ok(result);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info("[newComment] (NumberFormatException : {}) ",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
		}
	}
	
	@PostMapping("/forum/board/editComment.do")
	public ResponseEntity<Integer> editComment(@RequestBody BoardCommentDto commentDto,
						  HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		try {
			
			Integer memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());			
			commentDto.setMemberSeq(memberSeq);
			
			int result =commentService.editComment(commentDto); 
			
			return ResponseEntity.ok(result);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			log.info("[newComment] (NumberFormatException : {}) ",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
		}
	}
	
	
	/* comment로만 되지 않을까? */
	@DeleteMapping("/forum/board/{boardTypeSeq}/{boardSeq}/{commentSeq}/deleteComment.do")
	public ResponseEntity<Integer> deleteComment(@PathVariable("boardTypeSeq")Integer boardTypeSeq,
								@PathVariable("boardSeq")Integer boardSeq,
								@PathVariable("commentSeq")Integer commentSeq,
								HttpServletRequest request) {
		try {
			BoardCommentDto commentDto = BoardCommentDto.setBoardCommentDto(boardTypeSeq, boardSeq);
			commentDto.setCommentSeq(commentSeq);
			
			log.info("getBoardTypeSeq : {} , getBoardSeq : {} , getCommentSeq : {}",commentDto.getBoardTypeSeq(),commentDto.getBoardSeq(),commentDto.getCommentSeq());
			
			int result = commentService.deleteComment(commentDto);
			
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("[deleteComment] (Exception : {}) ",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
		}
	}
}
