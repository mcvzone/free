/**
 * preHandle : Controller 가 수행되기 전에 실행됩니다. 여기서는 이후 Controller를 수행할지 여부를 boolean 으로 return 하게 됩니다.
 * postHandle : Controller 가 수행된후 View 를 호출하기 전 상태입니다.
 * afterCompletion : View 작업까지 완료된 후 호출 됩니다. responseBody 를 이용할 경우 UI 에 이미 값을 전달후 해당 부분이 호출됩니다.
 */
package com.free.module.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterCeptor extends HandlerInterceptorAdapter{
private static final Logger logger = LoggerFactory.getLogger(SessionInterCeptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*
		CodeUtil codeUtil = CodeUtil.getInstance();
		String sScrId = request.getParameter(BaseCont.SCREEN_KEY);
		String sActId = request.getParameter(BaseCont.ACTION_KEY);
		
		CodeModel codeModel = codeUtil.getCodeItem("CO0000006", sScrId+"^"+sActId);
		SessionManager sm = new SessionManager(request);

		logger.debug("~ skip screen : " + codeModel.getCODE());
		logger.debug("~ session : " + sm.getTheOne());
		
		if( codeModel != null || sm.getTheOne() != null ){
			return true;
		}
		
		request.getRequestDispatcher(request.getContextPath() + "/login.do").forward(request, response);
		*/
        return false;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	      logger.debug("LoginInterceptor.postHandle process");
	      super.postHandle(request, response, handler, modelAndView);
	}
}
