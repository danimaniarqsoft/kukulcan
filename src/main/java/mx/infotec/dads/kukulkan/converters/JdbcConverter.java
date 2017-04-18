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
package mx.infotec.dads.kukulkan.converters;

import static mx.infotec.dads.kukulkan.util.DataBaseTypes.ARRAY;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.BIGINT;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.BINARY;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.BIT;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.BLOB;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.BOOLEAN;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.CHAR;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.CLOB;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.DATE;
import static mx.infotec.dads.kukulkan.util.DataBaseTypes.DECIMAL;

import java.util.HashMap;

import org.apache.metamodel.schema.ColumnType;

/**
 * DataTypeConverter for JDBC
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class JdbcConverter implements DataTypeConverter<ColumnType> {

    private final HashMap<String, String> javaTypesHashMap = new HashMap<>();

    public JdbcConverter() {
        javaTypesHashMap.put(ARRAY, "java.sql.Array");
        javaTypesHashMap.put(BIGINT, "Long");
        javaTypesHashMap.put(BINARY, "byte[]");
        javaTypesHashMap.put(BIT, "Boolean");
        javaTypesHashMap.put(BLOB, "java.sql.Blob");
        javaTypesHashMap.put(BOOLEAN, "Boolean");
        javaTypesHashMap.put(CHAR, "String");
        javaTypesHashMap.put(CLOB, "java.sql.Clob");
        javaTypesHashMap.put(DATE, "java.sql.Date");
        javaTypesHashMap.put(DECIMAL, "Long");
    }

    @Override
    public String resolveJavaType(ColumnType columnType) {
        return javaTypesHashMap.get(columnType.getName());
    }

}
