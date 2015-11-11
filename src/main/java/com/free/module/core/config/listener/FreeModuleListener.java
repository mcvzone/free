package com.free.module.core.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.free.module.core.config.FreeModuleConfig;

public class FreeModuleListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		FreeModuleConfig.getInstance();
	}
	
}
