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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.engine.domain.core.DataConnection;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelContext;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDataModelContext;
import mx.infotec.dads.kukulkan.engine.repository.DataConnectionRepository;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;

/**
 * Test for GeneratorService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class GenerationServiceTest {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private GenerationService generationService;

    @Autowired
    private DataConnectionRepository repository;

    @Test
    public void generationService() throws Exception {

        DataConnection connection = repository.findOne(1l);
        DataModelContext dmCtx = new JavaDataModelContext(connection);
        GeneratorContext genCtx = new GeneratorContext(dmCtx);
        generationService.process(genCtx);
        System.out.println(connection.getUrl());
        System.out.println(connection.getUsername());
        System.out.println(connection.getPassword());
        System.out.println(connection.getConnectionType().ordinal());
        // Map<String, Object> model = new HashMap<>();
        // model.put("javaType", "Integer");
        // model.put("propertyName", "name");
        //
        // templateService.fillModel("model.ftl", model);
    }
}