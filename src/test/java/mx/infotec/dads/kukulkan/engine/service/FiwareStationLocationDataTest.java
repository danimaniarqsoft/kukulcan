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
package mx.infotec.dads.kukulkan.engine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.templating.domain.Station;
import mx.infotec.dads.kukulkan.templating.domain.Station;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;

/**
 * Test for GeneratorService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class FiwareStationLocationDataTest {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private DataStoreRepository dataStoreRepository;

    @Autowired
    private DataStoreService dataStoreService;

    @Test
    public void generationService() throws Exception {

        final DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
        properties.put("type", "csv");
        properties.put("resource", "/home/daniel/git/kukulcan/src/test/resources/estaciones.csv");
        properties.put("encoding", "UTF-8");
        DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
        Schema defaultSchema = dataContext.getDefaultSchema();
        Table table = defaultSchema.getTableByName("estaciones.csv");
        DataSet ds = dataContext.query().from(table).select(table.getColumns()).execute();
        // Process Activities
        List<Station> stations = new ArrayList<>();

        int i = 1;
        while (ds.next()) {
            Row row = ds.getRow();
            Random random = new Random();
            Station station = new Station();
            station.setCount(i++);
            station.setClave((String)row.getValue(0));
            station.setAddress((String)row.getValue(1));
            station.setCo(random.nextInt(79 - 10 + 1) + 10);
            station.setHumidity(random.nextInt(79 - 10 + 1) + 10);
            station.setLatitud(row.getValue(2).toString());
            station.setLongitud(row.getValue(3).toString());
            station.setNo2(random.nextInt(79 - 10 + 1) + 10);
            station.setO3(random.nextInt(79 - 10 + 1) + 10);
            station.setPm10(random.nextInt(79 - 10 + 1) + 10);
            station.setSo2(random.nextInt(79 - 10 + 1) + 10);
            station.setTemperature(random.nextInt(79 - 10 + 1) + 10);
            stations.add(station);
            
        }
        
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("stations", stations);
        templateService.fillModel("orion", "fiware-orion-data/stations.ftl", input, BasePathEnum.SRC_MAIN_JAVA, "stations-others.js");
    }

}