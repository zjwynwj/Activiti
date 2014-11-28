<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.runtime.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@page import="com.zt.activiti.util.*"%>
<%

	ProcessEngine processEngine =ActivityUtil.getProcessEngine();

	// Get Activiti services
	RepositoryService repositoryService = processEngine
		.getRepositoryService();
	RuntimeService runtimeService = processEngine
		.getRuntimeService();
	TaskService taskService = processEngine
		.getTaskService();
	List<Model> models=repositoryService.createModelQuery().list();
	
%>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
<a href="deploy.jsp">deploy</a>
<a href="h2database">h2database</a>
<hr>

<table border="1" width="100%">
  <legend>process definition</legend>
  <thead>
    <tr>
	  <th>key</th>
	  <th>name</th>
	  <th>version版本</th>
	  <th>deployId</th>
	  <th>&nbsp;</th>
	</tr>
  </thead>
  <tbody>
<%
	for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().list()) {
		pageContext.setAttribute("processDefinition", processDefinition);
%>
    <tr>
	  <td>${processDefinition.key}</td>
	  <td>${processDefinition.name}</td>
	  <td>${processDefinition.version}</td>
	  <td>${processDefinition.deploymentId}</td>
	  <td>
	    <a href="start.jsp?id=${processDefinition.id}">start</a>
	    <a href="graph.jsp?processDefinitionId=${processDefinition.id}">graph</a>
	    <a href="modeler/delete!deleteDefinition.action?definitionId=${processDefinition.deploymentId}">delete</a>
	  </td>
	</tr>
<%
	}
%>
  </tbody>
</table>

<hr>

<table border="1" width="100%">
  <legend>process instance</legend>
  <thead>
    <tr>
	  <th>id</th>
	  <th>process definition</th>
	  <th>&nbsp;</th>
	</tr>
  </thead>
  <tbody>
<%
	for (ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().list()) {
		pageContext.setAttribute("processInstance", processInstance);
%>
    <tr>
	  <td>${processInstance.id}</td>
	  <td>${processInstance.processDefinitionId}</td>
	  <td>
	    <a href="graph.jsp?processInstanceId=${processInstance.id}">graph</a>
	  </td>
	</tr>
<%
	}
%>
  </tbody>
</table>

<hr>

<table border="1" width="100%">
  <legend>task</legend>
  <thead>
    <tr>
	  <th>id</th>
	  <th>name</th>
	  <th>assignee</th>
	  <th>&nbsp;</th>
	</tr>
  </thead>
  <tbody>
<%
	for (Task task : taskService.createTaskQuery().list()) {
		pageContext.setAttribute("task", task);
%>
    <tr>
	  <td>${task.id}</td>
	  <td>${task.name}</td>
	  <td>${task.assignee}</td>
	  <td>
	    <a href="complete.jsp?id=${task.id}">complete</a>
	    <a href="graph.jsp?taskId=${task.id}">graph</a>
	  </td>
	</tr>
<%
	}
%>
  </tbody>
</table>

<table border="1" width="100%">
  <legend>model</legend>
   <thead>
    <tr>
	  <th>id</th>
	  <th>name</th>
	  <th>创建时间</th>
	  <th>key</th>
	  <th>&nbsp;</th>
	</tr>
  </thead>
  <tbody>
  
<%  
  for(Model m: models){
		pageContext.setAttribute("m", m);

	  %> 
  	<tr>
	  <td>${m.id}</td>
	  <td>${m.name}</td>
	  <td>${m.createTime}</td>
	  <td>${m.key}</td>
	  <td>
	    <a href="modeler/editor.html?id=${m.id}">设计</a>
   	    <a href="modeler/deploy?action=deploy&modelId=${m.id}">部署</a>
	  	<a href="modeler/editor.html?id=0">add</a>
	    
	  </td>
	</tr>
	  </thead>
	
 <%   
  }
%>
  </tbody>

</table>


  </body>
</html>
