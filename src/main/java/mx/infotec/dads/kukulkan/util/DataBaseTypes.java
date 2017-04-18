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
package mx.infotec.dads.kukulkan.util;

/**
 * DataBaseTypes, represent all the possible database types that kukulkan can
 * map.
 * 
 * @author Daniel Cortes Pichardo
 */
public class DataBaseTypes {
    /*
     * Literal
     */
    public static final String CHAR = "CHAR";
    public static final String VARCHAR = "VARCHAR";
    public static final String LONGVARCHAR = "LONGVARCHAR";
    public static final String CLOB = "CLOB";
    public static final String NCHAR = "NCHAR";
    public static final String NVARCHAR = "NVARCHAR";
    public static final String LONGNVARCHAR = "LONGNVARCHAR";
    public static final String NCLOB = "NCLOB";

    /*
     * Numbers
     */
    public static final String TINYINT = "TINYINT";
    public static final String SMALLINT = "SMALLINT";
    public static final String INTEGER = "INTEGER";
    public static final String BIGINT = "BIGINT";
    public static final String FLOAT = "FLOAT";
    public static final String REAL = "REAL";
    public static final String DOUBLE = "DOUBLE";
    public static final String NUMERIC = "NUMERIC";
    public static final String DECIMAL = "DECIMAL";
    public static final String UUID = "UUID";

    /*
     * Time based
     */
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String TIMESTAMP = "TIMESTAMP";

    /*
     * Booleans
     */
    public static final String BIT = "BIT";
    public static final String BOOLEAN = "BOOLEAN";

    /*
     * Binary types
     */
    public static final String BINARY = "BINARY";
    public static final String VARBINARY = "VARBINARY";
    public static final String LONGVARBINARY = "LONGVARBINARY";
    public static final String BLOB = "BLOB";

    /*
     * Other types (as defined in {@link Types}).
     */
    public static final String NULL = "NULL";
    public static final String OTHER = "OTHER";
    public static final String JAVA_OBJECT = "JAVA_OBJECT";
    public static final String DISTINCT = "DISTINCT";
    public static final String STRUCT = "STRUCT";
    public static final String ARRAY = "ARRAY";
    public static final String REF = "REF";
    public static final String DATALINK = "DATALINK";
    public static final String ROWID = "ROWID";
    public static final String SQLXML = "SQLXML";
    public static final String INET = "INET";

    /*
     * Additional types (added by MetaModel for non-JDBC datastores)
     */
    public static final String LIST = "LIST";
    public static final String MAP = "MAP";
    public static final String SET = "SET";
    public static final String STRING = "STRING";
    public static final String NUMBER = "NUMBER";

    private DataBaseTypes() {
    }
}
