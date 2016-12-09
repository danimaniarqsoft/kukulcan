-- DATA STORE TYPES
INSERT INTO data_store_type(id, name, description) VALUES (1, 'jdbc','Data Store for JDBC connector');
INSERT INTO data_store_type(id, name, description) VALUES (2, 'csv','Data Store for CSV files');

-- DATA STORE
INSERT INTO data_store(id, data_store_type_id, url, driver_class, username, password, table_types)
    VALUES (1, 1, 'jdbc:mysql://localhost/membership?autoReconnect=true', 'com.mysql.jdbc.Driver', 'developer','temporal123', 'TABLE,VIEW');
   