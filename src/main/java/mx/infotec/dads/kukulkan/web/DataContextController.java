package mx.infotec.dads.kukulkan.web;

import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DataContextController.class);

    @Autowired
    private DataStoreRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Table getDataContext(@RequestParam MultiValueMap<String, String> params) {
        LOGGER.debug("params {}", params);
        try {
            DataStore dataStore = repository.findAll().get(0);
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put("type", dataStore.getDataStoreType().getName());
            properties.put("url", dataStore.getUrl());
            properties.put("driver-class", dataStore.getDriverClass());
            properties.put("username", dataStore.getUsername());
            properties.put("password", dataStore.getPassword());
            return DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties).getDefaultSchema()
                    .getTable(0);
        } catch (Exception e) {
            LOGGER.error("DataContextError", e);
        }
        return null;
    }
}