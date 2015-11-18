package com.free.module.core.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.free.module.core.config.FreePathConfig;
import com.free.module.core.config.FreeReservedWordConfig;

@Controller
public class FreeDNController{
	
	private static final Logger logger = LoggerFactory.getLogger(FreeDNController.class);
 
    @RequestMapping(FreePathConfig.CONTEXT_DOWNLOAD)
    public String download(Model result, @RequestParam(FreeReservedWordConfig.REQUIRED_SYSTEM_PARAM2)String fileName){
    	if( fileName.indexOf("/") > -1 ){
    		fileName = fileName.replaceAll("/", "");
    	}
    	
    	//::TODO biz category define.
        String fullPath = FreePathConfig.ATTACH_FILE_PATH + "/notice/" + fileName;
        File file = new File(fullPath);
        result.addAttribute("file", file);
        return "fileDownloadView";
    }
}