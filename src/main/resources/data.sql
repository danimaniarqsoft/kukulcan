-- DATA STORE TYPES
INSERT INTO data_stores_types(id, name, description) VALUES (1, 'jdbc','Data Store for JDBC connector');
INSERT INTO data_stores_types(id, name, description) VALUES (2, 'csv','Data Store for CSV files');

-- RULE TYPE
INSERT INTO rules_types(id, name, description) VALUES (1, 'singular','regla que aplica para palabras convertir palabras de plural a singular');
INSERT INTO rules_types(id, name, description) VALUES (2, 'plural','regla que aplica para palabras convertir palabras de singular a plural');


-- DATA STORE
INSERT INTO data_stores(id, data_stores_types_id, url, driver_class, username, password, table_types)
    VALUES (1, 1, 'jdbc:mysql://localhost/zonacero?autoReconnect=true', 'com.mysql.jdbc.Driver', 'developer','temporal123', 'TABLE,VIEW');


-- RULES - Plural a Singular
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('os$', 'o' ,1);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('yes$', 'y' ,1);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('ces$', 'z' ,1);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('as$', 'a' ,1);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('es$', 'e' ,1);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('os$', 'o' ,1);

-- RULES - Singular a plural
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('a$', 'as' ,2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('e$', 'es' ,2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('o$', 'os' ,2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('l$', 'ones' ,2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('o$', 'os',2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('y$', 'yes',2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('d$', 'des',2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('r$', 'res',2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('s$', 's',2);
INSERT INTO rules(expression, replacement, rules_types_id) VALUES ('x$', 'x',2);
