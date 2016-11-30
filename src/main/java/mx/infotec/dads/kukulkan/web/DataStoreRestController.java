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
import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;

/**
 * 
 * @author Daniel Cortes Pichardo
 */

@RestController
@RequestMapping(value = "/dataStores")
public class DataStoreRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataStoreRestController.class);

    @Autowired
    private DataStoreRepository repository;

    /**
     * GET ALL recupera todos los DataStore
     * 
     * @return List<DataStore>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataStore>> getAllDataStore() {
        List<DataStore> dataStoreList = repository.findAll();
        if (dataStoreList.isEmpty()) {
            // Se podría regresar HttpStatus.NOT_FOUND
            return new ResponseEntity<List<DataStore>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<DataStore>>(dataStoreList, HttpStatus.OK);
        }
    }

    /**
     * GET ONE recupera un registro DataStore
     * 
     * @param id
     * @return DataStore
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataStore> getDataStore(@PathVariable("id") Long id) {
        DataStore dataStore = repository.findOne(id);
        if (dataStore == null) {
            return new ResponseEntity<DataStore>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DataStore>(dataStore, HttpStatus.OK);
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
        if (repository.exists(dataStore.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } else {
            repository.save(dataStore);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(dataStore.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
    public ResponseEntity<DataStore> updateDataStore(@PathVariable("id") long id, @RequestBody DataStore dataStore) {
        LOGGER.debug("Actualizando DataStore" + id);
        DataStore currentDataStore = repository.findOne(id);
        if (currentDataStore == null) {
            LOGGER.debug("DataStore con id " + id + " no se encuentra");
            return new ResponseEntity<DataStore>(HttpStatus.NOT_FOUND);
        }
        repository.save(dataStore);
        return new ResponseEntity<DataStore>(dataStore, HttpStatus.OK);
    }

    /**
     * DELETE ONE
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataStore> deleteDataStore(@PathVariable("id") Long id) {
        LOGGER.debug("Buscar y borrar un DataStore con " + id);
        DataStore dataStore = repository.findOne(id);
        if (dataStore == null) {
            LOGGER.debug("No es posible borrar. El DataStore con id" + id + " no se encuentra");
            return new ResponseEntity<DataStore>(HttpStatus.NOT_FOUND);
        }
        repository.delete(id);
        return new ResponseEntity<DataStore>(HttpStatus.NO_CONTENT);
    }

    /**
     * DELETE ALL
     * 
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<DataStore> deleteAllDataStore() {
        repository.deleteAll();
        return new ResponseEntity<DataStore>(HttpStatus.NO_CONTENT);
    }
}