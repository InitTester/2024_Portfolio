package com.portfolio.www.index.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.index.dto.IndexAttachViewDto;
import com.portfolio.www.index.service.IndexService;

@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	@GetMapping("/index.do")
	public ModelAndView index(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		
		List<IndexAttachViewDto> attachViewDtos = indexService.getindexAttachView();
		mv.setViewName("index");
		mv.addObject("attachList", attachViewDtos);
		return mv;
	}	
}