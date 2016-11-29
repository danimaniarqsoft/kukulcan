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
package mx.infotec.dads.kukulkan.engine.service.layers.springrest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.service.layers.LayerTask;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;

/**
 * Service Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service("serviceLayerTask")
public class ServiceLayerTask implements LayerTask {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLayerTask.class);

    @Override
    public boolean doTask(GeneratorContext context) {
        LOGGER.debug("Service Layer Task Executing");
        System.out.println("Service Layer Task");
        Map<String, Object> model = new HashMap<>();
        model.put("year", "2016");
        model.put("author", "Daniel Cortes Pichardo");
        model.put("package", "com.danimanicp.kukulkan");
        model.put("imports", "import java.util.Long;");
        model.put("className", "DataConnection");
        model.put("tableName", "DATA_CONNECTION");
        model.put("propertyType", "Long");
        model.put("propertyName", "id");
        model.put("propertyNameMethod", "Id");
        model.put("date", new Date());
        templateService.fillModel("rest-spring-jpa/model.ftl", model);
        return true;
    }

}
