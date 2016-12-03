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

@Service
public class DataStoreServiceImpl implements DataStoreService {
    @Autowired
    private DataStoreRepository repository;

    @Override
    public DataContext getDataContext(DataStore dataStore) {
        switch (dataStore.getDataStoreType().getId()) {
        case 1: // JDBC
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put("type", dataStore.getDataStoreType().getName());
            properties.put("url", dataStore.getUrl());
            properties.put("driver-class", dataStore.getDriverClass());
            properties.put("username", dataStore.getUsername());
            properties.put("password", dataStore.getPassword());
            DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
            return dataContext;
        case 2: // CSV
            return null;
        default:
            return null;
        }
    }

    @Override
    public DataStore getDataStore(Long id) {
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
    public DataStore findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public DataStore save(DataStore dataStore) {
        return repository.save(dataStore);
    }

    @Override
    public boolean exists(Long id) {
        return repository.exists(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
