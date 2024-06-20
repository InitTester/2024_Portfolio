package com.portfolio.www.forum.board.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dao.mybatis.BoardAttachRepository;
import com.portfolio.www.forum.board.dto.BoardAttachDto;
import com.portfolio.www.forum.board.dto.BoardDto;
import com.portfolio.www.forum.board.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class BoardAttachService {

	@Autowired
	private BoardAttachRepository boardattachRepository;

	@Autowired
	private FileUtil fileUtil;

	@Value("#{config['access.host']}")
	private String accessUriPath;
	
	/* 게시글 첨부파일 추가 */
	@Transactional
	public void addBoardAttach(File destFile, BoardDto boardDto, MultipartFile mpf) {
		
		try {
			CommonUtil.getLogMessage(log, "BoardAttachService", "addBoardAttach", destFile.getPath());
			
			Integer boardSeq = boardDto.getBoardSeq();
			Integer boardTypeSeq = boardDto.getBoardTypeSeq();

			BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);

			String accessUri = "";
			
			if(!destFile.getPath().contains("c:")) {
				accessUri = accessUriPath +"/" + destFile.getPath().split("app")[1].substring(1);// + destFile.getName();
				log.info("accessUri : {}",accessUri);
			}
			
			attachDto.setChngFileNm(destFile.getName());
			attachDto.setAccessUri(accessUri);
			attachDto.setOrgFileNm(mpf.getOriginalFilename());
			attachDto.setFileType(mpf.getContentType());
			attachDto.setFileSize(mpf.getSize());
			attachDto.setSavePath(destFile.getAbsolutePath());
			
			boardattachRepository.addBoardAttach(attachDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("[addBoardAttach] Exception : {}",e.getMessage());
			throw e;
		}
	}

	/* 게시글 첨부파일 수정시 */
	public void editBoardAttach(File destFile, BoardAttachDto attachDto, Integer attachSeq, MultipartFile mpf) {
		
		try {
			boardattachRepository.deleteBoardAttach(attachSeq);

			attachDto.setOrgFileNm(mpf.getOriginalFilename());
			
			String accessUri = "";
			
			log.info("[editBoardAttach] (destFile.getPath().split(\"app\")[1]) : {}",destFile.getPath().split("app")[1]);
			
			if(!destFile.getPath().contains("c:")) {
				accessUri = accessUriPath + destFile.getPath().split("app")[1].substring(1);// + destFile.getName();	
			}
			
			attachDto.setChngFileNm(destFile.getName());
			attachDto.setAccessUri(accessUri);
			attachDto.setFileType(mpf.getContentType());
//			CommonUtil.getLogMessage(log, "editBoard", "isEmpty", 3);
			attachDto.setFileSize(mpf.getSize());
			attachDto.setSavePath(destFile.getAbsolutePath());
			
			boardattachRepository.addBoardAttach(attachDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("[editBoardAttach] Exception {} ",e.getMessage());
			throw e;
		}
	}
	
	/* 게시글 첨부파일 삭제 */
	public void deleteBoardAttach(Integer attachSeq) {

		boardattachRepository.deleteBoardAttach(attachSeq);
	}
	
	/* 게시글 첨부파일 전체삭제 */
	
	public void deleteBoardAttachAll(BoardDto boardDto) {

		try {
			Integer boardSeq = boardDto.getBoardSeq();
			Integer boardTypeSeq = boardDto.getBoardTypeSeq();

			BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);
			log.info("attachDto : {}",attachDto);
			boolean empty = boardattachRepository.getBoardAttachEmpty(attachDto) > 0 ? true : false;
			log.info("empty : {}",empty);
			if(empty) {
				boardattachRepository.deleteBoardAttachAll(attachDto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("e : {}",e.getMessage());
			throw e;
		}
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
}
