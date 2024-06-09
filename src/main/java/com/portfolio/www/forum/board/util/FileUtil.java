package com.portfolio.www.forum.board.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.www.common.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {
	
	String savePathDay = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
	Path root = Paths.get(System.getProperty("user.name"));
	
	/* windows, mac 허용하기 위한 폴더경로 */
//	private String SAVE_PATH = "/Users/"+root +"/pf/file/" + savePathDay;
	
//	private String SAVE_PATH = ResultsetRowsStatic.get
	
	@Value("#{config['file.save.path']}")
	private String savePath;
	
	public File saveFile(MultipartFile mpf, HttpServletRequest request) {
		
//		String SAVE_PATH = request.getRealPath("\\images\\") + savePathDay;
		String SAVE_PATH = savePath + savePathDay;

		CommonUtil.getLogMessage(log, "saveFile", "SAVE_PATH", SAVE_PATH);
		CommonUtil.getLogMessage(log, "saveFile", "root", root);
		
		File destFile = new File(SAVE_PATH);
		
		CommonUtil.getLogMessage(log, "saveFile", "AbsolutePath", destFile.getAbsolutePath());
		
		if(!destFile.exists()) {
			destFile.mkdirs();
		}
		
		int idx = mpf.getOriginalFilename().lastIndexOf(".");
		String expansion = mpf.getOriginalFilename().substring(idx);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		destFile = new File(SAVE_PATH, uuid + expansion);
		
		try {
			mpf.transferTo(destFile);
		} catch (IllegalStateException ise) {	
			CommonUtil.getLogMessage(log, "saveFile", "IllegalStateException", ise.getMessage());
		} catch (IOException ioe) {				
			CommonUtil.getLogMessage(log, "saveFile", "IOException", ioe.getMessage());
		}
		return destFile;
	}
}
