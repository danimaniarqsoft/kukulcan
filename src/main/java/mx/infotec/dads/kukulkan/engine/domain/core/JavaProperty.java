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
    private String columnType;
    private boolean indexed;
    private boolean nullable;
    private boolean primaryKey;

    private JavaProperty() {

    }

    public static JavaPropertyBuilder builder() {
        return new JavaPropertyBuilder();
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
        return propertyName.compareTo(o.getPropertyName());
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public String getColumnType() {
        return this.columnType;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }

    @Override
    public boolean isIndexed() {
        return this.indexed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaProperty other = (JavaProperty) obj;
        if (propertyName == null) {
            if (other.propertyName != null)
                return false;
        } else if (!propertyName.equals(other.propertyName))
            return false;
        return true;
    }

    protected void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    protected void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    protected void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    protected void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    protected void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    protected void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    protected void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public static class JavaPropertyBuilder {

        private JavaProperty javaProperty = new JavaProperty();

        public JavaPropertyBuilder withPropertyName(String propertyName) {
            this.javaProperty.setPropertyName(propertyName);
            return this;
        }

        public JavaPropertyBuilder withPropertyType(String propertyType) {
            this.javaProperty.setPropertyType(propertyType);
            return this;
        }

        public JavaPropertyBuilder withQualifiedName(String qualifiedName) {
            this.javaProperty.setQualifiedName(qualifiedName);
            return this;
        }

        public JavaPropertyBuilder withColumnName(String columnName) {
            this.javaProperty.setColumnName(columnName);
            return this;
        }

        public JavaPropertyBuilder withColumnType(String columnType) {
            this.javaProperty.setColumnType(columnType);
            return this;
        }

        public JavaPropertyBuilder isNullable(boolean nullable) {
            this.javaProperty.setNullable(nullable);
            return this;
        }

        public JavaPropertyBuilder isPrimaryKey(boolean isPrimaryKey) {
            this.javaProperty.setPrimaryKey(isPrimaryKey);
            return this;
        }

        public JavaPropertyBuilder isIndexed(boolean indexed) {
            this.javaProperty.setIndexed(indexed);
            return this;
        }

        public JavaProperty build() {
            return this.javaProperty;
        }

    }

}
