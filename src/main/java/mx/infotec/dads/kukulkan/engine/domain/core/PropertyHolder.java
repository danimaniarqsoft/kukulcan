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

/**
 * 
 * PropertyHolder Class that is used for hold the properties of a table
 * 
 * @author Daniel Cortes Pichardo
 * @param <T>
 *
 */
public interface PropertyHolder<T> extends Comparable<T> {

    /**
     * Return the name of the property
     * 
     * @return propertyName
     */
    String getPropertyName();

    /**
     * Return the type of the property
     * 
     * @return propertyType
     */
    String getPropertyType();

    /**
     * Return the name of the property
     * 
     * @return propertyName
     */
    String getColumnName();

    /**
     * Return the columnType of the table
     * 
     * @return columnType
     */
    String getColumnType();

    /**
     * Return the qualified name of the type of the Property, in order to create
     * a import statement in the generation fase;
     * 
     * @return qualifiedName
     */
    String getQualifiedName();

    /**
     * Return if it is a primary key property
     * 
     * @return boolean
     */
    boolean isPrimaryKey();

    /**
     * Return true if it is nullable
     * 
     * @return boolean
     */
    boolean isNullable();

    /**
     * Return true if it is index
     * 
     * @return boolean
     */
    boolean isIndexed();

    /**
     * Return associations if it exists
     * 
     * @return associations
     */
    Collection<PropertyHolder> getAssociations();

}
