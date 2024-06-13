package com.portfolio.www.forum.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
  public int addComment(@RequestBody BoardCommentDto commentDto,
	  HttpServletRequest request) {
	  
	  HttpSession session = request.getSession();
	  Integer memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());
	  
	  commentDto.setMemberSeq(memberSeq);
	  
	  return commentService.addComment(commentDto); 
  }
	 
}
