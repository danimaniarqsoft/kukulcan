CREATE SEQUENCE IF NOT EXISTS credentials_id_seq START WITH 1 INCREMENT BY 1;

create table data_store(
   id              serial DEFAULT nextval('credentials_id_seq'),
   data_store_type INT,
   url             VARCHAR(100) null,
   driver_class    VARCHAR(50) null,
   username        VARCHAR(20) null,
   password        VARCHAR(20) null,
   table_types     VARCHAR(20) null
);