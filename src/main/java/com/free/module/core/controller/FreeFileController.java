package com.free.module.core.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.free.module.core.config.FreePathConfig;
import com.free.module.core.config.FreeReservedWordConfig;
import com.free.module.core.model.FreeUploadFileModel;

import net.sf.json.JSONObject;

@Controller
public class FreeFileController{
	
	private static final Logger logger = LoggerFactory.getLogger(FreeFileController.class);
 
	/**
	 * download
	 * @param result
	 * @param fileName
	 * @return
	 */
    @RequestMapping(value = FreePathConfig.CONTEXT_DOWNLOAD, method = RequestMethod.POST)
    public String download(Model result, @RequestParam(FreeReservedWordConfig.REQUIRED_SYSTEM_PARAM2)String fileName){
    	if( fileName.indexOf("/") > -1 ){
    		fileName = fileName.replaceAll("/", "");
    	}
    	
    	//::TODO biz category define.
        String fullPath = FreePathConfig.ATTACH_FILE_PATH + "/notice/" + fileName;
        File file = new File(fullPath);
        result.addAttribute(FreeReservedWordConfig.FILE, file);
        return "fileDownloadView";
    }
    
    
    /**
     * upload
     * @param fileData
     * @param result
     * @param request
     * @return
     */
	@RequestMapping(value = FreePathConfig.CONTEXT_UPLOAD, method = RequestMethod.POST)
	public String upload(@ModelAttribute("file") FreeUploadFileModel fileData
							, @ModelAttribute("action") String sAction
							, Model result, HttpServletRequest request) {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		JSONObject jSONObject = new JSONObject();
		
		MultipartFile file = fileData.getFile();
		if (file.getSize() == 0) {
			jSONObject.put("result", "fail");
			jSONObject.put("desc", "file size zero");
			return "freeUploadView";
		}

		String fileName = file.getOriginalFilename();

		try {
			inputStream = file.getInputStream();

			//::TODO biz category define.
			File newFile = new File(FreePathConfig.ATTACH_FILE_PATH + "/notice/" + fileName);
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

		result.addAttribute(FreeReservedWordConfig.MISSION_RESULT, jSONObject);
		return "fileUploadView";
	}
}