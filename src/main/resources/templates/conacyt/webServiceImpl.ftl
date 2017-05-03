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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

${importModel}
<#if importPrimaryKey??>
${importPrimaryKey}
</#if>
${importService}
/**
 * 
 * @author ${author}
 */

@RestController
@RequestMapping(value = "/${propertyNamePlural}")
public class ${name}RestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(${name}RestController.class);

    @Autowired
    private ${name}Service service;

    /**
     * GET ALL recupera todos los ${name}
     * 
     * @return List<${name}>
     */
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

    /**
     * GET ALL BY PAGE recupera los ${name} por página
     * 
     * @return List<${name}>
     */
    @RequestMapping(value = "/pagable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<${name}>> getAll${name}ByPage(Pageable pagable) {
        Page<${name}> ${propertyName}Page = service.findAllByPage(pagable);
        if (${propertyName}Page.getTotalElements() == 0) {
            // Se podría regresar HttpStatus.NOT_FOUND
            return new ResponseEntity<Page<${name}>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Page<${name}>>(${propertyName}Page, HttpStatus.OK);
        }
    }

    /**
     * GET ONE recupera un registro ${name}
     * 
     * @param id
     * @return ${name}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<${name}> get${name}(@PathVariable("id") ${id} id) {
        ${name} ${propertyName} = service.findById(id);
        if (${propertyName} == null) {
            return new ResponseEntity<${name}>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<${name}>(${propertyName}, HttpStatus.OK);
    }

    /**
     * CREATE
     * 
     * @param ${propertyName}
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create${name}(@RequestBody ${name} ${propertyName}, UriComponentsBuilder ucBuilder) {
        if (service.exists(${propertyName}.get${primaryKey.name?cap_first}())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } else {
            service.save(${propertyName});
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(${propertyName}.get${primaryKey.name?cap_first}()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
    }

    /**
     * UPDATE
     * 
     * @param id
     * @param ${propertyName}
     * @return ${name} updated
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<${name}> update${name}(@PathVariable("id") ${id} id, @RequestBody ${name} ${propertyName}) {
        LOGGER.debug("Actualizando ${name}" + id);
        ${name} current${name} = service.findById(id);
        if (current${name} == null) {
            LOGGER.debug("${name} con id " + id + " no se encuentra");
            return new ResponseEntity<${name}>(HttpStatus.NOT_FOUND);
        }
        service.save(${propertyName});
        return new ResponseEntity<${name}>(${propertyName}, HttpStatus.OK);
    }

    /**
     * DELETE ONE
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<${name}> delete${name}(@PathVariable("id") ${id} id) {
        LOGGER.debug("Buscar y borrar un ${name} con " + id);
        ${name} ${propertyName} = service.findById(id);
        if (${propertyName} == null) {
            LOGGER.debug("No es posible borrar. El ${name} con id" + id + " no se encuentra");
            return new ResponseEntity<${name}>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<${name}>(HttpStatus.NO_CONTENT);
    }

    /**
     * DELETE ALL
     * 
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<${name}> deleteAll${name}() {
        service.deleteAll();
        return new ResponseEntity<${name}>(HttpStatus.NO_CONTENT);
    }
}