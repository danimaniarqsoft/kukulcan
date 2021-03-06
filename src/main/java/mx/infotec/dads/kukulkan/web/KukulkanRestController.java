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
package mx.infotec.dads.kukulkan.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.metamodel.DataContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelContext;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDataModelContext;
import mx.infotec.dads.kukulkan.engine.factories.LayerTaskFactory;
import mx.infotec.dads.kukulkan.engine.service.DataStoreService;
import mx.infotec.dads.kukulkan.engine.service.GenerationService;
import mx.infotec.dads.kukulkan.util.ArchetypeType;
import mx.infotec.dads.kukulkan.util.DataMapping;
import mx.infotec.dads.kukulkan.util.EntitiesFactory;
import mx.infotec.dads.kukulkan.util.KukulkanContext;

/**
 * KukuklanRestController is the main controller in charge of generate the
 * application base on a DataStore.
 * 
 * @author Daniel Cortes Pichardo
 */

@RestController
@RequestMapping(value = "/generateApplication")
public class KukulkanRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KukulkanRestController.class);

    @Autowired
    private GenerationService generationService;
    @Autowired
    private DataStoreService dataStoreService;
    @Autowired
    private LayerTaskFactory layerTaskFactory;

    /**
     * CREATE
     * 
     * @param dataStore
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> generateApplication(@RequestBody KukulkanContext ctx, UriComponentsBuilder ucBuilder) {
        // Create DataStore
        LOGGER.debug("Creating DataStore object");
        DataStore dataStore = dataStoreService.findById(ctx.getDataStore());
        // Create DataModel
        LOGGER.debug("Creating DataModel object");
        DataModelContext dmCtx = new JavaDataModelContext(dataStore);
        // Create DataContext
        LOGGER.debug("Creating DataContext object");
        DataContext dataContext = dataStoreService.getDataContext(dataStore);
        dmCtx.setDataContext(dataContext);
        // Tables to process
        List<String> tablesToProcess = new ArrayList<>();
        // Mapping DataContext into DataModel
        List<DataModelGroup> dmgList = DataMapping.createSingleDataModelGroupList(dmCtx.getDataContext(),
                tablesToProcess);
        dmCtx.setDataModelGroup(dmgList);
        // Create GeneratorContext
        LOGGER.debug("Creating GeneratorContext object");
        GeneratorContext genCtx = new GeneratorContext(dmCtx, ctx.getPc());
        // Process Activities
        LOGGER.debug("Executing generation service");
        generationService.process(genCtx, layerTaskFactory.getLayerTaskSet(ArchetypeType.REST_SPRING_JPA));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand("generated").toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/newContext", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KukulkanContext> getNewContext() {
        return new ResponseEntity<>(EntitiesFactory.createDefaultKukulkanContext(), HttpStatus.OK);
    }
}
