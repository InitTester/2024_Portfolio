package com.portfolio.www.forum.board.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.portfolio.www.forum.board.util.FileUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private final static Logger log = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardVoteRepository boardVoteRepository;
	
	@Autowired
	private BoardAttachRepository boardattachRepository;
	
	@Autowired
	private FileUtil fileUtil;

	/* 게시판 이름 */
	public String getBoardTypeNm(Integer boardTypeSeq) {
		return boardRepository.getBoardTypeNm(boardTypeSeq);
	}
	
	/* 게시글 전체가져오기 */
	public List<BoardDto> getBoardList(HashMap<String, Object> params){
		return boardRepository.getBoardList(params);
	}
	
	/* 게시글 조회수 */
	public int updateHit(Integer boardSeq) {
		return boardRepository.updateHit(boardSeq);
	}
	
	/* 게시글 상세조회 */
	public BoardDto getBoardDetail(Integer boardSeq) {
		return boardRepository.getBoardDetail(boardSeq);
	}
	
	/* 좋아요/싫어요 상태*/
	public String getVote(HashMap<String, String> params) {
		
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq").toString());
		Integer boardSeq = Integer.parseInt(params.get("boardSeq").toString());
		Integer memberSeq=  Integer.parseInt(params.get("memberSeq").toString());
		
		BoardVoteDto boardVoteDto = BoardVoteDto.getBoardVoteDto(boardTypeSeq, boardSeq, memberSeq, "", "");
		
		CommonUtil.getLogMessage(log, "getVote", "boardVoteDto", boardVoteDto);
		System.out.println(boardVoteDto);
		
		return boardVoteRepository.getVote(boardVoteDto);
	}
	
	/* 좋아요/싫어요 등록/수정/삭제 */
	@Transactional
	public int setVote(BoardVoteDto boardVoteDto) {
		
		/* 좋아요/싫어요 기능 
		 * 1. 값이 없을 때 insert
		 * 2. 값이 있으면서 다를때 update
		 * 3. 값이 있으면서 같을때 delete 
		 * */
		
		/* 1. 해당 정보에 값이 있는지 확인 
		 * 2. 없다면 추가
		 * 3. 있다면 저장 값비교 처리
		 * */
		
		int resultVlue= -1;
		
		String beforeisList = boardVoteRepository.getVote(boardVoteDto);
		
		if(beforeisList == null) {
			boardVoteRepository.addVote(boardVoteDto);
			resultVlue = 1;
		} else if(!beforeisList.equals(boardVoteDto.getIsLike())){
			boardVoteRepository.updateVote(boardVoteDto);
			resultVlue = 2;
		} else {
			// update
			boardVoteRepository.deleteVote(boardVoteDto);
			resultVlue = 3;
		}
		
		return resultVlue;
	}
	
	/* 게시글 등록 */
	@Transactional
	public boolean addBoard(BoardDto boardDto, MultipartFile[] attFiles, HttpServletRequest request) {
		
		File destFile =null;
		
		try {

			/*board_seq*/
			boardRepository.addBoard(boardDto);
			int boardSeq = boardDto.getBoardSeq();
			
			CommonUtil.getLogMessage(log, "addBoard", "getBoardSeq", boardDto.getBoardSeq());
			CommonUtil.getLogMessage(log, "addBoard", "boardSeq", boardSeq);
			
			for(MultipartFile mpf : attFiles) {
				if(!mpf.isEmpty()) {
					destFile = fileUtil.saveFile(mpf,request);

//					String ChngFileNm = UUID.randomUUID().toString().replaceAll("-","");
					BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardDto.getBoardTypeSeq(), boardSeq);
					
//					attachDto.setChngFileNm(ChngFileNm);
					attachDto.setOrgFileNm(mpf.getOriginalFilename());
					attachDto.setFileType(mpf.getContentType());
					attachDto.setFileSize(mpf.getSize());
					attachDto.setSavePath(destFile.getAbsolutePath());
					
					boardattachRepository.addBoardAttach(attachDto);
				}
			}
			
			return true;
			
		}catch(DataAccessException dae) {
			CommonUtil.getLogMessage(log, "addBoard", "DataAccessException", dae);
			return false;
		}catch(Exception e) {
			if(!ObjectUtils.isEmpty(destFile)) {
				destFile.delete();
				log.info(" addBoard :: e.getMessage()={}", e.getMessage());	
			}
			return false;
		}
	}
	
	/* 게시글 첨부파일 리스트 */
	public List<BoardAttachDto> getBoardAttachAll(BoardAttachDto attachDto){
		return boardattachRepository.getBoardAttachAll(attachDto);
	}
	
	
	
	
	
	
	
	/* Util */	
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
