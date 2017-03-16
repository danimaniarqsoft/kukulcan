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
package mx.infotec.dads.kukulkan.templating.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.util.BasePathEnum;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private Configuration fmConfiguration;

    @Autowired
    private KukulkanConfigurationProperties prop;

    @Override
    public String fillModel(String proyectoId, String templateName, Object model, BasePathEnum path, String filePath) {
        Template template;
        try {
            template = fmConfiguration.getTemplate(templateName);
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(model, consoleWriter);
            File file = new File(prop.getOutputdir() + proyectoId + "/" + path.getPath() + "/" + filePath);
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                file.createNewFile();
            }
            Writer fileWriter = new FileWriter(file);
            template.process(model, fileWriter);
        } catch (IOException | TemplateException e) {
            throw new ApplicationException("Fill Model Error", e);
        } finally {

        }
        return null;
    }

}
