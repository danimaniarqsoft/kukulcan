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
${packageImpl}

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

${importModel}
<#if importPrimaryKey??>
${importPrimaryKey}
</#if>
${importRepository}
${importService}

/**
 * ${name}ServiceImpl
 * 
 * @author ${author}
 *
 */
@Service
public class ${name}ServiceImpl implements ${name}Service {

    @Autowired
    private ${name}Repository repository;

    @Override
    public Page<${name}> findAllByPage(Pageable pagable) {
        return repository.findAll(pagable);
    }

    @Override
    public List<${name}> findAll() {
        return repository.findAll();
    }

    @Override
    public ${name} findById(${id} id) {
        return repository.findOne(id);
    }

    @Override
    public ${name} save(${name} ${propertyName}) {
        return repository.save(${propertyName});
    }

    @Override
    public boolean exists(${id} id) {
        return repository.exists(id);
    }

    @Override
    public void delete(${id} id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
