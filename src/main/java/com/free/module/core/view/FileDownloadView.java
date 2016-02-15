package com.free.module.core.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.free.module.core.config.WordConfig;
 
@Component(value="fileDownloadView")
public class FileDownloadView extends AbstractView {
    
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadView.class);
    
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String sUserAgent, sFileName;
        
        File file = (File)model.get(WordConfig.FILE);
        sFileName = URLEncoder.encode(file.getName(), "utf-8");
        sFileName = sFileName.replaceAll("+", " ");
        
        sUserAgent = request.getHeader("User-Agent");
        response.setContentType("application/download; utf-8");
        response.setContentLength((int) file.length());

        if( sUserAgent.indexOf("MSIE") > -1 || sUserAgent.indexOf("Trident") > -1 ){   //MSIE브라우저에서 한글인코딩
            response.setHeader("Content-Disposition", "attachment; filename=" + sFileName.replaceAll("\\+", "\\ ") + ";");
        } else {    // 모질라나 오페라 브라우저에서 한글 인코딩
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
}