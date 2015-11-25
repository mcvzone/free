package com.free.module.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.free.module.core.config.ModuleConfig;
import com.free.module.core.config.PathConfig;
import com.free.module.core.config.WordConfig;
import com.free.module.core.exception.ReturnToVoidException;
import com.free.module.core.util.XmlUtil;

@Controller
public class AjaxController{
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
    
	@SuppressWarnings("unchecked")
    @RequestMapping(value=PathConfig.CONTEXT_AJAX, method = RequestMethod.POST)
	public String post(Model result, HttpServletRequest request
			, @RequestParam(value=WordConfig.REQUIRED_SYSTEM_PARAM1) String sMission
			, @RequestParam Map<String, String> map) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
															NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ReturnToVoidException {
		String sNodeName, sValue;
		NodeList missions, returnPages;
		Node mission, returnPage;
		Map<String, String> mReturnPages = new HashMap<String, String>();
		
		missions = ModuleConfig.getInstance().getMission(sMission);

		Object instance = null, model = null, returnObject;
		Class<?> klass = null, argType = null;
		Method method = null;

		for(int i=0;i<missions.getLength();i++){
			mission = missions.item(i);
			sNodeName = mission.getNodeName();
			sValue = mission.getTextContent();
			
			if( "class".equals(sNodeName) ){
				klass = Class.forName(sValue);
				instance = klass.newInstance();
			} else if( "arg-type".equals(sNodeName)){
				argType = Class.forName(sValue);
			} else if( "method-name".equals(sNodeName)){
				if( argType != null ){
					method = klass.getDeclaredMethod(sValue, argType);
				} else {
					method = klass.getDeclaredMethod(sValue);
				}
			}/* else if( "return-type".equals(sNodeName)){
				sResult = sValue;
			} */else if( "return-page".equals(sNodeName)){
				returnPages = mission.getChildNodes();
				if( returnPages != null && returnPages.getLength() > 0){
					for( int z=0; z<returnPages.getLength(); z++ ){
						returnPage = returnPages.item(z);
						mReturnPages.put(returnPage.getNodeName(), returnPage.getTextContent());
					}
				}
			}
		}
		
		if( klass != null ){
			
			//setting argument
			if( argType != null ){
				if( argType.equals(Map.class) ){
					model = map;
				} else {
					model = XmlUtil.mappingParameterToModel(request.getParameterMap(), argType);
				}
			}
			
			//invoke mission
			if( method != null ){
				if( !void.class.equals(method.getReturnType())){
					if( argType != null ){
						returnObject = method.invoke(instance, model);
					} else {
						returnObject = method.invoke(instance);
					}
					
					result.addAttribute(WordConfig.MISSION_RESULT, returnObject);
				} else {
					throw new ReturnToVoidException("can't void return.");
				}
			}
		}
		return "ajaxResultView";
	}
}