CREATE SEQUENCE IF NOT EXISTS data_store_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS rule_id_seq START WITH 1 INCREMENT BY 1;
/*==============================================================*/
/*Table: DATA_STORES_TYPE                                       */
/*==============================================================*/
create table IF NOT EXISTS DATA_STORE_TYPE(
   ID                 INT,
   NAME               VARCHAR(10),
   DESCRIPTION        VARCHAR(50)
);

/*==============================================================*/
/*Table: RULE_TYPE                                              */
/*==============================================================*/
create table IF NOT EXISTS RULE_TYPE(
   ID                 INT,
   NAME               VARCHAR(10),
   DESCRIPTION        VARCHAR(100)
);

/*==============================================================*/
/*Table: DATA STORE                                             */
/*==============================================================*/
create table IF NOT EXISTS DATA_STORE(
   ID                 SERIAL DEFAULT nextval('data_store_id_seq'),
   DATA_STORE_TYPE_ID INT,
   URL                VARCHAR(100) null,
   DRIVER_CLASS       VARCHAR(50) null,
   USERNAME           VARCHAR(20) null,
   PASSWORD           VARCHAR(20) null,
   TABLE_TYPES        VARCHAR(20) null,
   constraint PK_DATA_STORE primary key (ID),
   constraint FK_DATA_STORE_TYPE foreign key (DATA_STORE_TYPE_ID)
   references DATA_STORE_TYPE (ID) 
);

/*==============================================================*/
/*Table: RULE                                                   */
/*==============================================================*/
create table IF NOT EXISTS RULE(
   ID                 SERIAL DEFAULT nextval('rule_id_seq'),
   RULE_TYPE_ID       INT,
   EXPRESSION         VARCHAR(100) null,
   REPLACEMENT        VARCHAR(100) null,
   constraint PK_RULE primary key (ID)
);