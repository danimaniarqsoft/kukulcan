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
import java.util.TreeSet;

import mx.infotec.dads.kukulkan.util.GenerationType;

/**
 * PrimaryKey container for PrimaryKey descriptio.
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

public class PrimaryKey {

    private String name;
    private String type;
    private String qualifiedLabel;
    private Collection<PropertyHolder> properties;
    private boolean isComposed;
    private GenerationType generationType;

    public boolean addProperty(PropertyHolder propertyHolder) {
        return properties.add(propertyHolder);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isComposed() {
        return isComposed;
    }

    public void setComposed(boolean isComposed) {
        this.isComposed = isComposed;
    }

    public String getQualifiedLabel() {
        return qualifiedLabel;
    }

    public void setQualifiedLabel(String qualifiedLabel) {
        this.qualifiedLabel = qualifiedLabel;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    public Collection<PropertyHolder> getProperties() {
        return properties;
    }

    public void setProperties(Collection<PropertyHolder> properties) {
        this.properties = properties;
    }

    public static PrimaryKey createOrderedDataModel() {
        PrimaryKey pk = new PrimaryKey();
        pk.setProperties(new TreeSet<>());
        return pk;
    }
}
