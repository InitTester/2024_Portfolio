package com.portfolio.www.forum.board.util.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.portfolio.www.common.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
/*springMVC에서 파일 다운로드 기능을 위한 커스텀 뷰 클래스*/
public class FileDownloadView extends AbstractView {
	
	public FileDownloadView() {
		// TODO Auto-generated constructor stub
		// MIME 타입 설정
		setContentType("application/download; charset-UTF-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("fileInfo");
		
		File file = (File)fileInfo.get("downloadFile");
		String downloadFileNm = (String) fileInfo.get("downloadFileNm");
		boolean ZipFile = (boolean)fileInfo.get("ZipFile");
		
		/* 응답 헤더 설정 */
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MISE") > -1;
		String fileNm = null;
		
		fileNm = ie ? URLEncoder.encode(downloadFileNm, "UTF-8") : new String(downloadFileNm.getBytes("UTF-8"),"ISO-8859-1");
		
		response.setHeader("Content-Disposition",  "attachment; filename=\"" + fileNm + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		
		try {
			
			fis=new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			CommonUtil.getLogMessage(log, "renderMergedOutputModel", "FileNotFoundException", fnfe.getMessage());
		} finally {
			if(fis !=null) {
				try {
					fis.close();
				}catch (IOException ioe) {
					// TODO: handle exception
					CommonUtil.getLogMessage(log, "renderMergedOutputModel", "IOException", ioe.getMessage());
				}
			}
			out.flush();
		}
		
		if(ZipFile) {
			file.delete();
		}
	}
}
