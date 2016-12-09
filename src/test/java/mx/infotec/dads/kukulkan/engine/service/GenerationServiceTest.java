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
import java.util.List;

import org.apache.metamodel.DataContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelContext;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDataModelContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.factories.LayerTaskFactory;
import mx.infotec.dads.kukulkan.util.ArchetypeType;
import mx.infotec.dads.kukulkan.util.DataMapping;

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
    private GenerationService generationService;
    @Autowired
    private DataStoreService dataStoreService;
    @Autowired
    private LayerTaskFactory layerTaskFactory;

    @Test
    public void generationService() throws Exception {
        // Create ProjectConfiguration
        ProjectConfiguration pConf = new ProjectConfiguration();
        pConf.setId("gen");
        pConf.setGroupId("mx.infotec.dads.arquitectura");
        pConf.setVersion("1.0.0");
        pConf.setPackaging("mx.infotec.dads.arquitectura");
        pConf.setYear("2016");
        pConf.setAuthor("KUKULKAN");
        pConf.setWebLayerName("rest");
        pConf.setServiceLayerName("service");
        pConf.setDaoLayerName("repository");
        pConf.setDomainLayerName("model");
        // Create DataStore
        DataStore dataStore = dataStoreService.getDataStore(1l);
        // Create DataModel
        DataModelContext dmCtx = new JavaDataModelContext(dataStore);
        // Create DataContext
        DataContext dataContext = dataStoreService.getDataContext(dataStore);
        dmCtx.setDataContext(dataContext);
        // Tables to process
        List<String> tablesToProcess = new ArrayList<>();
        // Mapping DataContext into DataModel
        List<DataModelGroup> dmgList = DataMapping.createSingleDataModelGroupList(dmCtx.getDataContext(),
                tablesToProcess);
        dmCtx.setDataModelGroup(dmgList);
        // Create GeneratorContext
        GeneratorContext genCtx = new GeneratorContext(dmCtx, pConf);

        // Process Activities
        generationService.process(genCtx, layerTaskFactory.getLayerTaskSet(ArchetypeType.REST_SPRING_JPA));

    }
}