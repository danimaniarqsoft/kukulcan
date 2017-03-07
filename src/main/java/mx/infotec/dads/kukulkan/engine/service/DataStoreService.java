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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;

/**
 * DataStore Service
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface DataStoreService {
    /**
     * Get a DataContext
     * 
     * @param dataStore
     * @return DataContext
     */
    public DataContext getDataContext(DataStore dataStore);

    /**
     * Get a DataStore
     * 
     * @param id
     *            of the data store
     * @return DataContext
     */
    public DataStore getDataStore(String id);

    /**
     * regresa una lista con todos los elementos DataStore
     * 
     * @return List<DataStore>
     */
    List<DataStore> findAll();

    /**
     * regresa una lista de elementos DataStore por página
     * 
     * @param pagable
     * @return
     */
    Page<DataStore> findAllByPage(Pageable pagable);

    /**
     * Consulta un DataStore por su llave primaria
     * 
     * @param id
     * @return DataStore
     */
    DataStore findById(String id);

    /**
     * Guarda o actualiza un DataStore
     * 
     * @param dataStore
     * @return boolean
     */
    DataStore save(DataStore dataStore);

    /**
     * Regresa true o false si existe un DataStore almacenado
     * 
     * @param id
     * @return boolean
     */
    boolean exists(String id);

    /**
     * Borrar un DataStore por su llave primaria
     * 
     * @param id
     */
    void delete(String id);

    /**
     * Borrar todos los elementos DataStore almacenados
     * 
     * @param id
     */
    void deleteAll();

}
