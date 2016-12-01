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

import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.formatToPackageStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.schema.Table;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;

/**
 * DataMapping utility class
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DataMapping {

    private DataMapping() {

    }

    /**
     * Create a DataModelGroup Class
     * 
     * @param dataContext
     * @return DataModelGroup
     */
    public static DataModelGroup createDataModelGroup(DataContext dataContext) {
        DataModelGroup dmg = new DataModelGroup();
        dmg.setName("");
        dmg.setDescription("Default package");
        dmg.setBriefDescription("Default package");
        dmg.setDataModelElements(new ArrayList<>());
        Table[] tables = dataContext.getDefaultSchema().getTables();
        List<DataModelElement> dmeList = new ArrayList<>();
        for (Table table : tables) {
            DataModelElement dme = new DataModelElement();
            dme.setTableName(table.getName());
            dme.setName(SchemaPropertiesParser.parseToClassName(table.getName()));
            dme.setPropertyName(SchemaPropertiesParser.parseToPropertyName(table.getName()));
            dmeList.add(dme);
        }
        dmg.setDataModelElements(dmeList);
        return dmg;
    }

    /**
     * Create a List of DataModelGroup into a single group from a DataContext
     * Element
     * 
     * @param dataContext
     * @return
     */
    public static List<DataModelGroup> createSingleDataModelGroupList(DataContext dataContext) {
        List<DataModelGroup> dataModelGroupList = new ArrayList<>();
        dataModelGroupList.add(createDataModelGroup(dataContext));
        return dataModelGroupList;
    }

    public void mapCommonProperties(ProjectConfiguration pConf, Map<String, Object> model, DataModelElement dmElement,
            String basePackage) {
        model.put("package", formatToPackageStatement(basePackage, pConf.getWebLayerName()));
        model.put("propertyName", dmElement.getPropertyName());
        model.put("name", dmElement.getName());
        model.put("id", "Long");
    }
}
