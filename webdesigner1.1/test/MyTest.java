import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

import com.zt.activiti.util.ActivityUtil;


public class MyTest {
	@Test
	public void test() throws UnsupportedEncodingException {
		
		 RepositoryService repositoryService=ActivityUtil.getRepositoryService();

		System.out.println(new String(repositoryService.getModelEditorSource("601"), "utf-8"));

	}

}
