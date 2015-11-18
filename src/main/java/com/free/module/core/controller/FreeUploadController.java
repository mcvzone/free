/**
 * http://micropilot.tistory.com/2559
 */
package com.free.module.core.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.free.module.core.model.FreeUploadFileModel;
import com.free.module.core.util.FreeFileValidator;

@Controller
public class FreeUploadController{
	@Autowired
	private FreeFileValidator fileValidator;

	@RequestMapping(value = "/upload/form", method = RequestMethod.GET)
	public String getUploadForm(@ModelAttribute("uploadedFile") FreeUploadFileModel uploadedFile, BindingResult result) {
		return "upload/uploadForm";
	}

	/*
	 * @ModelAttribute("desc") String desc 는 브라우저에서 전송되는 desc 라는 파라미터를 Model 객체에
	 * desc 라는 이름으로 저장한다는 것을 의미한다. Model 에 저장된 데이터는 내부에서 request 객체에 저장되므로 뷰에서
	 * 접근할 수 있게 된다
	 */
	@RequestMapping(value = "/upload/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUploaded(@ModelAttribute("desc") String desc,
			@ModelAttribute("uploadedFile") FreeUploadFileModel uploadedFile, BindingResult result) {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		// 전송된 폼 필드 데이터(desc)를 받는다
		System.out.println("desc=" + desc);

		// 업로드된 파일을 임의의 경로로 이동한다
		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String fileName = file.getOriginalFilename();

		if (result.hasErrors()) {
			return new ModelAndView("upload/uploadForm");
		}

		try {
			inputStream = file.getInputStream();

			File newFile = new File("D:/test/upload/" + fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ModelAndView("upload/uploadedFile", "filename", fileName);
	}

	@RequestMapping("/upload/download")
	@ResponseBody
	public byte[] getImage(HttpServletResponse response, @RequestParam String filename) throws IOException {
		File file = new File("D:/test/upload/" + filename);
		byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

		// 한글은 http 헤더에 사용할 수 없기때문에 파일명은 영문으로 인코딩하여 헤더에 적용한다
		String fn = new String(file.getName().getBytes(), "iso_8859_1");

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
		response.setContentLength(bytes.length);
		response.setContentType("image/jpeg");

		return bytes;
	}

}