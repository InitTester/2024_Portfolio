package com.portfolio.www.index.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.forum.board.dto.BoardVoteDto;
import com.portfolio.www.index.dto.IndexAttachViewDto;
import com.portfolio.www.index.dto.IndexBoardViewDto;
import com.portfolio.www.index.dto.IndexContactMeDto;
import com.portfolio.www.index.service.IndexService;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	@GetMapping("/index.do")
	public ModelAndView index(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		
		List<IndexAttachViewDto> attachViewDtos = indexService.getindexAttachView();
		List<IndexBoardViewDto> boardViewDtos = indexService.getIndexBoardView();
		mv.setViewName("index");
		mv.addObject("attachList", attachViewDtos);
		mv.addObject("boardList",boardViewDtos);
		return mv;
	}	
	
	@GetMapping("/aboutMe.do")
	public ModelAndView aboutMe(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("aboutMe");
		return mv;
	}	
	
	@PostMapping("/contactMe.do")
	public ResponseEntity<Integer> contactMe(@RequestBody IndexContactMeDto contactMeDto, HttpServletRequest request) {
			
		log.info("[contactMe] contactMeDto : {}",contactMeDto);
		
		int result = -1;
		
		try {
			result = indexService.addContactMe(contactMeDto);			
			return ResponseEntity.ok().body(result);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			log.info("[contactMe] NumberFormatException : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("[contactMe] Exception : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}	
}