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

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.domain.core.Rule;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;

/**
 * EntitiesFactory provide common entities with a properly initialization
 * 
 * @author Daniel Cortes Pichardo
 */
public class EntitiesFactory {
    private static final String TABLE_VIEW = "TABLE,VIEW";

    private EntitiesFactory() {

    }

    public static DataStore createDataStore() {
        DataStore ds = new DataStore();
        ds.setDataStoreType(new DataStoreType());
        ds.setDriverClass("");
        ds.setName("");
        ds.setPassword("");
        ds.setTableTypes("");
        ds.setUsername("");
        return ds;
    }

    public static KukulkanContext createDefaultKukulkanContext() {
        ProjectConfiguration pConf = new ProjectConfiguration();
        pConf.setId("conacyt");
        pConf.setGroupId("");
        pConf.setVersion("1.0.0");
        pConf.setPackaging("");
        pConf.setYear("2017");
        pConf.setAuthor("KUKULKAN");
        pConf.setWebLayerName("rest");
        pConf.setServiceLayerName("service");
        pConf.setDaoLayerName("repository");
        pConf.setDomainLayerName("model");
        pConf.setGroupId("mx.infotec.dads");
        pConf.setPackaging("mx.infotec.dads.conacyt");
        return new KukulkanContext(pConf, "");
    }

    public static DataStore createDefaultDataStore(DataStoreType dst) {
        DataStore dsValuApp = new DataStore();
        dsValuApp.setDataStoreType(dst);
        dsValuApp.setDriverClass("com.mysql.jdbc.Driver");
        dsValuApp.setName("valuapp");
        dsValuApp.setPassword("root");
        dsValuApp.setTableTypes(TABLE_VIEW);
        dsValuApp.setUrl("jdbc:mysql://localhost/valuapp?autoReconnect=true");
        dsValuApp.setUsername("root");
        return dsValuApp;
    }
    
    public static DataStore createConacytDataStore(DataStoreType dst) {
        DataStore dsValuApp = new DataStore();
        dsValuApp.setDataStoreType(dst);
        dsValuApp.setDriverClass("oracle.jdbc.driver.OracleDriver");
        dsValuApp.setName("conacyt");
        dsValuApp.setPassword("ApEfiDEV17");
        dsValuApp.setTableTypes(TABLE_VIEW);
        dsValuApp.setUrl("jdbc:oracle:thin:@172.22.13.130:1538/MIICDEV");
        dsValuApp.setUsername("APPL_EFIDT");
        return dsValuApp;
    }

    public static DataStore createTestDataStore(DataStoreType dst) {
        DataStore dsValuApp = new DataStore();
        dsValuApp.setDataStoreType(dst);
        dsValuApp.setDriverClass("org.h2.Driver");
        dsValuApp.setName("h2-db-test");
        dsValuApp.setPassword("");
        dsValuApp.setTableTypes(TABLE_VIEW);
        dsValuApp.setUrl("jdbc:h2:~/test");
        dsValuApp.setUsername("");
        return dsValuApp;
    }

    public static DataStore createMysqlTestDataStore(DataStoreType dst) {
        DataStore dsValuApp = new DataStore();
        dsValuApp.setDataStoreType(dst);
        dsValuApp.setDriverClass("org.h2.Driver");
        dsValuApp.setName("test");
        dsValuApp.setPassword("");
        dsValuApp.setTableTypes(TABLE_VIEW);
        dsValuApp.setUrl("jdbc:h2:~/test");
        dsValuApp.setUsername("");
        return dsValuApp;
    }

    public static DataStoreType createDefaultDataStoreType() {
        DataStoreType dst = new DataStoreType();
        dst.setDescription("Data Store for JDBC connector");
        dst.setName("jdbc");
        return dst;
    }

    public static RuleType createDefaultSingularRuleType() {
        RuleType singularRuleType = new RuleType();
        singularRuleType.setDescription("regla que aplica para palabras convertir palabras de plural a singular");
        singularRuleType.setName("singular");
        return singularRuleType;
    }

    public static RuleType createDefaultPluralRuleType() {
        RuleType plurarlRuleType = new RuleType();
        plurarlRuleType.setDescription("regla que aplica para palabras convertir palabras de singular a plural");
        plurarlRuleType.setName("plural");
        return plurarlRuleType;
    }

    public static Rule createOsRule(RuleType ruleType) {
        Rule osRule = new Rule();
        osRule.setExpression("os$");
        osRule.setReplacement("o");
        osRule.setRuleType(ruleType);
        return osRule;
    }

    public static Rule createEsRule(RuleType ruleType) {
        Rule esRule = new Rule();
        esRule.setExpression("es$");
        esRule.setReplacement("");
        esRule.setRuleType(ruleType);
        return esRule;
    }
}
