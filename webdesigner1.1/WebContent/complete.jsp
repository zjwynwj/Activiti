<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="com.zt.activiti.util.*"%>


<%

	ProcessEngine processEngine = ActivityUtil.getProcessEngine();

	// Get Activiti services
	TaskService taskService = processEngine
		.getTaskService();

	// complete a task
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("superior", "superior");
	List list =new ArrayList();
	list.add("倪武江");
	list.add("李国军");
	list.add("zwh");
	map.put("users", list);

	taskService.complete(request.getParameter("id"), map);

	response.sendRedirect("index.jsp");
%>


