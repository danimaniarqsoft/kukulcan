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

${imports}
import java.io.Serializable;

/**
 * The ${className}
 * 
 * @author ${author}
 *
 */
 @Embeddable
public class DispositivoPK implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_alta")
    private java.util.Date fechaAlta;

    public DispositivoPK() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(java.util.Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DispositivoPK)) {
            return false;
        }
        DispositivoPK castOther = (DispositivoPK) other;
        return (this.id == castOther.id) && this.fechaAlta.equals(castOther.fechaAlta);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.id;
        hash = hash * prime + this.fechaAlta.hashCode();

        return hash;
    }
}
@Entity("${tableName}")
public class ${className} implements Serializable {
    /**
     * Este campo fue generado automaticamente por ${author} 
     * Este campo corresponde a la tabla ${tableName}
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    private ${propertyType} ${propertyName};

	 /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la ${tableName}.${propertyName}
     *
     * @return el valor de area_conocimiento.id
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public ${propertyType} get${propertyName?cap_first}() {
        return ${propertyName};
    }

	 /**
     * Este método fue generado automaticamente por ${author} 
     * Este método GETTER fue generado para la tabla. ${tableName}.${propertyName}
     *
     * @return el valor de area_conocimiento.id
     *
     * @kukulkanGenerated ${aDateTime?iso_utc}
     */
    public void set${propertyName?cap_first}(${propertyType} ${propertyName}) {
        this.${propertyName} = ${propertyName};
    }
}