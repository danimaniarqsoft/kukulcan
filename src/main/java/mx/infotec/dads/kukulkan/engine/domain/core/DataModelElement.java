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
import java.util.List;

/**
 * The DataModelElement represent a Table mapped into a specific technology
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DataModelElement {

    /**
     * id of the dataModelElement, it is the primary key
     */
    protected PrimaryKey primaryKey;

    /**
     * urlName statements of the elements
     */
    protected String urlName;

    /**
     * imports statements of the elements
     */
    protected List<String> imports;

    /**
     * Name of the dataModel, usually it is the name of the Schema formatted for
     * specific technology. For instance, in java it is the java Class name
     */
    protected String name;

    /**
     * The name of the database Table
     */
    protected String tableName;

    /**
     * The name of the table formated to java world.
     */
    protected String propertyName;

    /**
     * The columns mapped into the PropertyHolder class
     */
    protected Collection<PropertyHolder> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PropertyHolder> getProperties() {
        return properties;
    }

    public void setProperties(Collection<PropertyHolder> properties) {
        this.properties = properties;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }
}
