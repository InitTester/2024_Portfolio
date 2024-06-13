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
import com.portfolio.www.forum.board.exception.FileDeleteException;
import com.portfolio.www.forum.board.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardAttachService {

	@Autowired
	private BoardAttachRepository boardattachRepository;

	@Autowired
	private FileUtil fileUtil;

	@Value("#{config['img.access.host']}")
	private String accessUriPath;
	
	/* 게시글 첨부파일 추가 */
	public void addBoardAttach(File destFile, BoardDto boardDto, MultipartFile mpf) {
		
		CommonUtil.getLogMessage(log, "BoardAttachService", "addBoardAttach", destFile.getPath());
		
		Integer boardSeq = boardDto.getBoardSeq();
		Integer boardTypeSeq = boardDto.getBoardTypeSeq();

		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);
	
		String accessUri = "";
		
		if(!destFile.getPath().contains("c:")) {
			accessUri = accessUriPath + destFile.getPath().split("app")[1].substring(1);// + destFile.getName();	
		}
		
		attachDto.setChngFileNm(destFile.getName());
		attachDto.setAccessUri(accessUri);
		attachDto.setOrgFileNm(mpf.getOriginalFilename());
		attachDto.setFileType(mpf.getContentType());
		attachDto.setFileSize(mpf.getSize());
		attachDto.setSavePath(destFile.getAbsolutePath());
		
		boardattachRepository.addBoardAttach(attachDto);
	}

	/* 게시글 첨부파일 수정시 */
	public void editBoardAttach(File destFile, BoardDto boardDto, MultipartFile mpf) {
		
		Integer boardSeq = boardDto.getBoardSeq();
		Integer boardTypeSeq = boardDto.getBoardTypeSeq();

		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);
		attachDto.setOrgFileNm(mpf.getOriginalFilename());
		
		if(boardattachRepository.getBoardAttachOneEmpty(attachDto)!=0) {
			destFile = fileUtil.saveFile(mpf);

			String accessUri = "";
			
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
		}
	}
	
	/* 게시글 첨부파일 삭제 */
	public void deleteBoardAttach(BoardDto boardDto) {

		Integer boardSeq = boardDto.getBoardSeq();
		Integer boardTypeSeq = boardDto.getBoardTypeSeq();

		BoardAttachDto attachDto = BoardAttachDto.setBoardAttachDto(boardTypeSeq, boardSeq);
		
		boolean empty = boardattachRepository.getBoardAttachEmpty(attachDto) > 0 ? false : true;
		
		if(!empty) {
			boardattachRepository.deleteBoardAttachAll(attachDto);
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
