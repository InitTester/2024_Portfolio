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
	private BoardVoteRepository boardVoteRepository;
	
	@Autowired
	private BoardAttachRepository boardattachRepository;
	
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
	
	/* 좋아요/싫어요 상태*/
	public String getVote(HashMap<String, String> params) {
		
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq").toString());
		Integer boardSeq = Integer.parseInt(params.get("boardSeq").toString());
		Integer memberSeq=  Integer.parseInt(params.get("memberSeq").toString());
		
		BoardVoteDto boardVoteDto = BoardVoteDto.getBoardVoteDto(boardTypeSeq, boardSeq, memberSeq, "", "");
		
		CommonUtil.getLogMessage(log, "getVote", "boardVoteDto", boardVoteDto);
		
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
	public boolean newBoard(BoardDto boardDto, MultipartFile[] attFiles, HttpServletRequest request) {
		
		File destFile =null;

		try {

			/*board_seq*/
			boardRepository.newBoard(boardDto);
			int boardSeq = boardDto.getBoardSeq();
			
			CommonUtil.getLogMessage(log, "addBoard", "boardSeq", boardSeq);
			
			for(MultipartFile mpf : attFiles) {
				if(!mpf.isEmpty()) {
					destFile = fileUtil.saveFile(mpf);

					CommonUtil.getLogMessage(log, "addBoard", "getPath", destFile.getPath());
					String accessUri = "";
					if(!destFile.getPath().contains("c:")) {
						accessUri = accessUriPath + destFile.getPath().split("app")[1].substring(1);// + destFile.getName();	
					}
					
					BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardDto.getBoardTypeSeq(), boardSeq);
				
					attachDto.setChngFileNm(destFile.getName());
					attachDto.setAccessUri(accessUri);
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
				CommonUtil.getLogMessage(log, "addBoard", "Exception", e.getMessage());
			}
			return false;
		}
	}
	
	/* 게시글 수정 */
	public int editBoard(BoardDto boardDto,  MultipartFile[] attFiles) {
		
		int cnt = -1;

		File destFile =null;
		
		try {
			cnt = boardRepository.editBoard(boardDto);
			CommonUtil.getLogMessage(log, "editBoard", "title", boardDto.getTitle());
			
			for(MultipartFile mpf : attFiles) {
				if(!mpf.isEmpty()) {
					destFile = fileUtil.saveFile(mpf);

					CommonUtil.getLogMessage(log, "editBoard", "isEmpty", 1);
					String accessUri = accessUriPath + destFile.getPath().split("/app/")[1];// + destFile.getName();
					BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardDto.getBoardTypeSeq(), boardDto.getBoardSeq());

					attachDto.setChngFileNm(destFile.getName());
					attachDto.setAccessUri(accessUri);
					attachDto.setOrgFileNm(mpf.getOriginalFilename());
					attachDto.setFileType(mpf.getContentType());
//					CommonUtil.getLogMessage(log, "editBoard", "isEmpty", 3);
					attachDto.setFileSize(mpf.getSize());
					attachDto.setSavePath(destFile.getAbsolutePath());
					
					boardattachRepository.addBoardAttach(attachDto);
				}
			}
			
			return cnt;
		}catch(DataAccessException dae) {
			CommonUtil.getLogMessage(log, "editBoard", "DataAccessException", dae.getMessage());
			return cnt;
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
		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardDto.getBoardTypeSeq(), boardDto.getBoardSeq());
		boolean empty = boardattachRepository.getBoardAttachEmpty(attachDto) > 0 ? false : true;
		
		if(!empty) {
			int cnt = boardattachRepository.deleteBoardAttachAll(attachDto);
			
			// 첨부파일 삭제가 안되었다
			if(cnt!=1) {
				FileDeleteException fde = new FileDeleteException("deleteBoard Fail");
				CommonUtil.getLogMessage(log, "deleteBoard", "FileDeleteException", fde.getMessage());
				throw fde;
			}
		}
		return boardRepository.deletetBoard(boardDto);
	}

	
	
	/* 게시글 첨부파일 리스트 */
	public List<BoardAttachDto> getBoardAttachAll(BoardAttachDto attachDto){
		return boardattachRepository.getBoardAttachAll(attachDto);
	}
	
	/* 게시글 첨부파일 리스트 */
	public BoardAttachDto getBoardAttach(Integer attachSeq){
		boardattachRepository.viewsDownloadHit(attachSeq);
		return boardattachRepository.getBoardAttach(attachSeq);
	}
	
	/* 다운로드 전체 파일 */
	public File getBoardAttachFileAll(Integer boardTypeSeq, Integer boardSeq ){
		
		List<BoardAttachDto> attachDtos = boardattachRepository.getBoardAttachAll(BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq));
		
		for(BoardAttachDto attachDto : attachDtos) {
			boardattachRepository.viewsDownloadHit(attachDto.getAttachSeq());
		}

		return fileUtil.makeZipFiles(attachDtos);
	}	
	
	/* 다운로드 전체 이름 */
	public String getDownloadAllName() {
		String savePathDay = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);		
		return savePathDay + ".zip";
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
