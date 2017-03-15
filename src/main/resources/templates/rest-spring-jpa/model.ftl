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
@Entity
@Table(name="${tableName}")
public class ${className} implements Serializable {
	private static final long serialVersionUID = 1L;

	<#list properties as property>
    /**
     * Este campo fue generado automaticamente por ${author} 
     * Este campo corresponde a la tabla ${tableName}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    @Column(name="${property.columnName}")
    private ${property.propertyType} ${property.propertyName};
	</#list>

	<#list properties as property>
	 /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la ${tableName}.${property.propertyName}
     *
     * @return el valor de area_conocimiento.id
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
     * @return el valor de area_conocimiento.id
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public void set${property.propertyName?cap_first}(${property.propertyType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
    </#list>

}