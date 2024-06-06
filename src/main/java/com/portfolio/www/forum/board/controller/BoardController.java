package com.portfolio.www.forum.board.controller;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.message.BoardMessageEnum;
import com.portfolio.www.forum.board.service.BoardService;

@Controller
public class BoardController {
	private final static Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	/* 게시글 리스트 size : 10*/
	@GetMapping("/forum/board/listPage.do")
	public ModelAndView listPage(@RequestParam HashMap<String, Object> params, 
								 @RequestParam(name="boardTypeSeq", defaultValue = "1") Integer boardTypeSeq,
								 @RequestParam(defaultValue = "1") Integer page,
								 @RequestParam(defaultValue = "10") Integer size) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/board/list");
		
		/* 게시글 타입 */
		params.put("type", boardTypeSeq);
		params.put("page", page);
		params.put("size", size);
		
		int start = (page-1)*size;	
		params.put("start", start);
		
		HashMap<String, Integer> pageHandler = boardService.getBoardPageInfo(boardTypeSeq, size, page);
		
		/* 잘못된 페이지 접근 처리 */
	  	if(page <0 || page > (int)pageHandler.get("totalPageSize")) {
			mv.addObject("code", BoardMessageEnum.PAGEING_ERROR.getCode());
			mv.addObject("msg", BoardMessageEnum.PAGEING_ERROR.getDescription());	
	  	}		

//	  	mv.addObject("list", boardService.getList(params,page,size));
	  	mv.addObject("boardTypeSeq", boardTypeSeq);
	  	mv.addObject("boardTypeNm", boardService.getBoardTypeNm(boardTypeSeq));	  	
		mv.addObject("board",boardService.getBoardList(params));		
		mv.addObject("pageHandler", pageHandler);
		
		return mv;
	}

	/* 게시물 상세페이지 */
	@GetMapping("/forum/board/readPage.do")
	public ModelAndView readPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		String boardSeq = params.get("boardSeq");
		
		/* 조회수 올리기 TODO 추후 조회수 새로고침화면에서 어떻게..?*/ 	
		boardService.updateHit(boardSeq);
		
		String memberId = CommonUtil.getCookieValue(request,"memberId");
		params.put("memberId", memberId);
		
		String isLike = boardService.getVote(params);
		
		mv.addObject("boardDetail", boardService.getBoardDetail(boardSeq));
		mv.addObject("isLike", isLike);
		mv.setViewName("forum/board/read");
		
		return mv;
	}
	
	@GetMapping("/forum/board/writePage.do")
	public ModelAndView writePage(@RequestParam HashMap<String, String> params) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/board/write");
		
		return mv;
	}

}