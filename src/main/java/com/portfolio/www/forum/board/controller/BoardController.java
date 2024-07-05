package com.portfolio.www.forum.board.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dto.BoardAttachDto;
import com.portfolio.www.forum.board.dto.BoardCommentDto;
import com.portfolio.www.forum.board.dto.BoardDto;
import com.portfolio.www.forum.board.dto.BoardVoteDto;
import com.portfolio.www.forum.board.message.BoardMessageEnum;
import com.portfolio.www.forum.board.service.BoardAttachService;
import com.portfolio.www.forum.board.service.BoardCommentService;
import com.portfolio.www.forum.board.service.BoardService;
import com.portfolio.www.forum.board.service.BoardVoteService;
import com.portfolio.www.forum.board.util.PageHandler;
import com.portfolio.www.user.message.MemberMessageEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardAttachService attachService;

	@Autowired
	private BoardVoteService voteService;

	@Autowired
	private BoardCommentService commentService;
	
	/* 게시글 리스트 size : 10*/
	@GetMapping("/forum/board/listPage.do")
	public ModelAndView boardListPage(@RequestParam HashMap<String, Object> params, 
								 @RequestParam(name="boardTypeSeq", defaultValue = "1") Integer boardTypeSeq,
								 @RequestParam(defaultValue = "1") Integer page,
								 @RequestParam(defaultValue = "10") Integer size,
								 RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());

		/* 게시글 타입 */
		params.put("type", boardTypeSeq);
		params.put("page", page);
		params.put("size", size);
		
		int start = (page-1)*size;	
		params.put("start", start);
		
		Integer totalPage = boardService.getBoardTotalCnt(boardTypeSeq);
		
		PageHandler ph = new PageHandler(size, page, totalPage);

		/* 잘못된 페이지 접근 처리 */
	  	if(size > 10 || page < 0 || page > ph.getTotalPageSize()) {
			redirectAttributes.addFlashAttribute("code", BoardMessageEnum.PAGEING_ERROR.getCode());
			redirectAttributes.addFlashAttribute("msg", BoardMessageEnum.PAGEING_ERROR.getDescription());
			mv.setViewName("redirect:/forum/board/listPage.do");
	  	} else {
//		  	mv.addObject("list", boardService.getList(params,page,size));
		  	mv.addObject("boardTypeSeq", boardTypeSeq);
		  	mv.addObject("boardTypeNm", boardService.getBoardTypeNm(boardTypeSeq));	  	
			mv.addObject("board",boardService.getBoardList(params));		
			mv.addObject("pageHandler", ph);

			mv.setViewName("forum/board/list");	
	  	}
		return mv;
	}

	/* 게시물 상세페이지 */
	@GetMapping("/forum/board/readPage.do")
	public ModelAndView boardReadPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		log.info("[boardReadPage] params : {}", params);
		
		try {
			Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
			Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
			
			/* 조회수 올리기 TODO 추후 조회수 새로고침화면에서 어떻게..?*/ 	
			boardService.viewsBoardHit(boardSeq);
			
			Integer memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());// CommonUtil.getCookieValue(request,"memberSeq");
			BoardVoteDto boardVoteDto = BoardVoteDto.getBoardVoteDto(boardTypeSeq, boardSeq, memberSeq, "", "");
			
			CommonUtil.getLogMessage(log, "getVote", "boardVoteDto", boardVoteDto);
			
			String isLike = voteService.getVote(boardVoteDto);
	
			BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);	
			BoardCommentDto commentDto = BoardCommentDto.setBoardCommentDto(boardTypeSeq, boardSeq);	
		
			List<BoardAttachDto> attFiles = attachService.getBoardAttachAll(attachDto);
			List<BoardCommentDto> comments = commentService.getBoardCommentList(commentDto); 
			
			mv.addObject("boardDetail", boardService.getBoardDetail(boardSeq));
			mv.addObject("isLike", isLike);
			mv.addObject("attFiles", attFiles);
			mv.addObject("comments", comments);
			mv.setViewName("forum/board/read");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Exception : ",e.getMessage());
		}
		
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
		
		BoardDto boardDto = BoardDto.setBoardDto(boardTypeSeq, 0, title, content, memberSeq);
			
		boolean result = boardService.newBoard(boardDto, attFiles, request);

		if(result) {
			mv.addObject("code",BoardMessageEnum.SUCCESS.getCode());
			mv.addObject("msg",BoardMessageEnum.SUCCESS.getDescription());
			mv.setViewName("redirect:/forum/board/listPage.do?boardTypeSeq="+boardTypeSeq);
		} else {	
			mv.addObject("code",BoardMessageEnum.FAIL.getCode());
			mv.addObject("msg",BoardMessageEnum.FAIL.getDescription());
			mv.addObject("board", boardDto);			
			mv.setViewName("forum/board/new");
		}
		return mv;
	}
	
	/* 게시글 수정 페이지 */
	@GetMapping("/forum/board/editPage.do")
	public ModelAndView boardEditPage(@RequestParam HashMap<String, String> params,
			@RequestParam(defaultValue = "1") Integer boardSeq,
			@RequestParam(defaultValue = "1") Integer boardTypeSeq) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("boardTypeSeq", boardTypeSeq);
		mv.setViewName("forum/board/edit");

		log.info("[boardEditPage] (boardTypeSeq : {} ) (boardSeq : {})",boardTypeSeq,boardSeq);
		
		BoardDto boardDto = boardService.getBoardDetail(boardSeq);
		
		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);		
		List<BoardAttachDto> attFiles = attachService.getBoardAttachAll(attachDto);
		
		mv.addObject("board", boardDto);
		mv.addObject("attFiles", attFiles);
		
		return mv;
	}
	
	/* 게시글 수정 */
	@PostMapping("/forum/board/edit.do")
	public ModelAndView edit(@RequestParam HashMap<String , String> params,
			            @RequestParam(value = "attFile", required=false) MultipartFile[] attFiles,
			            HttpServletRequest request) {	
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key",Calendar.getInstance().getTimeInMillis());
//		Integer memberSeq = Integer.parseInt(CommonUtil.getCookieValue(request, "memberSeq"));

		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
		Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
		String title = params.get("title");
		String content = params.get("trumbowyg-demo");
		Integer memberSeq = Integer.parseInt(CommonUtil.getCookieValue(request, "memberSeq"));
		
		BoardDto boardDto = BoardDto.setBoardDto(boardTypeSeq, boardSeq, title, content, memberSeq);
		
		log.info("boardDto : {} ",boardDto);
		log.info("[edit] (boardDto.title) : {} ",boardDto.getTitle());
		log.info("attFiles : {} ", Arrays.toString(attFiles));
		
		int result = boardService.editBoard(boardDto, attFiles);

		if(result==1) {
			
			mv.addObject("code",BoardMessageEnum.EDIT_SUCCESS.getCode());
			mv.addObject("msg",BoardMessageEnum.EDIT_SUCCESS.getDescription());
			mv.setViewName("redirect:/forum/board/readPage.do?boardTypeSeq="+boardTypeSeq+"&boardSeq="+params.get("boardSeq"));
		} else {	
			
			mv.addObject("code",BoardMessageEnum.EDIT_FAIL.getCode());
			mv.addObject("msg",BoardMessageEnum.EDIT_FAIL.getDescription());
			mv.addObject("board", boardDto);
			mv.setViewName("forum/board/editPage.do?boardTypeSeq="+boardTypeSeq+"&boardSeq="+params.get("boardSeq"));
		}
		return mv;
	}
	
}