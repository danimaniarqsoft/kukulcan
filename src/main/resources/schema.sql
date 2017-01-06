CREATE SEQUENCE IF NOT EXISTS data_stores_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS rules_id_seq START WITH 1 INCREMENT BY 1;

/*==============================================================*/
/*Table: DATA_SOTRE_TYPE                                        */
/*==============================================================*/
create table IF NOT EXISTS DATA_STORES_TYPES(
   ID                 INT,
   NAME               VARCHAR(10),
   DESCRIPTION        VARCHAR(50)
);

/*==============================================================*/
/*Table: RULE_TYPE                                              */
/*==============================================================*/
create table IF NOT EXISTS RULES_TYPES(
   ID                 INT,
   NAME               VARCHAR(10),
   DESCRIPTION        VARCHAR(50)
);

/*==============================================================*/
/*Table: DATA STORE                                             */
/*==============================================================*/
create table IF NOT EXISTS DATA_STORES(
   ID                 SERIAL DEFAULT nextval('data_stores_id_seq'),
   DATA_STORES_TYPES_ID INT,
   URL                VARCHAR(100) null,
   DRIVER_CLASS       VARCHAR(50) null,
   USERNAME           VARCHAR(20) null,
   PASSWORD           VARCHAR(20) null,
   TABLE_TYPES        VARCHAR(20) null,
   constraint PK_DATA_STORES primary key (ID),
   constraint FK_DATA_STORES_TYPES foreign key (DATA_STORES_TYPES_ID)
   references DATA_STORES_TYPES (ID) 
);

/*==============================================================*/
/*Table: RULE                                                   */
/*==============================================================*/
create table IF NOT EXISTS RULES(
   ID                 SERIAL DEFAULT nextval('rules_id_seq'),
   EXPRESSION         VARCHAR(100) null,
   REPLACEMENT        VARCHAR(100) null,
   RULES_TYPES_ID     INT,
   constraint PK_RULES primary key (ID),
   constraint FK_RULES_TYPES foreign key (RULES_TYPES_ID)
   references RULES_TYPES (ID) 
);
