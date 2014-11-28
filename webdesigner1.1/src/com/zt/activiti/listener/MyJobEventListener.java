package com.zt.activiti.listener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;


public class MyJobEventListener implements ActivitiEventListener {

	public boolean isFailOnException() {
		
		System.out.println("MyJobEventListener isFailOnException");
		
		return false;
	}

	public void onEvent(ActivitiEvent event) {

		switch (event.getType()) {

	      case JOB_EXECUTION_SUCCESS:
	        System.out.println("A job well done!");
	        break;

	      case JOB_EXECUTION_FAILURE:
	        System.out.println("A job has failed...");
	        break;

	      default:
	        System.out.println("Event received: " + event.getType());
	    }

		
	}

}
