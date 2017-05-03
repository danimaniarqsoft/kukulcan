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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

${importModel}
<#if importPrimaryKey??>
${importPrimaryKey}
</#if>
${importService}
/**
 * 
 * @author ${author}
 */

@ApplicationPath("/")
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
@WebService(name = "ConvocatoriaSEI", targetNamespace = "http://${name}.fosec.conacyt.gob.mx")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface ${name}SEI {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<${name}>> getAll${name}() {
        List<${name}> ${propertyName}List = service.findAll();
        if (${propertyName}List.isEmpty()) {
            // Se podría regresar HttpStatus.NOT_FOUND
            return new ResponseEntity<List<${name}>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<${name}>>(${propertyName}List, HttpStatus.OK);
        }
    }
    
    @POST
    @Path("/${name}SEI")
    @WebMethod(operationName = "cargaComboFondosGeneral")
    @ResponseWrapper(localName = "cargaComboFondosGeneralResponseDTOWrapper", targetNamespace = "http://convocatoria.fosec.conacyt.gob.mx", className = "mx.gob.conacyt.fosec.convocatoria.model.dto")
    @WebResult(name = "cargaComboFondosGeneralResponseDTO")
    ${name} cargaComboFondosGeneral();

}
