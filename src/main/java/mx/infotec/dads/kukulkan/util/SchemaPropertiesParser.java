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
 * The SchemaPropertiesParser is used for parse common properties of the de E-R
 * model and convert it to java properties
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class SchemaPropertiesParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaPropertiesParser.class);

    private SchemaPropertiesParser() {

    }

    public static String parseToPropertyName(String columnName) {
        LOGGER.debug("Parsing the column {}", columnName);
        String propertyName = columnName.toLowerCase();
        return trimUnderscore(propertyName);
    }

    public static String parseToClassName(String columnName) {
        LOGGER.debug("Parsing the column {}", columnName);
        return parseToUpperCaseFirstChar(parseToPropertyName(columnName));
    }

    public static String parseToUpperCaseFirstChar(String element) {
        LOGGER.debug("Parsing element {}", element);
        return element.replaceFirst(Character.toString(element.charAt(0)),
                Character.toString(element.charAt(0)).toUpperCase());
    }

    /**
     * The trimUnderscore method trim an expression like *_[A-Za-z] and it
     * converted to *_[A-Z] expression
     * 
     * @param columnName
     * @return
     */
    public static String trimUnderscore(String columnName) {
        LOGGER.debug("Trim the value {}", columnName);
        int underscoreIndex = columnName.indexOf("_");
        if (underscoreIndex > -1 && underscoreIndex < columnName.length() - 1) {
            String leftUnderscore = columnName.substring(0, underscoreIndex);
            String rightUnderscore = columnName.substring(underscoreIndex + 1, columnName.length());
            rightUnderscore = rightUnderscore.replaceFirst(Character.toString(rightUnderscore.charAt(0)),
                    (Character.toString(rightUnderscore.charAt(0))).toUpperCase());
            return trimUnderscore(leftUnderscore + rightUnderscore);
        } else {
            return columnName;
        }
    }
}
