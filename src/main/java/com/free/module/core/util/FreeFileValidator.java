package com.free.module.core.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.free.module.core.model.FreeUploadFileModel;

@Service("fileValidator")
public class FreeFileValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object uploadedFile, Errors errors) {

		FreeUploadFileModel file = (FreeUploadFileModel) uploadedFile;

		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!");
		}
	}
}