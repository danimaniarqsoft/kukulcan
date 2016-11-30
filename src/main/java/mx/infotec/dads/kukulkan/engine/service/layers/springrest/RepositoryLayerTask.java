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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.service.layers.LayerTask;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;

/**
 * Repository Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service("repositoryLayerTask")
public class RepositoryLayerTask implements LayerTask {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryLayerTask.class);

    @Override
    public boolean doTask(GeneratorContext context) {
        LOGGER.debug("Service Layer Task Executing");
        Map<String, Object> model = new HashMap<>();
        model.put("year", context.getProjectConfiguration().getYear());
        model.put("autor", context.getProjectConfiguration().getAuthor());
        // model.put("package", context.getProjectConfiguration().getGroupId();
        doForEachDataModelGroup(context.getProjectConfiguration(), context.getDataModelContext().getDataModelGroup(),
                model);
        model.put("importModel", "import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;");
        model.put("propertyName", "dataStore");
        model.put("name", "DataStore");
        model.put("id", "Long");
        templateService.fillModel("rest-spring-jpa/repository.ftl", model);
        return true;
    }

    public void doForEachDataModelGroup(ProjectConfiguration pConf, Collection<DataModelGroup> dmGroup,
            Map<String, Object> model) {

    }

    public void doForEachDataModelElement(ProjectConfiguration pConf, Collection<DataModelElement> dmElement,
            Map<String, Object> model) {

    }

}
