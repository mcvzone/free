package com.free.module.common.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.free.module.common.model.param.LoginParam;
import com.free.module.common.model.vo.UserInfoVo;
import com.free.module.core.exception.ValueCheckException;

public class CmLoginProcess {
private static final Logger logger = LoggerFactory.getLogger(CmLoginProcess.class);
	
	public UserInfoVo loginCheck(LoginParam loginModel) throws ValueCheckException{
		if( loginModel.getLoginId() == null || loginModel.getLoginPw() == null){
			throw new ValueCheckException("LoginId or LoginPw");
		}
		
		logger.debug("loginModel.getLoginId() : " + loginModel.getLoginId());
		logger.debug("loginModel.getLoginPw() : " + loginModel.getLoginPw());
		
		UserInfoVo userInfoVo = new UserInfoVo();
		userInfoVo.setId(loginModel.getLoginId());
		userInfoVo.setName("neo");
		userInfoVo.setAge("22");
		userInfoVo.setPower_level("1");
		userInfoVo.setSex("M");
		userInfoVo.setLocation("login");
		
		return userInfoVo;
	}
}
