
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.zt.activiti.util.ActivityUtil;



public class TestGetEngine  {
    Logger log=	Logger.getLogger(TestGetEngine.class);
	ProcessEngine processEngine = ActivityUtil.getProcessEngine();


	/**
	 * 获取流程引擎
	 */
	@Test
	public void test() {
		
		 ProcessEngine processEngine = ProcessEngineConfiguration  
	                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")  
	                .buildProcessEngine();  
	        RepositoryService repositoryService = processEngine.getRepositoryService();  
	        System.out.println(processEngine.getName());
	        System.out.println(repositoryService.toString());
	        
	        processEngine.close();
		
	}
	
	
	/**
	 *部署流程
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void test2() throws UnsupportedEncodingException {

		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		String xml="<?xml version=\"1.0\" encoding=\"GBK\"?> <definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">   <process id=\"myProcess\" name=\"My process\" isExecutable=\"true\">     <startEvent id=\"startevent1\" name=\"Start\"></startEvent>     <userTask id=\"usertask1\" name=\"发起流程\" activiti:assignee=\"tom\"></userTask>     <userTask id=\"usertask2\" name=\"领导审批\" activiti:assignee=\"kit\"></userTask>     <sequenceFlow id=\"flow1\" sourceRef=\"startevent1\" targetRef=\"usertask1\"></sequenceFlow>     <sequenceFlow id=\"flow2\" sourceRef=\"usertask1\" targetRef=\"usertask2\"></sequenceFlow>     <endEvent id=\"endevent1\" name=\"End\"></endEvent>     <sequenceFlow id=\"flow3\" sourceRef=\"usertask2\" targetRef=\"endevent1\"></sequenceFlow>   </process>   <bpmndi:BPMNDiagram id=\"BPMNDiagram_myProcess\">     <bpmndi:BPMNPlane bpmnElement=\"myProcess\" id=\"BPMNPlane_myProcess\">       <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">         <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"415.0\" y=\"70.0\"></omgdc:Bounds>       </bpmndi:BPMNShape>       <bpmndi:BPMNShape bpmnElement=\"usertask1\" id=\"BPMNShape_usertask1\">         <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"380.0\" y=\"180.0\"></omgdc:Bounds>       </bpmndi:BPMNShape>       <bpmndi:BPMNShape bpmnElement=\"usertask2\" id=\"BPMNShape_usertask2\">         <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"380.0\" y=\"340.0\"></omgdc:Bounds>       </bpmndi:BPMNShape>       <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">         <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"415.0\" y=\"520.0\"></omgdc:Bounds>       </bpmndi:BPMNShape>       <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">         <omgdi:waypoint x=\"432.0\" y=\"105.0\"></omgdi:waypoint>         <omgdi:waypoint x=\"432.0\" y=\"180.0\"></omgdi:waypoint>       </bpmndi:BPMNEdge>       <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">         <omgdi:waypoint x=\"432.0\" y=\"235.0\"></omgdi:waypoint>         <omgdi:waypoint x=\"432.0\" y=\"340.0\"></omgdi:waypoint>       </bpmndi:BPMNEdge>       <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">         <omgdi:waypoint x=\"432.0\" y=\"395.0\"></omgdi:waypoint>         <omgdi:waypoint x=\"432.0\" y=\"520.0\"></omgdi:waypoint>       </bpmndi:BPMNEdge>     </bpmndi:BPMNPlane>   </bpmndi:BPMNDiagram> </definitions>";
		System.out.println(xml);
		
		repositoryService.createDeployment()
						 .addInputStream("processNew.bpmn20.xml", new ByteArrayInputStream(xml.getBytes("GBK")))
						 .deploy();
		
		
	}
	
	
	@Test
	public void test3(){
		
		log.error("11");
		
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
			
		System.out.println( repositoryService.createProcessDefinitionQuery().list());
		
	}
	
	
	@Test
	public void test4(){
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.deleteDeployment("101", true);
		repositoryService.deleteDeployment("1", true);
		repositoryService.deleteDeployment("201", true);
	}
}
