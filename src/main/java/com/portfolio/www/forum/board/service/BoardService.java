package com.portfolio.www.forum.board.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dao.mybatis.BoardAttachRepository;
import com.portfolio.www.forum.board.dao.mybatis.BoardRepository;
import com.portfolio.www.forum.board.dao.mybatis.BoardVoteRepository;
import com.portfolio.www.forum.board.dto.BoardAttachDto;
import com.portfolio.www.forum.board.dto.BoardDto;
import com.portfolio.www.forum.board.dto.BoardVoteDto;
import com.portfolio.www.forum.board.exception.FileDeleteException;
import com.portfolio.www.forum.board.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardAttachService attachService;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Value("#{config['img.access.host']}")
	private String accessUriPath;

	/* 게시판 이름 */
	public String getBoardTypeNm(Integer boardTypeSeq) {
		return boardRepository.getBoardTypeNm(boardTypeSeq);
	}
	
	/* 게시글 전체가져오기 */
	public List<BoardDto> getBoardList(HashMap<String, Object> params){
		return boardRepository.getBoardList(params);
	}
	
	/* 게시글 조회수 */
	public int viewsBoardHit(Integer boardSeq) {
		return boardRepository.viewsBoardHit(boardSeq);
	}
	
	/* 게시글 상세조회 */
	public BoardDto getBoardDetail(Integer boardSeq) {
		return boardRepository.getBoardDetail(boardSeq);
	}
	
	/* 게시글 등록 */
	@Transactional
	public boolean newBoard(BoardDto boardDto, MultipartFile[] attFiles, HttpServletRequest request) {
		
		File destFile =null;

		try {
			boardRepository.newBoard(boardDto);
			
			for(MultipartFile mpf : attFiles) {
				if(!mpf.isEmpty()) {
					destFile = fileUtil.saveFile(mpf);
					attachService.addBoardAttach(destFile,boardDto,mpf);
				}
			}
			
			return true;
			
		}catch(DataAccessException dae) {
			CommonUtil.getLogMessage(log, "addBoard", "DataAccessException", dae);
			return false;
		}catch(Exception e) {
			if(!ObjectUtils.isEmpty(destFile)) {
				destFile.delete();
				CommonUtil.getLogMessage(log, "addBoard", "Exception", e.getMessage());
			}
			return false;
		}
	}
	
	/* 게시글 수정 */
	public int editBoard(BoardDto boardDto, MultipartFile[] attFiles) {
		
		int cnt = -1;

		File destFile =null;
		
		try {
			cnt = boardRepository.editBoard(boardDto);
			CommonUtil.getLogMessage(log, "editBoard", "title", boardDto.getTitle());
			
//			BoardAttachDto attachDtoDel = BoardAttachDto.setBoardAttachDto(boardDto.getBoardTypeSeq(), boardDto.getBoardSeq());
//			boardattachRepository.deleteBoardAttachAll(attachDtoDel);
			
			for(MultipartFile mpf : attFiles) {
				if(!mpf.isEmpty()) {
					CommonUtil.getLogMessage(log, "editBoard", "isEmpty", 1);
					
					attachService.editBoardAttach(destFile, boardDto, mpf);
				}
			}
			
			return cnt;
		}catch(DataAccessException dae) {
			CommonUtil.getLogMessage(log, "editBoard", "DataAccessException", dae.getMessage());
			throw dae;
		}catch(Exception e) {
			if(!ObjectUtils.isEmpty(destFile)) {
				destFile.delete();
				CommonUtil.getLogMessage(log, "editBoard", "Exception", e.getMessage());
			}
			return cnt;
		}
	}
	
	/* 게시글 삭제 */
	public int deleteBoard(BoardDto boardDto) {
		
		attachService.deleteBoardAttach(boardDto);

		return boardRepository.deletetBoard(boardDto);
	}

	/* 페이징 처리를 위한 전체 페이지 */
	public HashMap<String, Integer> getBoardPageInfo(Integer boardTypeSeq, Integer size, Integer page) {

		HashMap<String, Integer> pageHandler = new HashMap<String, Integer>();

		/*
		 * pageHandler에 필요한 변수 선언 
		 * totalPage 전체 게시물 개수 
		 * page 현재 페이지 번호 
		 * size 페이지당 게시물 수
		 * begin 시작페이지 
		 * end 끝 페이지
		 * prev 이전 화살표 
		 * next 다음 화살표
		 */

		int totalPage = boardRepository.getBoardTotalCnt(boardTypeSeq);
		int totalPageSize = totalPage / size;

		int begin = page == 0 ? 1 : ((page - 1) / size) * size + 1;
		int end = Math.min(begin + size - 1, totalPageSize);

		int prev = begin;
		int next = end != totalPage ? end : totalPage;

		pageHandler.put("totalPage", totalPage);
		pageHandler.put("totalPageSize", totalPageSize);
		pageHandler.put("begin", begin);
		pageHandler.put("end", end);
		pageHandler.put("size", size);
		pageHandler.put("prev", prev);
		pageHandler.put("next", next);

		return pageHandler;
	}
}
