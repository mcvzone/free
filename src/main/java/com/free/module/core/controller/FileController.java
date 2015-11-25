package com.free.module.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.free.module.core.config.PathConfig;
import com.free.module.core.config.WordConfig;
import com.free.module.core.util.FileUtil;
import com.free.module.core.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
public class FileController{
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Resource(name = "uploadPathResource")
	private FileSystemResource uploadPathResource;
 
	/**
	 * download
	 * @param result
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
    @RequestMapping(value = PathConfig.CONTEXT_DOWNLOAD)
    public void download(Model result, HttpServletRequest request, HttpServletResponse response,
    						@RequestParam(WordConfig.REQUIRED_SYSTEM_PARAM2)String fileName) throws IOException{
    	if( fileName.indexOf("/") > -1 ){
    		fileName = fileName.replaceAll("/", "");
    	}
    	
    	//::TODO biz category define.
        String fullPath = uploadPathResource.getPath() + fileName;
        File file = new File(fullPath);
        result.addAttribute(WordConfig.FILE, file);
        
        String sUserAgent, sFileName;
		sFileName = URLEncoder.encode(file.getName(), "utf-8");
		sFileName = sFileName.replace("+", " ");
		
		sUserAgent = request.getHeader("User-Agent");
		response.setContentType("application/download; utf-8");
		response.setContentLength((int) file.length());

		if( sUserAgent.indexOf("MSIE") > -1 || sUserAgent.indexOf("Trident") > -1 ){   //MSIE브라우저에서 한글인코딩
			response.setHeader("Content-Disposition", "attachment; filename=" + sFileName.replaceAll("\\+", "\\ ") + ";");
		} else {	// 모질라나 오페라 브라우저에서 한글 인코딩
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(sFileName.getBytes("UTF-8"), "ISO-8859-1").replaceAll("\\+", "\\ ") + ";");
		}

		response.setHeader("Content-Transfer-Encoding", "binary");

		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( fis != null ){ try { fis.close(); } catch (Exception e) {} }
			if( out != null ){ try { out.close(); } catch (Exception e) {} }
		}
    }
    
    
    /**
     * upload
     * @param fileData
     * @param result
     * @param request
     * @return
     * @throws IOException 
     */
	@RequestMapping(value = PathConfig.CONTEXT_UPLOAD, method = RequestMethod.POST)
	public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
		// 파일명
		String fileName = StringUtil.convertKorToUTF(file.getOriginalFilename());
		// 업로드 경로 설정
		String sRootPath = uploadPathResource.getPath();
		String sSvrFilePath = FileUtil.getFilePath(sRootPath);
		String sSvrFileName = FileUtil.getFileName(fileName);

		// 파일 관련 변수 선언
		InputStream inStream = null;    
		OutputStream outStream = null;
		// AJAX 관련 변수 선언
		PrintWriter outWriter=null; 
		try {
			// 파일 업로드 변수 설정
			inStream = file.getInputStream();  
			logger.debug("path:"+sRootPath+sSvrFilePath);
			File newFile = new File(sRootPath+sSvrFilePath, sSvrFileName);
			// 파일 업로드 실행
			boolean isUploaded = FileUtil.uploadFormFile(file, newFile);
			JSONObject jSONObject = new JSONObject();
			if(isUploaded){
				// 업로드 파일 정보 셋팅
				jSONObject.put(WordConfig.MISSION_RESULT, "ok");
				jSONObject.put(WordConfig.Json.NAME, fileName);
				jSONObject.put(WordConfig.Json.SAVE_NAME, sSvrFileName);
				jSONObject.put(WordConfig.Json.UPLOADED_PATH , sSvrFilePath);
				jSONObject.put(WordConfig.Json.FILE_SIZE, file.getSize());
				jSONObject.put(WordConfig.Json.TYPE, file.getContentType());
				
				// 한글 처리를 위한 response 설정
				response.setContentType("text/plain;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control", "no-chche");

			}else{
				jSONObject.put(WordConfig.MISSION_RESULT, "fail");
			}
			logger.debug("==============>jSONObject:"+jSONObject);
			// 업로드 결과 전송
			outWriter = response.getWriter();
			outWriter.print(jSONObject);
			outWriter.flush();
		} catch (IOException ex) {  
			logger.error(ex.getMessage(), ex);
		}finally{
			try{if(inStream!=null) inStream.close();}catch(Exception ex){}
			try{if(outStream!=null) outStream.close();}catch(Exception ex){}
		}
	}
	
	
	/**
	 * 파일 삭제
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = PathConfig.CONTEXT_DELETE, method=RequestMethod.POST)
	public void delete(HttpSession session, HttpServletResponse response,
			@RequestParam(WordConfig.Json.TYPE) String sFileType,
			@RequestParam(WordConfig.Json.UPLOADED_PATH) String sUploadedPath,
			@RequestParam(WordConfig.Json.SAVE_NAME) String sSaveName){
		
		logger.debug(sFileType + "_" + sUploadedPath + "_" + sSaveName);
		
		String sRootPath = uploadPathResource.getPath();
		/*
		if("FILE".equals(sFileType)){
			
		}else if("IMAGE".equals(sFileType)){
			sRootPath = session.getServletContext().getRealPath("/");
		}*/
		
		JSONObject jSONObject = new JSONObject();
		PrintWriter out=null;
		try {
			logger.debug("sRootPath+sUploadedPath [sSaveName] : " + sRootPath+sUploadedPath + "[ " + sSaveName + " ]");
			
			// 서버 파일 삭제
			File file = new File(sRootPath+sUploadedPath, sSaveName);
			if(FileUtil.deleteFile(file)) {
				jSONObject.put(WordConfig.MISSION_RESULT, "ok");
			} else {
				jSONObject.put(WordConfig.MISSION_RESULT, "fail");
			}

			// 삭제 결과 전송
			out = response.getWriter();
			out.print(jSONObject);
			out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			try{
				if(out!=null) out.close();
			}catch(Exception ex){}
		}
	}
}