DROP TABLE IF EXISTS agent CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS house CASCADE;
DROP SEQUENCE IF EXISTS agent_id_seq;
DROP SEQUENCE IF EXISTS customer_id_seq;
DROP SEQUENCE IF EXISTS house_id_seq;
-- CREATE SEQUENCE agent_id_seq START WITH 1 minvalue 1 maxvalue 30000 cycle;
-- CREATE SEQUENCE customer_id_seq START WITH 1 minvalue 1 maxvalue 30000 cycle;
-- CREATE SEQUENCE house_id_seq START WITH 1 minvalue 1 maxvalue 30000 cycle;

CREATE TABLE agents (
   	id			bigserial not null,
	name		varchar(50) not null unique,
   	last_name	VARCHAR(30) not null,
	first_name	varchar(30) not null,
	email		varchar(50),
	phone_number	varchar(50),
	password	varchar(30),
	address varchar(150),
	constraint agent_pk primary key (id)
);
CREATE TABLE customers (
   id              bigserial not null,
   name 			varchar(50) not null unique,
   first_name      VARCHAR(30) not null,
   last_name       VARCHAR(30) not null,
   email           VARCHAR(50),
   address         VARCHAR(150),
   salary          numeric(7,2),
   phone_number    varchar(50),
   agent_id   bigint,
	constraint customer_pk primary key (id),
	constraint customer_agent_fk foreign key (agent_id)
	references agents(id)

);

CREATE TABLE houses (
   id          	bigserial not null,
   address		varchar(150) not null unique,
	price		numeric(15,2),
	year		integer,
	last_bought	date,
	last_sold	date,
	tax			numeric(10,2),
	customer_id bigint,
	constraint house_pk primary key (id),
	constraint house_customer_fk foreign key (customer_id)
	references customers(id)
);