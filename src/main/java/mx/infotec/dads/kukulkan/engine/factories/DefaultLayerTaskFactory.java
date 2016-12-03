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
package mx.infotec.dads.kukulkan.engine.factories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.service.layers.LayerTask;
import mx.infotec.dads.kukulkan.util.ArchetypeType;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * DefaultLayerTaskFactory
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service("defaultLayerTaskFactory")
public class DefaultLayerTaskFactory implements LayerTaskFactory {

    @Autowired
    private ApplicationContext appContext;

    @Override
    public List<LayerTask> getLayerTaskSet(ArchetypeType archetypeType) {
        switch (archetypeType) {
        case REST_SPRING_JPA:
            List<LayerTask> tasks = new ArrayList<>();
            tasks.add((LayerTask) appContext.getBean("repositoryLayerTask"));
            tasks.add((LayerTask) appContext.getBean("restControllerLayerTask"));
            tasks.add((LayerTask) appContext.getBean("serviceLayerTask"));
            return tasks;
        default:
            throw new ApplicationException("Operation Not Supported" + archetypeType.toString());
        }

    }
}
