package com.free.module.core.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.free.module.core.config.ModuleConfig;

public class PrepareListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ModuleConfig.getInstance();
	}
	
}
