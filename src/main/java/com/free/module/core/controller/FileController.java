package com.free.module.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.free.module.core.config.PathConfig;
import com.free.module.core.config.WordConfig;
import com.free.module.core.util.FileUtil;

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
    @RequestMapping(value = PathConfig.CONTEXT_DOWNLOAD, method = RequestMethod.POST)
    public void download(Model result, HttpServletRequest request, HttpServletResponse response,
    						@RequestParam(WordConfig.REQUIRED_SYSTEM_PARAM2)String fileName) throws IOException{
    	if( fileName.indexOf("/") > -1 ){
    		fileName = fileName.replaceAll("/", "");
    	}
    	
    	//::TODO biz category define.
        String fullPath = PathConfig.ATTACH_FILE_PATH + "/notice/" + fileName;
        File file = new File(fullPath);
        result.addAttribute(WordConfig.FILE, file);
        
        String sUserAgent, sFileName;
		sFileName = URLEncoder.encode(file.getName(), "utf-8");
		sFileName = sFileName.replaceAll("+", " ");
		
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
			
			logger.debug(fis.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
		
		out.flush();
    }
    
    
    /**
     * upload
     * @param fileData
     * @param result
     * @param request
     * @return
     */
	@RequestMapping(value = PathConfig.CONTEXT_UPLOAD, method = RequestMethod.POST)
	public String upload(@ModelAttribute("file") MultipartFile file
							, @ModelAttribute("action") String sAction
							, Model result, HttpServletRequest request) {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		JSONObject jSONObject = new JSONObject();
		
		if (file.getSize() == 0) {
			jSONObject.put("result", "fail");
			jSONObject.put("desc", "file size zero");
			return "freeUploadView";
		}

		String fileName = file.getOriginalFilename();

		try {
			inputStream = file.getInputStream();

			//::TODO biz category define.
			File newFile = new File(PathConfig.ATTACH_FILE_PATH + "/notice/" + fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			jSONObject.put("result", "ok");
			jSONObject.put("desc", "upload success");

		} catch (IOException e) {
			jSONObject.put("result", "fail");
			jSONObject.put("desc", e.getCause());
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		result.addAttribute(WordConfig.MISSION_RESULT, jSONObject);
		return "fileUploadView";
	}
	
	
	/**
	 * 파일 삭제
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = PathConfig.CONTEXT_DELETE, method=RequestMethod.POST)
	public void doDeleteFile(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		String sRootPath = null;
		String sFileType = request.getParameter("file_type");
		String sSvrFilePath = request.getParameter("file_path");
		String sSvrFileName = request.getParameter("file_name");
		
		
		// Root 파일 경로 지정
		if("FILE".equals(sFileType)){
			sRootPath = uploadPathResource.getPath();
		}else if("IMAGE".equals(sFileType)){
			sRootPath = session.getServletContext().getRealPath("/");
		}
		
		JSONObject jSONObject = new JSONObject();
		PrintWriter out=null;
		try {
			// 서버 파일 삭제
			File file = new File(sRootPath+sSvrFilePath, sSvrFileName);
			boolean isDeleted = FileUtil.deleteFile(file);
			
			if(isDeleted){
				jSONObject.put("result", "success");
			}else{
				jSONObject.put("result", "failure");
			}

			// 삭제 결과 전송
			out = response.getWriter();
			out.print(jSONObject);
			out.flush();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}finally{
			try{
				if(out!=null) out.close();
			}catch(Exception ex){}
		}
	}
}