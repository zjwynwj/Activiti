package com.modeler.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zt.activiti.util.ActivityUtil;
import com.zt.activiti.util.JsonMapper;

public class BaseAction extends ActionSupport{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request= ServletActionContext.getRequest();
	{
		
	}
	ProcessEngine processEngine=ActivityUtil.getProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();  
	
	JsonMapper jsonMapper=JsonMapper.buildNormalMapper();
	
	
	
	
	

}
