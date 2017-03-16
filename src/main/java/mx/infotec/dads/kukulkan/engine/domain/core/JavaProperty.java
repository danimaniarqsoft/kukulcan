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

import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * 
 * PropertyHolder Class that is used for hold the properties of a table
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class JavaProperty implements PropertyHolder<JavaProperty> {

    private String propertyName;
    private String propertyType;
    private String qualifiedName;
    private String columnName;
    private boolean primaryKey;

    public JavaProperty(String propertyName, String propertyType, String columnName, String qualifiedName,
            boolean isPrimaryKey) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.qualifiedName = qualifiedName;
        this.primaryKey = isPrimaryKey;
        this.columnName = columnName;
    }

    @Override
    public String getPropertyName() {
        return this.propertyName;
    }

    @Override
    public String getPropertyType() {
        return this.propertyType;
    }

    @Override
    public String getQualifiedName() {
        return this.qualifiedName;
    }

    @Override
    public Collection<PropertyHolder> getAssociations() {
        throw new ApplicationException("Method not implemented");
    }

    @Override
    public boolean isPrimaryKey() {
        return this.primaryKey;
    }

    @Override
    public int compareTo(JavaProperty o) {
        return qualifiedName.compareTo(o.getQualifiedName());
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

}