CREATE SEQUENCE IF NOT EXISTS credentials_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS account_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS usuario_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS app_user_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS imagen_id_seq START WITH 1 INCREMENT BY 1;
/*==============================================================*/
/*Table: data_store_type                                        */
/*==============================================================*/
create table data_store_type(
   id                 INT,
   name               VARCHAR(10),
   description        VARCHAR(50)
);
/*==============================================================*/
/*Table: data_store                                             */
/*==============================================================*/
create table data_store(
   id                 SERIAL DEFAULT nextval('credentials_id_seq'),
   data_store_type_id INT,
   url                VARCHAR(100) null,
   driver_class       VARCHAR(50)  null,
   username           VARCHAR(20)  null,
   password           VARCHAR(20)  null,
   table_types        VARCHAR(20)  null,
   constraint pk_data_store primary key (id),
   constraint fk_data_store_type foreign key (data_store_type_id) references data_store_type (id) 
);
/*==============================================================*/
/*Table: account                                                */
/*==============================================================*/
create table account(
   username       VARCHAR(100) null,
   password       VARCHAR(100) null
);	
/*==============================================================*/
/*Table: perfil                                                 */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS perfil (
  id INT NOT NULL COMMENT 'id de la tabla perfil',
  codigo_perfil VARCHAR(40) NOT NULL,
  descripcion_perfil VARCHAR(200) NOT NULL,
  habilitado TINYINT(1) NOT NULL DEFAULT 1,
  constraint pk_perfil primary key (id)
);
/*==============================================================*/
/*Table: usuario                                                */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS usuario (
  id    				NUMBER(20,7) DEFAULT nextval('usuario_id_seq'),
  username 				VARCHAR(50) NOT NULL,
  password 				VARCHAR(128) NOT NULL,
  correo 				VARCHAR(254) NOT NULL,
  fecha_creacion 		TIMESTAMP NOT NULL,
  cuenta_no_expirada 	BIT NOT NULL DEFAULT true,
  cuenta_no_bloqueada 	BIT NOT NULL DEFAULT true,
  credencial_no_expirada BIT NOT NULL DEFAULT true,
  habilitado 			BIT NOT NULL DEFAULT true,
  contador_intentos_fallidos INT(11) NOT NULL DEFAULT true,
  pregunta_secreta 		VARCHAR(200) NOT NULL,
  respuesta_secreta 	VARCHAR(200) NOT NULL,
  id_de_seguridad 		VARCHAR(50) NULL DEFAULT NULL,
  instante_de_bloqueo 	TIME NULL DEFAULT NULL,
  ventana_para_id_seguridad DATE NULL DEFAULT NULL,
  fecha_ultimo_acceso 	TIMESTAMP NULL DEFAULT NULL,
  fecha_ultimo_cambio_clave TIMESTAMP NULL DEFAULT NULL,
  constraint pk_usuario primary key (id)
);
CREATE INDEX IDXCORREO ON usuario(correo);

/*==============================================================*/
/*Table: usuario_detalle                                        */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS usuario_detalle (
  usuario_id 			NUMBER NOT NULL,
  nombres 				VARCHAR(100) NOT NULL,
  primer_apellido  		VARCHAR(80) NOT NULL,
  segundo_apellido 		VARCHAR(80) NULL,
  telefono 				VARCHAR(22) NULL,
  direccion 			VARCHAR(350) NULL,
  constraint fk_usuario foreign key (usuario_id) references usuario (id)
 );
 
 CREATE TABLE IF NOT EXISTS imagen (
  imagen_id             SERIAL NOT NULL, 
  nombre                VARCHAR(150) NOT NULL,
  mime_type             VARCHAR(20) NOT NULL,
  contenido             longblob NOT NULL,
  blob                  blob NOT NULL,
  clob                  clob NOT NULL,
  sha256sum             VARCHAR(64) NOT NULL,
  constraint pk_imagen primary key (imagen_id)
);
/*==============================================================*/
/*Table: usuario_perfil                                         */
/*==============================================================*/
 CREATE TABLE IF NOT EXISTS usuario_perfil (
  usuario_id 			INT NOT NULL,
  perfil_id 			INT NOT NULL,
  constraint pk_usuario_perfil primary key (usuario_id, perfil_id),
  constraint fk_usuario_id foreign key (usuario_id) references usuario (id),
  constraint fk_perfil_id foreign key (perfil_id) references perfil (id)
);
/*==============================================================*/
/*Table: tipo_bitacora                                          */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS tipo_bitacora (
  id                 	INT,
  codigo 				VARCHAR(30) NOT NULL,
  descripcion 			VARCHAR(150) NULL,
  constraint pk_tipo_bitacora primary key (id)
);
  
/*==============================================================*/
/*Table: bitacora                                         		*/
/*==============================================================*/
CREATE TABLE IF NOT EXISTS bitacora (
  id                 	SERIAL DEFAULT nextval('bitacora_id_seq'),
  tipo_bitacora_id  	INT NOT NULL,
  ip 					VARCHAR(45) NOT NULL,
  fecha		 			TIMESTAMP NOT NULL,
  username 				VARCHAR(25) NULL DEFAULT NULL,
  detalle	 			VARCHAR(1500) NULL DEFAULT NULL,
  constraint pk_bitacora primary key (id),
  constraint fk_tipo_bitacora_id foreign key (tipo_bitacora_id) references tipo_bitacora (id)
);
/*==============================================================*/
/*Table: app_user                                         		*/
/*==============================================================*/
CREATE TABLE IF NOT EXISTS app_user (
  id                 	SERIAL DEFAULT nextval('app_user_id_seq'),
  password 				VARCHAR(60) NOT NULL,
  username 				VARCHAR(45) NOT NULL,
  constraint pk_bitacora primary key (id)
);
/*==============================================================*/
/*Table: user_role                                         		*/
/*==============================================================*/
CREATE TABLE IF NOT EXISTS user_role (
  role  				VARCHAR(45) NOT NULL,
  app_user_id 			int,
  constraint fk_app_id foreign key (app_user_id) references app_user (id)
);