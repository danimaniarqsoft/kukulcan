/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.engine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;

/**
 * Test for GeneratorService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class RepositorySekcTest {

    @Autowired
    private TemplateService templateService;

    @Test
    public void generationService() throws Exception {
        List<String> names = Arrays.asList("SEAction", "SEActivity", "SEActivityAssociation", "SEActivitySpace",
                "SECompletionCriterion", "SEEntryCriterion", "SEAlpha", "SEAlphaAssociation", "SEAlphaContainment",
                "SELevelOfDetail", "SEState", "SEWorkProduct", "SEWorkProductManifest", "SECompetency",
                "SECompetencyLevel", "SECheckpoint", "SEEndeavorProperty", "SEExtensionElement", "SEKernel",
                "SELibrary", "SEMergeResolution", "SEMethod", "SEPattern", "SEPatternAssociation", "SEPractice",
                "SEPracticeAsset", "SEResource", "SETag", "SETypedPattern", "SETypedResource", "SETypedTag",
                "SEUserDefinedType", "SEFeatureSelection", "SEViewSelection");
        List<Map<String, String>> inputs = new ArrayList<>();

        for (String name : names) {
            Map<String, String> input = new HashMap<String, String>();
            input.put("year", "2017");
            input.put("author", "Daniel Cortes Pichardo");
            input.put("package", "mx.infotec.dads.essence.repository");
            input.put("name", name);
            inputs.add(input);
        }

        for (Map<String, String> map : inputs) {
            templateService.fillModel("orion", "repository/repository.ftl", map, BasePathEnum.SRC_MAIN_JAVA,
                    map.get("name") + "Repository.java");
        }
    }

}