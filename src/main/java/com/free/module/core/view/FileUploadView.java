package com.free.module.core.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.free.module.core.config.WordConfig;

import net.sf.json.JSONObject;
 
@Component(value="fileUploadView")
public class FileUploadView extends AbstractView {
    
    private static final Logger logger = LoggerFactory.getLogger(FileUploadView.class);
    
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        response.setContentType("text/html; charset=UTF-8");
        
        JSONObject jsonResult = (JSONObject)model.get(WordConfig.MISSION_RESULT);
        PrintWriter out = response.getWriter();
        out.print(jsonResult);
    }
}