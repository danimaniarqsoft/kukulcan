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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.service.DataStoreService;
import mx.infotec.dads.kukulkan.util.EntitiesFactory;

/**
 * 
 * @author Daniel Cortes Pichardo
 */

@RestController
@RequestMapping(value = "/dataStores")
public class DataStoreRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataStoreRestController.class);

    @Autowired
    private DataStoreService service;

    /**
     * GET ALL recupera todos los DataStore
     * 
     * @return List<DataStore>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataStore>> getAllDataStore() {
        List<DataStore> dataStoreList = service.findAll();
        if (dataStoreList.isEmpty()) {
            // Se podría regresar HttpStatus.NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dataStoreList, HttpStatus.OK);
        }
    }

    /**
     * GET ALL BY PAGE recupera los DataStore por página
     * 
     * @return List<DataStore>
     */
    @RequestMapping(value = "/pagable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<DataStore>> getAllDataStoreByPage(Pageable pagable) {
        Page<DataStore> dataStorePage = service.findAllByPage(pagable);
        if (dataStorePage.getTotalElements() == 0) {
            // Se podría regresar HttpStatus.NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dataStorePage, HttpStatus.OK);
        }
    }

    /**
     * GET ONE recupera un registro DataStore
     * 
     * @param id
     * @return DataStore
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataStore> getDataStore(@PathVariable("id") String id) {
        DataStore dataStore = service.findById(id);
        if (dataStore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dataStore, HttpStatus.OK);
    }

    /**
     * CREATE
     * 
     * @param dataStore
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDataStore(@RequestBody DataStore dataStore, UriComponentsBuilder ucBuilder) {
        if (service.exists(dataStore.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            service.save(dataStore);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(dataStore.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

    /**
     * UPDATE
     * 
     * @param id
     * @param dataStore
     * @return DataStore updated
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DataStore> updateDataStore(@PathVariable("id") String id, @RequestBody DataStore dataStore) {
        LOGGER.debug("Actualizando DataStore" + id);
        DataStore currentDataStore = service.findById(id);
        if (currentDataStore == null) {
            LOGGER.debug("DataStore con id " + id + " no se encuentra");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.save(dataStore);
        return new ResponseEntity<>(dataStore, HttpStatus.OK);
    }

    /**
     * DELETE ONE
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataStore> deleteDataStore(@PathVariable("id") String id) {
        LOGGER.debug("Buscar y borrar un DataStore con " + id);
        DataStore dataStore = service.findById(id);
        if (dataStore == null) {
            LOGGER.debug("No es posible borrar. El DataStore con id" + id + " no se encuentra");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * DELETE ALL
     * 
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<DataStore> deleteAllDataStore() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/newDataStore", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataStore> getNewDataStore() {
        return new ResponseEntity<>(EntitiesFactory.createDataStore(), HttpStatus.OK);
    }
}