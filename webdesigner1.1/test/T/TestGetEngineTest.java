package T;

import static org.junit.Assert.*;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.junit.Test;

import com.zt.activiti.util.ActivityUtil;

public class TestGetEngineTest {

	@Test
	public void test() {
		
		
		ProcessEngine processEngine=ActivityUtil.getProcessEngine();
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		
		Model model= repositoryService.getModel("");
		
		System.out.println(model.getId());
	}

}
