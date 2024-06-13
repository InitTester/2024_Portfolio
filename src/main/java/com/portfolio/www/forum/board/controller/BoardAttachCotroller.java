package com.portfolio.www.forum.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.forum.board.dto.BoardAttachDto;
import com.portfolio.www.forum.board.service.BoardAttachService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardAttachCotroller {

	@Autowired
	private BoardAttachService attachService;
	
	/* 게시글 전체 다운로드 */
	@GetMapping("/forum/board/downloadAll.do")
	public ModelAndView downloadAll(@RequestParam Integer boardTypeSeq,
			@RequestParam Integer boardSeq){
		
		ModelAndView mv = new ModelAndView();
		String downloadFileNm = attachService.getDownloadAllName(); 
		File file = attachService.getBoardAttachFileAll(boardTypeSeq, boardSeq);
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("downloadFile", file);
		fileInfo.put("downloadFileNm", downloadFileNm);
		fileInfo.put("ZipFile", false);
		
		mv.addObject("fileInfo", fileInfo);
		mv.setViewName("fileDownloadView");
		
		return mv;
	}
	
	/* 게시글 개별 다운로드 */
	@GetMapping("/forum/board/download.do")
	public ModelAndView download( @RequestParam Integer attachSeq){
		
		ModelAndView mv = new ModelAndView();
		BoardAttachDto attachDto = attachService.getBoardAttach(attachSeq);
		File file = new File(attachDto.getSavePath());
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("downloadFile", file);
		fileInfo.put("downloadFileNm", attachDto.getOrgFileNm());
		fileInfo.put("ZipFile", false);
		
		mv.addObject("fileInfo", fileInfo);
		mv.setViewName("fileDownloadView");
		
		return mv;
	}
}
