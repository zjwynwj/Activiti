package com.modeler.action;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.zt.activiti.util.ActivityUtil;
import com.zt.activiti.util.JsonMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.struts2.ServletActionContext;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class OpenAction extends ActionSupport  {

	/**
	 * 获取流程模型信息
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	ProcessEngine processEngine=ActivityUtil.getProcessEngine();
	
	JsonMapper jsonMapper=JsonMapper.buildNormalMapper();
    Logger logger=	Logger.getLogger(OpenAction.class);
	@Override
	public String execute() throws Exception {
		
		String modelId=request.getParameter("id");
		System.out.println(modelId);

		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		Model model = repositoryService.getModel(modelId);

		
		if (model == null) {
			logger.info(modelId+":  model({}) is null");
			model = repositoryService.newModel();
			repositoryService.saveModel(model);
			modelId = model.getId();
		}
		Map root = new HashMap();
		root.put("modelId", model.getId());
		root.put("name", model.getName());
		root.put("revision", model.getVersion());
		root.put("description", model.getMetaInfo());
		
		
		byte[] bytes = repositoryService.getModelEditorSource(model.getId());//重数据库中获取model对应的xml数据

		if (bytes != null) {
			//如果没有对应数据
			String modelEditorSource = new String(bytes, "utf-8");
			logger.info("modelEditorSource : {} "+ modelEditorSource);
			Map modelNode = jsonMapper.fromJson(modelEditorSource, Map.class);
			root.put("model", modelNode);
			
		} else {
			Map modelNode = new HashMap();
			modelNode.put("id", "canvas");
			modelNode.put("resourceId", "canvas");

			Map stencilSetNode = new HashMap();
			stencilSetNode.put("namespace",
					"http://b3mn.org/stencilset/bpmn2.0#");
			modelNode.put("stencilset", stencilSetNode);

			model.setMetaInfo(jsonMapper.toJson(root));
			model.setName("name");
			model.setKey("key");

			root.put("model", modelNode);
			
		}
		String str=jsonMapper.toJson(root);
		System.out.println(str);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(str);
		return null;
		
	}
	
	

}
