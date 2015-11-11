package com.free.module.core.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.free.module.core.config.FreePathConfig;
import com.free.module.core.exception.XmlParseException;

public class FreeUtil {
	private static final Logger logger = LoggerFactory.getLogger(FreeUtil.class);
	
	/**
	 * parameter 정보를 Model에 매핑한다.
	 * @param mParam
	 * @param sModel
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object mappingParameterToModel(Map<String, String[]> mParam, Class<?> Model) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> Var;
		String sName;
		Object instance = Model.newInstance();
		
		Field[] fields = Model.getDeclaredFields();
		for( Field field : fields ){
			field.setAccessible(true);
			Var = field.getType();
			sName = field.getName();
			
			if( mParam.get(sName) != null ){
				//String[] type
				if( Var.equals(Class.forName("[Ljava.lang.String;")) ){
					field.set(instance, mParam.get(sName));
				}
				//String type
				else if( Var.equals(Class.forName("java.lang.String"))){
					field.set(instance, mParam.get(sName)[0]);
				}
				//int
				else if( Var.equals(Class.forName("int"))){
					field.set(instance, Integer.valueOf(mParam.get(sName)[0]));
				}
				//double
				else if( Var.equals(Class.forName("double"))){
					field.set(instance, Double.valueOf(mParam.get(sName)[0]));
				}
				//int
				else if( Var.equals(Class.forName("long"))){
					field.set(instance, Long.valueOf(mParam.get(sName)[0]));
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * xml 의 정보를 가져와 파라매터로 받은 map 객체에 셋팅한다.
	 * @param mMission
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XmlParseException
	 */
	public static Map<String, NodeList> getMissionXmlElement() throws ParserConfigurationException, SAXException, IOException, XmlParseException{
		Map<String, NodeList> mMission = new HashMap<String, NodeList>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document docBizConfig, docBaMission;
		NodeList bizs, missions;
		Node biz, mission;
		
		docBizConfig = builder.parse(new File(FreePathConfig.CONFIG_PATH + "freeModuleList.xml"));
		bizs = docBizConfig.getElementsByTagName("bizs");
		String sId, sBizName, sBizMission;
		
		//logger.debug("biz size : " + bizs.item(0).getChildNodes().getLength());
		
		//mission
		for( int i=0; i < bizs.item(0).getChildNodes().getLength(); i++ ){
			if( bizs.item(0).getChildNodes().item(i).ELEMENT_NODE != bizs.item(0).getChildNodes().item(i).getNodeType() ){
				continue;
			}
			
			sBizName = bizs.item(0).getChildNodes().item(i).getNodeName();
			sBizMission = bizs.item(0).getChildNodes().item(i).getTextContent();
			
			docBaMission = builder.parse(new File(FreePathConfig.BIZ_PATH + sBizName + "/" + sBizMission));
			missions = docBaMission.getElementsByTagName("mission");
			
			for( int j=0; j<missions.getLength(); j++ ){
				mission = missions.item(j);
				NamedNodeMap nnm = mission.getAttributes();

				if( nnm.getNamedItem("id") == null ){
					throw new XmlParseException("Please, Check the mission attribute. It's id.");
				} else if( "".equals(nnm.getNamedItem("id").getNodeValue()) ){
					throw new XmlParseException("Empty String id.");
				}
				
				sId = nnm.getNamedItem("id").getNodeValue();
				if( mMission.containsKey(sId) ){
					throw new XmlParseException("already mission. [" + sId + "]");
				}
				
				logger.info("load mission : " + sId);
				mMission.put(sId, mission.getChildNodes());
			}
		}
		return mMission;
	}
}
