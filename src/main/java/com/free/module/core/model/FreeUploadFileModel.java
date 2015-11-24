package com.free.module.core.model;

import org.springframework.web.multipart.MultipartFile;

public class FreeUploadFileModel {

	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}	
}