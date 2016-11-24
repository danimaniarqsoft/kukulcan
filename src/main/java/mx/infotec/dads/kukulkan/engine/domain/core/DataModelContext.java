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
package mx.infotec.dads.kukulkan.engine.domain.core;

import java.util.Collection;

import org.apache.metamodel.DataContext;

/**
 * The DataModelContext class is an abstraction of the DataModel of the
 * application and it is agnostic technology model.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public abstract class DataModelContext {

    protected DataContext dataContext;
    protected DataStore dataStore;
    protected Collection<DataModelGroup> dataModelGroup;

    public DataModelContext(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    protected DataModelContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public DataContext getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }


    public DataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Collection<DataModelGroup> getDataModelGroup() {
        return dataModelGroup;
    }

    public void setDataModelGroup(Collection<DataModelGroup> dataModelGroup) {
        this.dataModelGroup = dataModelGroup;
    }

}
