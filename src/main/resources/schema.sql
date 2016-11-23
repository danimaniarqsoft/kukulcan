CREATE SEQUENCE IF NOT EXISTS credentials_id_seq START WITH 1 INCREMENT BY 1;

create table IF NOT EXISTS data_connection(
   id       serial DEFAULT nextval('credentials_id_seq'),
   url      VARCHAR(100)                   null,
   username VARCHAR(100)                   null,
   password VARCHAR(100)                   null
);