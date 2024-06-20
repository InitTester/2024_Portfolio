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
@Transactional(rollbackFor = {Exception.class})
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardAttachService attachService;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Value("#{config['access.host']}")
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
			BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardDto.getBoardTypeSeq(),boardDto.getBoardSeq());
			List<BoardAttachDto> boardAttachList = attachService.getBoardAttachAll(attachDto);
			int idx = 0;
			
			for(MultipartFile mpf : attFiles) {				
				log.info("boardAttachList :  {}", boardAttachList);
				log.info("mpf : {}", mpf);
				
				if(!mpf.getOriginalFilename().equals("")) {
//					
					destFile = fileUtil.saveFile(mpf);
					
					log.info("boardAttachList.size() : {}", boardAttachList.size());
					log.info("idx : {}", idx);
					
					if(boardAttachList.size() > idx) {
						log.info("editBoardAttach");
//						log.info("boardAttachList.get(idx) :  {}", boardAttachList.get(idx));
//						log.info("boardAttachList.get(idx).getOrgFileNm() :  {}", boardAttachList.get(idx).getOrgFileNm());
						attachService.editBoardAttach(destFile, attachDto, boardAttachList.get(idx).getAttachSeq(), mpf);
					}else {
						log.info("addBoardAttach");
						attachService.addBoardAttach(destFile,boardDto,mpf);
					}
				}
				idx++;
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
//	@Transactional(rollbackFor = Exception.class)
	public int deleteBoard(BoardDto boardDto) {
		try {
			attachService.deleteBoardAttachAll(boardDto);
			log.info("boardDto : {}",boardDto);
			
			return boardRepository.deletetBoard(boardDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("[deleteBoard] Exception : {} ",e.getMessage());
			throw e;
		}
	}
	
	public int getBoardTotalCnt(Integer boardTypeSeq) {
		return boardRepository.getBoardTotalCnt(boardTypeSeq);
	}
}
