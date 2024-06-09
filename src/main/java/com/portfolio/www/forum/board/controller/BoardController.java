package com.portfolio.www.forum.board.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dto.BoardAttachDto;
import com.portfolio.www.forum.board.dto.BoardDto;
import com.portfolio.www.forum.board.message.BoardMessageEnum;
import com.portfolio.www.forum.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 게시글 리스트 size : 10*/
	@GetMapping("/forum/board/listPage.do")
	public ModelAndView boardListPage(@RequestParam HashMap<String, Object> params, 
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
	public ModelAndView boardReadPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
		
		/* 조회수 올리기 TODO 추후 조회수 새로고침화면에서 어떻게..?*/ 	
		boardService.updateHit(boardSeq);
		
		String memberSeq = CommonUtil.getCookieValue(request,"memberSeq");
		params.put("memberSeq", memberSeq);
		
		String isLike = boardService.getVote(params);

		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);	
		List<BoardAttachDto> attFiles = boardService.getBoardAttachAll(attachDto);
		
		mv.addObject("boardDetail", boardService.getBoardDetail(boardSeq));
		mv.addObject("isLike", isLike);
		mv.addObject("attFiles", attFiles);
		mv.setViewName("forum/board/read");
		
		return mv;
	}

	/* 게시글 등록 페이지 */
	@GetMapping("/forum/board/newPage.do")
	public ModelAndView boardNewPage(@RequestParam HashMap<String, String> params,
			@RequestParam(defaultValue = "1") Integer boardSeq,
			@RequestParam(defaultValue = "1") Integer boardTypeSeq) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("boardTypeSeq", boardTypeSeq);
		mv.setViewName("forum/board/new");
		
		return mv;
	}
	
	/* 게시글 등록 */
	@PostMapping("/forum/board/new.do")
	public ModelAndView boardNew(@RequestParam HashMap<String, String> params,
			                  @RequestParam(value = "attFile", required=false) MultipartFile[] attFiles,
			                  HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
		String title = params.get("title");
		String content = params.get("trumbowyg-demo");
		Integer memberSeq = Integer.parseInt(CommonUtil.getCookieValue(request, "memberSeq"));
		
		BoardDto boardDto = BoardDto.setBoardDto(boardTypeSeq, title, content, memberSeq);
			
		boolean result = boardService.addBoard(boardDto, attFiles, request);

		if(result) {
			CommonUtil.getLogMessage(log, "boardNew", "true", result);
			mv.addObject("code",BoardMessageEnum.SUCCESS.getCode());
			mv.addObject("msg",BoardMessageEnum.SUCCESS.getDescription());
			mv.setViewName("redirect:/forum/board/listPage.do");
		} else {	
			CommonUtil.getLogMessage(log, "boardNew", "false", result);
			mv.addObject("code",BoardMessageEnum.FAIL.getCode());
			mv.addObject("msg",BoardMessageEnum.FAIL.getDescription());
			mv.addObject("board", boardDto);
			
			mv.setViewName("forum/board/new");
		}
		return mv;
	}
	/* 게시글 등록 페이지 */
	@GetMapping("/forum/board/editPage.do")
	public ModelAndView boardEditPage(@RequestParam HashMap<String, String> params,
			@RequestParam(defaultValue = "1") Integer boardSeq,
			@RequestParam(defaultValue = "1") Integer boardTypeSeq) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("boardTypeSeq", boardTypeSeq);
		mv.setViewName("forum/board/edit");

		CommonUtil.getLogMessage(log, "boardEditPage", "boardSeq", boardSeq);
		CommonUtil.getLogMessage(log, "boardEditPage", "boardTypeSeq", boardTypeSeq);
		
		BoardDto boardDto = boardService.getBoardDetail(boardSeq);
		
		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);		
		List<BoardAttachDto> attFiles = boardService.getBoardAttachAll(attachDto);
		
		mv.addObject("board", boardDto);
		mv.addObject("attFiles", attFiles);
		
		return mv;
	}
	/* 게시글 수정 */
//	@PostMapping("/forum/board/editPage.do"){
//		
//	}
	

}