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
import org.apache.metamodel.schema.Column;
import org.apache.metamodel.schema.Table;

import mx.infotec.dads.kukulkan.engine.domain.core.DataModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DataModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.engine.domain.core.PrimaryKey;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.service.layers.LayerTask;

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
    public static DataModelGroup createDataModelGroup(DataContext dataContext, List<String> tablesToProcess) {
        DataModelGroup dmg = new DataModelGroup();
        dmg.setName("");
        dmg.setDescription("Default package");
        dmg.setBriefDescription("Default package");
        dmg.setDataModelElements(new ArrayList<>());
        Table[] tables = dataContext.getDefaultSchema().getTables();
        List<DataModelElement> dmeList = new ArrayList<>();
        createDataModelElement(tablesToProcess, tables, dmeList);
        dmg.setDataModelElements(dmeList);
        return dmg;
    }

    private static void createDataModelElement(List<String> tablesToProcess, Table[] tables,
            List<DataModelElement> dmeList) {
        for (Table table : tables) {
            if ((tablesToProcess.contains(table.getName()) || tablesToProcess.isEmpty())
                    && hasPrimaryKey(table.getPrimaryKeys())) {
                DataModelElement dme = DataModelElement.createOrderedDataModel();
                String singularName = InflectorProcessor.getInstance().singularize(table.getName());
                dme.setTableName(table.getName());
                dme.setName(SchemaPropertiesParser.parseToClassName(singularName));
                dme.setPropertyName(SchemaPropertiesParser.parseToPropertyName(singularName));
                configPrimaryKey(dme, singularName, table.getPrimaryKeys());
                extractProperties(dme, table);
                dmeList.add(dme);
            }
        }
    }

    public static void configPrimaryKey(DataModelElement dme, String singularName, Column[] columns) {
        dme.setPrimaryKey(extractPrimaryKey(singularName, columns));
        if (!dme.getPrimaryKey().isComposed()) {
            dme.getImports().add(dme.getPrimaryKey().getQualifiedLabel());
        }
    }

    public static void extractProperties(DataModelElement dme, Table table) {
        Column[] columns = table.getColumns();
        for (Column column : columns) {
            if (!column.isPrimaryKey()) {
                String propertyName = SchemaPropertiesParser.parseToPropertyName(column.getName());
                String propertyType = column.getType().getJavaEquivalentClass().getSimpleName();
                dme.getImports().add(column.getType().getJavaEquivalentClass().getCanonicalName());
                String qualifiedLabel = column.getType().getJavaEquivalentClass().toString();
                dme.addProperty(new JavaProperty(propertyName, propertyType, column.getName(), qualifiedLabel, false));
            }
        }
    }

    public static boolean hasPrimaryKey(Column[] columns) {
        return columns.length == 0 ? false : true;
    }

    public static PrimaryKey extractPrimaryKey(String singularName, Column[] columns) {
        PrimaryKey pk = new PrimaryKey();
        // Not found primary key
        if (columns.length == 0) {
            return null;
        }
        // Simple Primary key
        if (columns.length == 1) {
            pk.setType(columns[0].getType().getJavaEquivalentClass().getSimpleName());
            pk.setQualifiedLabel(columns[0].getType().getJavaEquivalentClass().getCanonicalName());
            pk.setName(SchemaPropertiesParser.parseToPropertyName(columns[0].getName()));
            pk.setComposed(false);
        } else {
            // Composed Primary key
            pk.setType(SchemaPropertiesParser.parseToClassName(singularName) + "PK");
            pk.setName(SchemaPropertiesParser.parseToPropertyName(singularName) + "PK");
            pk.setComposed(true);
        }

        return pk;
    }

    /**
     * Create a List of DataModelGroup into a single group from a DataContext
     * Element
     * 
     * @param dataContext
     * @return
     */
    public static List<DataModelGroup> createSingleDataModelGroupList(DataContext dataContext,
            List<String> tablesToProces) {
        List<DataModelGroup> dataModelGroupList = new ArrayList<>();
        dataModelGroupList.add(createDataModelGroup(dataContext, tablesToProces));
        return dataModelGroupList;
    }

    public static List<LayerTask> createLaterTaskList(Map<String, LayerTask> map, ArchetypeType archetype) {
        List<LayerTask> layerTaskList = new ArrayList<>();
        for (Map.Entry<String, LayerTask> layerTaskEntry : map.entrySet()) {
            if (layerTaskEntry.getValue().getArchetypeType().equals(archetype)) {
                layerTaskList.add(layerTaskEntry.getValue());
            }
        }
        return layerTaskList;
    }

    public void mapCommonProperties(ProjectConfiguration pConf, Map<String, Object> model, DataModelElement dmElement,
            String basePackage) {
        model.put("package", formatToPackageStatement(basePackage, pConf.getWebLayerName()));
        model.put("propertyName", dmElement.getPropertyName());
        model.put("name", dmElement.getName());
        model.put("id", "Long");
    }
}
