package com.zt.activiti.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class ActivitiService {
	
	private ProcessEngine processEngine=ActivityUtil.getProcessEngine();
	private RepositoryService repositoryService=ActivityUtil.getRepositoryService();
	
	
	/**
	 * @category 通过model 部署流程
	 * @param modelId
	 */
	public void deployById(String modelId)  {
		
		//获取设计好的model信息
		Model modelData=repositoryService.getModel(modelId);
		
		try {
			//获取model的json对象的内容
			String xml=new String(repositoryService.getModelEditorSource(modelData.getId()),"utf-8");
			
			//将String 转化为json对象
			JsonNode modelNode = (ObjectNode) new ObjectMapper().readTree(xml);
			
			//将model的json对象转化为bpmn 的model对象
		    BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		    
		    //bpmn 的model对象 转化为xml 的字节流
		    byte[]  bpmnBytes = new BpmnXMLConverter().convertToXML(model);
		    String processName = modelData.getName() + ".bpmn20.xml";
		    
		    //部署流程
			repositoryService.createDeployment()
				       .addInputStream(processName, 
				    		   new ByteArrayInputStream(bpmnBytes))
						.deploy();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("部署成功");
	}

}
