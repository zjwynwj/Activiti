package com.modeler.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Enumeration;
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

public class SaveAction extends ActionSupport  {

	/**
	 * 获取流程模型信息
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request= ServletActionContext.getRequest();

	ProcessEngine processEngine=ActivityUtil.getProcessEngine();
	
	JsonMapper jsonMapper=JsonMapper.buildNormalMapper();
    Logger logger=	Logger.getLogger(SaveAction.class);
	@Override
	public String execute() throws Exception {
		
		
		String modelId=request.getParameter("id");
//System.out.println(modelId);
		String jsonXml=request.getParameter("json_xml");
//System.out.println(jsonXml);
		String name=request.getParameter("name");
//System.out.println(name);
		String svg_xml=request.getParameter("svg_xml");
//System.out.println(svg_xml);
		String metaInfo=request.getParameter("description");


		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		Model model = repositoryService.getModel(modelId);
		model.setName(name);
		model.setMetaInfo(metaInfo);
		repositoryService.saveModel(model);
		
	/*	String glossayXml;
		String id;
		String description;
		String jsonXml;
		String name;
		String namespace;
		String parent;
		String svgXml;
		String type;
		String views;*/
		
		
		repositoryService.addModelEditorSource(model.getId(),
				jsonXml.getBytes("utf-8"));
		
		
	//	  InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes()); 
		  
         // TranscoderInput input = new TranscoderInput(svgStream);  

		repositoryService.addModelEditorSourceExtra(model.getId(), 
				svg_xml.getBytes("utf-8"));
		
		response.getWriter().write("{}");
		return null;
		
	}
	
	

}
