package com.modeler.action;

public class DeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String deleteDefinition() throws Exception {
		
		
		String deploymentId=this.request.getParameter("definitionId");

		this.repositoryService.deleteDeployment(deploymentId,true);
		this.response.sendRedirect("../index.jsp");
		
		return null;
	}
	
}
