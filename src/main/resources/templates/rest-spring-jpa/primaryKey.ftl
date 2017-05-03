<#assign aDateTime = .now>
/*
 *  
 * The MIT License (MIT)
 * Copyright (c) ${year} ${author}
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
${package}

<#list imports as import>
import ${import};
</#list>

/**
 * The ${className}
 * 
 * @author ${author}
 *
 */
@Embeddable
public class ${primaryKey.type} implements Serializable {
    // Default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;
	<#list primaryKey.properties as property>
	
    /**
     * Este campo fue generado automaticamente por ${author} 
     * Este campo corresponde a la tabla ${tableName}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    @Column(name="${property.columnName}")
    private ${property.propertyType} ${property.propertyName};
	</#list>
	
	<#list primaryKey.properties as property>
    /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la ${tableName}.${property.columnName}
     *
     * @return el valor de ${property.propertyName}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public ${property.propertyType} get${property.propertyName?cap_first}() {
        return ${property.propertyName};
    }

    /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la tabla. ${tableName}.${property.propertyName}
     *
     * @return el valor de ${property.propertyName?cap_first}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public void set${property.propertyName?cap_first}(${property.propertyType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
    </#list>
    
    @Override	
	public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ${primaryKey.type})) {
            return false;
        }
        ${primaryKey.type} castOther = (${primaryKey.type}) other;
        return <#list primaryKey.properties as property><#if property?counter==1>(this.${property.propertyName} == castOther.${property.propertyName})<#else>
                && this.${property.propertyName}.equals(castOther.${property.propertyName})</#if></#list>; 
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        <#list primaryKey.properties as property>
        result = prime * result + ((${property.propertyName} == null) ? 0 : ${property.propertyName}.hashCode());
        </#list>
        return result;
    }
}
