package com.modeler.action;

import com.zt.activiti.util.ActivitiService;

public class DeployAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ActivitiService activitiService=new ActivitiService();

	@Override
	public String execute() throws Exception {
		
		
		
		String action=this.request.getParameter("action");
		String modelId=this.request.getParameter("modelId");
		System.out.println(modelId);
		activitiService.deployById(modelId);
		
		this.response.setContentType("text/html;charset=UTF-8");
		this.response.getWriter().write("{msg:部署成功}");
		return null;
	}

}
