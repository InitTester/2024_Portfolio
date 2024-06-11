package com.portfolio.www.forum.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.www.forum.board.dto.BoardDto;
import com.portfolio.www.forum.board.exception.FileDeleteException;
import com.portfolio.www.forum.board.message.BoardMessageEnum;
import com.portfolio.www.forum.board.service.BoardService;

@RestController
public class RestBoardController {
	
	@Autowired
	private BoardService boardService;

	/* 게시글 삭제 */
	@DeleteMapping("/forum/board/{boardTypeSeq}/{boardSeq}/delete.do")
	public ResponseEntity<Map<String, Object>> delte(@PathVariable("boardTypeSeq")Integer boardTypeSeq,
											@PathVariable("boardSeq")Integer boardSeq){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		BoardDto boardDto = BoardDto.setBoardDto(boardTypeSeq, boardSeq, null, null, null);
		int result;
		try {
			result = boardService.deleteBoard(boardDto);
			response.put("result", result);
			
			response.put("code",BoardMessageEnum.DELETE_SUCCESS.getCode());
			response.put("msg",BoardMessageEnum.DELETE_SUCCESS.getDescription());			
			response.put("page","/forum/board/listPage.do");
			
			return ResponseEntity.ok(response);
			
		}catch (FileDeleteException e) {
			// TODO Auto-generated catch block
			response.put("code",BoardMessageEnum.FILE_DELETE_FAIL.getCode());
			response.put("msg",BoardMessageEnum.FILE_DELETE_FAIL.getDescription());	
			response.put("page","/forum/board/readPage.do?boardSeq="+boardSeq);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block

			response.put("code",BoardMessageEnum.DELETE_FAIL.getCode());
			response.put("msg",BoardMessageEnum.DELETE_FAIL.getDescription());	
			response.put("page","/forum/board/readPage.do?boardSeq="+boardSeq);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		
	}
	
}
