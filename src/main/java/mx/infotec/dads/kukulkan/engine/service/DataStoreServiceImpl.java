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

import java.util.List;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.util.Constants;

/**
 * DataStoreServiceImpl
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class DataStoreServiceImpl implements DataStoreService {

    private static final String DATA_STORE_TYPE = "type";
    private static final String DATA_STORE_URL = "url";
    private static final String DATA_STORE_DRIVER_CLASS = "driver-class";
    private static final String DATA_STORE_USERNAME = "username";
    private static final String DATA_STORE_PASSWORD = "password";
    @Autowired
    private DataStoreRepository repository;

    @Override
    public DataContext getDataContext(DataStore dataStore) {
        if (dataStore.getDataStoreType().getName().equals(Constants.DATA_STORE_TYPE_JDBC)) {
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put(DATA_STORE_TYPE, dataStore.getDataStoreType().getName());
            properties.put(DATA_STORE_URL, dataStore.getUrl());
            properties.put(DATA_STORE_DRIVER_CLASS, dataStore.getDriverClass());
            properties.put(DATA_STORE_USERNAME, dataStore.getUsername());
            properties.put(DATA_STORE_PASSWORD, dataStore.getPassword());
            return DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
        } else if (dataStore.getDataStoreType().getName().equals(Constants.DATA_STORE_TYPE_CSV)) {
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put(DATA_STORE_TYPE, dataStore.getDataStoreType().getName());
            properties.put(DATA_STORE_URL, dataStore.getUrl());
            return DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
        } else {
            return null;
        }
    }

    @Override
    public DataStore getDataStore(String id) {
        return repository.findOne(id);
    }

    @Override
    public Page<DataStore> findAllByPage(Pageable pagable) {
        return repository.findAll(pagable);
    }

    @Override
    public List<DataStore> findAll() {
        return repository.findAll();
    }

    @Override
    public DataStore findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public DataStore save(DataStore dataStore) {
        return repository.save(dataStore);
    }

    @Override
    public boolean exists(String id) {
        return repository.exists(id);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
