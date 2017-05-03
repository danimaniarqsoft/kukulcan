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
 * Defines the types of primary key generation strategies for multiple
 * technologies
 * 
 * @author Daniel Cortes Pichardo
 * 
 */
public enum GenerationType {
    /**
     * Indicates that the persistence provider must assign primary keys for the
     * entity using an underlying database table to ensure uniqueness.
     */
    TABLE,

    /**
     * Indicates that the persistence provider must assign primary keys for the
     * entity using a database sequence.
     */
    SEQUENCE,

    /**
     * Indicates that the persistence provider must assign primary keys for the
     * entity using a database identity column.
     */
    IDENTITY,

    /**
     * Indicates that the persistence provider should pick an appropriate
     * strategy for the particular database. The <code>AUTO</code> generation
     * strategy may expect a database resource to exist, or it may attempt to
     * create one. A vendor may provide documentation on how to create such
     * resources in the event that it does not support schema generation or
     * cannot create the schema resource at runtime.
     */
    AUTO
}
