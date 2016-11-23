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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The JavaFileNameParser Class is used for parsing the filename of a Java class and formatted to the most
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class JavaFileNameParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaFileNameParser.class);

    private JavaFileNameParser(){
        
    }
    /**
     * Covierte un string con formato JavaFile.java a javaFile
     * 
     * @param javaFileName
     * @return String javaFileName con formato camelcase
     */
    public static String parseToPropertyName(String javaFileName) {
        return javaFileName.replaceFirst(javaFileName.charAt(0) + "", (javaFileName.charAt(0) + "").toLowerCase())
                .replaceAll(".java", "");
    }

    /**
     * Convierte javaFileName.java a JavaFileName
     * 
     * @param javaFileName
     * @return String
     */
    public static String parseToPropertyType(String javaFileName) {
        return javaFileName.replaceAll(".java", "");
    }

}
