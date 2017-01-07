-- DATA STORE TYPES
INSERT INTO data_store_type(id, name, description) VALUES (1, 'jdbc','Data Store for JDBC connector');
INSERT INTO data_store_type(id, name, description) VALUES (2, 'csv','Data Store for CSV files');

-- DATA STORE
INSERT INTO data_store(data_store_type_id, url, driver_class, username, password, table_types) VALUES (1, 'jdbc:mysql://localhost/zonacero?autoReconnect=true', 'com.mysql.jdbc.Driver', 'developer','temporal123', 'TABLE,VIEW');

-- RULE TYPE
INSERT INTO rule_type(id, name, description) VALUES (1, 'singular','regla que aplica para palabras convertir palabras de plural a singular');
INSERT INTO rule_type(id, name, description) VALUES (2, 'plural','regla que aplica para palabras convertir palabras de singular a plural');


INSERT INTO rule(rule_type_id, expression, replacement) VALUES (1, 'os$', 'o');
INSERT INTO rule(rule_type_id, expression, replacement) VALUES (1, 'es$', '');