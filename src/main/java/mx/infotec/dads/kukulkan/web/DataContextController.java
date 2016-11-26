package mx.infotec.dads.kukulkan.web;

import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;

@RestController
@RequestMapping(value = "/dataContext")
public class DataContextController {

    @Autowired
    private DataStoreRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Table getDataContext(@RequestParam MultiValueMap<String, String> params) throws Exception {
        try {
            DataStore dataStore = repository.findOne(1l);
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put("type", dataStore.getDataStoreType().getName());
            System.out.println(dataStore.getDataStoreType().getName());
            properties.put("url", dataStore.getUrl());
            System.out.println(dataStore.getUrl());
            properties.put("driver-class", dataStore.getDriverClass());
            properties.put("username", dataStore.getUsername());
            properties.put("password", dataStore.getPassword());
            return DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties).getDefaultSchema().getTable(0);     
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

       
    }
}