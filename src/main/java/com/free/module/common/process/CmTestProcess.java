package com.free.module.common.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.free.module.common.model.param.TestParam;

public class CmTestProcess {
    private static final Logger logger = LoggerFactory.getLogger(CmTestProcess.class);
    
    public String test(TestParam testModel){
        return "success";
    }
}
