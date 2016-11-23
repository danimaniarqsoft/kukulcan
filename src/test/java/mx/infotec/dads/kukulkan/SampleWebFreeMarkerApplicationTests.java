package mx.infotec.dads.kukulkan;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.service.TemplateService;

/**
 * Basic integration tests for FreeMarker application.
 *
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SampleWebFreeMarkerApplicationTests {

	@Autowired
	private TemplateService templateService;

	@Test
	public void testFreeMarkerTemplate() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("javaType", "Integer");
		model.put("propertyName", "name");

		templateService.fillModel("model.ftl", model);
	}

}