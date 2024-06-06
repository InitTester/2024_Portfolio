package com.portfolio.www.forum.board.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dto.BoardVoteDto;
import com.portfolio.www.forum.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
public class RestBoardVoteController {
	private final static Logger log = LoggerFactory.getLogger(RestBoardVoteController.class);
	@Autowired
	private BoardService boardService;

	@PostMapping("/forum/board/vote.do")
//	@ResponseBody
	public ResponseEntity<Integer> vote(@RequestBody BoardVoteDto voteDto, HttpServletRequest request) {

		voteDto.setmemberId(CommonUtil.getCookieValue(request, "memberId"));
		voteDto.setIp(request.getRemoteAddr());
		
		int result = boardService.setVote(voteDto);
		
		return ResponseEntity.ok().body(result);

	}
}
