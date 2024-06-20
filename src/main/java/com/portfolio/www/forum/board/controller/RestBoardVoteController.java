package com.portfolio.www.forum.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dto.BoardVoteDto;
import com.portfolio.www.forum.board.service.BoardService;
import com.portfolio.www.forum.board.service.BoardVoteService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class RestBoardVoteController {
	
	@Autowired
	private BoardVoteService voteService;

	@PostMapping("/forum/board/vote.do")
	public ResponseEntity<Integer> vote(@RequestBody BoardVoteDto voteDto, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int result = -1;
		
		try {
			Integer memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());//CommonUtil.getCookieValue(request, "memberSeq"));
			String Ip = request.getRemoteAddr();

			voteDto.setmemberSeq(memberSeq);
			voteDto.setIp(Ip);
			
			result = voteService.setVote(voteDto);
			
			return ResponseEntity.ok().body(result);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		

	}
}
