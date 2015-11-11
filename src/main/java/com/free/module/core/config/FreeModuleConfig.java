package com.free.module.core.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.free.module.core.exception.XmlParseException;
import com.free.module.core.util.FreeUtil;

public class FreeModuleConfig {

	private static Map<String, NodeList> mMission = new HashMap<String, NodeList>();

	private static final class SingletonHolder{
		static final FreeModuleConfig freeModuleConfig = new FreeModuleConfig();
		private SingletonHolder(){}
	}
	
	private FreeModuleConfig(){
		try{
			lodingModule();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static FreeModuleConfig getInstance(){
		return SingletonHolder.freeModuleConfig;
	}
	
	private void lodingModule() throws ParserConfigurationException, SAXException, IOException, XmlParseException{
		mMission = FreeUtil.getMissionXmlElement();
	}
	
	public NodeList getMission(String sMission){
		return mMission.get(sMission);
	}
}
