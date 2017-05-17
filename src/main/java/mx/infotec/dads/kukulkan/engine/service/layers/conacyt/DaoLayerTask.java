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
package mx.infotec.dads.kukulkan.engine.service.layers.conacyt;

import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.formatToImportStatement;
import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.formatToPackageStatement;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;
import mx.infotec.dads.kukulkan.util.NameConventions;

/**
 * Dao Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service("conacytDaoLayerTask")
public class DaoLayerTask extends ConacytLayerTaskVisitor {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoLayerTask.class);

    @Override
    public void doForEachDataModelElement(ProjectConfiguration pConf, Collection<DataModelElement> dmElementCollection,
            Map<String, Object> model, String dmgName) {
        LOGGER.debug("doForEachDataModelElement");
        String basePackage = pConf.getPackaging() + dmgName;
        for (DataModelElement dmElement : dmElementCollection) {
            model.put("package", formatToPackageStatement(basePackage, pConf.getConacytDaoLayerName()));
            model.put("packageImpl", formatToPackageStatement(basePackage, pConf.getConacytDaoLayerName(), "impl"));
            model.put("name", dmElement.getName());
            model.put("importDao", formatToImportStatement(basePackage, pConf.getConacytDaoLayerName(),
                    dmElement.getName() + NameConventions.CONACYT_DAO));
            templateService.fillModel(pConf.getId(), "conacyt/hibernate-dao.ftl", model, BasePathEnum.SRC_MAIN_JAVA,
                    basePackage.replace('.', '/') + "/" + dmgName + "/" + pConf.getConacytDaoLayerName() + "/"
                            + dmElement.getName() + NameConventions.CONACYT_DAO + ".java");
            templateService.fillModel(pConf.getId(), "conacyt/hibernate-dao-impl.ftl", model, BasePathEnum.SRC_MAIN_JAVA,
                    basePackage.replace('.', '/') + "/" + dmgName + "/" + pConf.getConacytDaoLayerName() + "/impl/"
                            + dmElement.getName() + NameConventions.CONACYT_DAO_IMPLEMENTS + ".java");
        }
    }

}
