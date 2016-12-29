CREATE SEQUENCE IF NOT EXISTS data_store_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS plural_id_seq START WITH 1 INCREMENT BY 1;

/*==============================================================*/
/*Table: CATALOGS                                               */
/*==============================================================*/
create table IF NOT EXISTS DATA_STORE_TYPE(
   ID                 INT,
   NAME               VARCHAR(10),
   DESCRIPTION        VARCHAR(50)
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
/*Table: PLURAL DATA                                            */
/*==============================================================*/
create table IF NOT EXISTS INFLECTOR(
   ID                 SERIAL DEFAULT nextval('plural_id_seq'),
   SINGULAR           VARCHAR(100) null,
   PLURAL       	  VARCHAR(100) null,
   constraint PK_INFLECTOR primary key (ID)
);