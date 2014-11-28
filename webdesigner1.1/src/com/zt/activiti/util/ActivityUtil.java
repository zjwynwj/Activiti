package com.zt.activiti.util;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

public class ActivityUtil {
	
	private static ProcessEngine processEngine;
	private static RepositoryService repositoryService;
	
	static{
		
		processEngine=ProcessEngineConfiguration  
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")  
                .buildProcessEngine();
		repositoryService = processEngine.getRepositoryService();
	}

	public ActivityUtil() {
		
	}
	
	/**
	 * @category 获取流程引擎
	 * @return ProcessEngine
	 */
	public static ProcessEngine getProcessEngine() {
		return processEngine;
	}

	
	/**
	 * @category 获取流程仓库
	 * @return RepositoryService
	 */
	public static RepositoryService getRepositoryService() {
		return repositoryService;
	}


}
