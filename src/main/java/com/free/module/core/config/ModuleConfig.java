package com.free.module.core.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.free.module.core.exception.XmlParseException;
import com.free.module.core.util.XmlUtil;

public class ModuleConfig {

    private static Map<String, NodeList> mMission = new HashMap<String, NodeList>();

    private static final class SingletonHolder{
        static final ModuleConfig moduleConfig = new ModuleConfig();
        private SingletonHolder(){}
    }
    
    private ModuleConfig(){
        try{
            lodingModule();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static ModuleConfig getInstance(){
        return SingletonHolder.moduleConfig;
    }
    
    private void lodingModule() throws ParserConfigurationException, SAXException, IOException, XmlParseException{
        mMission = XmlUtil.getMissionXmlElement();
    }
    
    public NodeList getMission(String sMission){
        return mMission.get(sMission);
    }
}
