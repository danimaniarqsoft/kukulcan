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

import org.apache.metamodel.DataContext;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

@RestController
@RequestMapping(value = "/dataStores")
public class DataStoreController {
    @Autowired
    private DataStoreRepository repository;

    @Autowired
    private Configuration configuration;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataStore> getDataConnections() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataStore getDataConnection(@PathVariable("id") Long id) throws Exception {
        if (id >= 2)
            throw new ApplicationException();
        return repository.findOne(id);
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getTables(@RequestParam MultiValueMap<String, String> params) {
        DataStore dataConnection = repository.findOne(1L);
        final DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
        properties.put("type", "jdbc");
        properties.put("url", dataConnection.getUrl());
        properties.put("username", dataConnection.getUsername());
        properties.put("password", dataConnection.getPassword());
        DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
        Schema defaultSchema = dataContext.getDefaultSchema();
        String[] tables = defaultSchema.getTableNames();
        return tables;
    }
}
