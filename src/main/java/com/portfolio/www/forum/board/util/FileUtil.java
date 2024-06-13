package com.portfolio.www.forum.board.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.www.common.util.CommonUtil;
import com.portfolio.www.forum.board.dto.BoardAttachDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {

	@Value("#{config['img.save.path']}")
	private String imgPath;
	
	@Value("#{config['file.save.path']}")
	private String filePath;
	
	private String SAVE_PATH="";
	
	/* 파일 저장 */
	public File saveFile(MultipartFile mpf) {
		
		SAVE_PATH = setSAVE_PATH(imgPath);		
		File destFile = new File(SAVE_PATH);

		if(!destFile.exists()) {
			destFile.mkdirs();
		}
		
		int idx = mpf.getOriginalFilename().lastIndexOf(".");
		String expansion = mpf.getOriginalFilename().substring(idx);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		log.info("[saveFile] (SAVE_PATH : {}) (uuid : {}) (expansion : {}) ",SAVE_PATH, uuid, expansion);
		
		destFile = new File(SAVE_PATH, uuid + expansion);
		
		try {
			mpf.transferTo(destFile);
		} catch (IllegalStateException ise) {	
			CommonUtil.getLogMessage(log, "saveFile", "IllegalStateException", ise.getMessage());
			throw ise;
		} catch (IOException ioe) {				
			CommonUtil.getLogMessage(log, "saveFile", "IOException", ioe.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			CommonUtil.getLogMessage(log, "saveFile", "Exception", e.getMessage());
		}
		return destFile;
	}
	
	/* 파일 전체 다운로드(zip) */
	public File makeZipFiles(List<BoardAttachDto> attachDtos) {

		SAVE_PATH = setSAVE_PATH(filePath);
		File destFile = new File(SAVE_PATH);
		
		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;

		if(!destFile.exists()) {
			destFile.mkdirs();
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "") + ".zip";
		destFile = new File(SAVE_PATH,uuid);
		
		try {
			fos = new FileOutputStream(destFile);
			zipOut = new ZipOutputStream(fos);
			
			for(BoardAttachDto attachDto : attachDtos) {
				CommonUtil.getLogMessage(log, "makeZipFiles", "getSavePath", attachDto.getSavePath());
				File file = new File(attachDto.getSavePath());
				
				if(!file.exists()) {
					CommonUtil.getLogMessage(log, "makeZipFiles", "!file.exists() File not found", attachDto.getSavePath());
					continue;
				}
				
				fis = new FileInputStream(file);				
				ZipEntry zipEntry = new ZipEntry(attachDto.getOrgFileNm());
				zipOut.putNextEntry(zipEntry);
				
				byte[] bytes = new byte[1024];
				int length;
				while((length = fis.read(bytes))>=0) {
					zipOut.write(bytes,0,length);
				}
				
				fis.close();
				zipOut.closeEntry();
			}
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			CommonUtil.getLogMessage(log, "makeZipFiles", "FileNotFoundException", fnfe.getMessage());
		} catch (IOException io) {
			CommonUtil.getLogMessage(log, "makeZipFiles", "IOException", io.getMessage());
		} finally {
			if(zipOut != null) {
				try {
					zipOut.close();
				} catch(IOException zioe) {
					CommonUtil.getLogMessage(log, "makeZipFiles", "finally-IOException(zipOut)", zioe.getMessage());		
				}
			}
			
			if(fos != null) {
				try {
					fos.close();
				} catch(IOException fioe) {
					CommonUtil.getLogMessage(log, "makeZipFiles", "finally-IOException(fos)", fioe.getMessage());
				}
			}
		}
		return destFile;
	}

	public String setSAVE_PATH(String saveType) {

		String savePathDay = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);

		/* windows, mac 허용하기 위한 폴더경로 */
		 Path root = Paths.get(System.getProperty("user.name"));
		 
		if(root.toString().contains("c:")) {
			return "/" + root + saveType + savePathDay;
		}
		else {
			return saveType + savePathDay;
		}
	}
	
}
