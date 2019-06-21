-- Script para base de dados PostgreSQL -- 

CREATE DATABASE crawler
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE crawler
    IS 'Base de dados para um web crawler';

CREATE TABLE urls (
	id SERIAL PRIMARY KEY,
	url varchar(500) NOT NULL UNIQUE
);

CREATE sequence urls_seq INCREMENT 1 MINVALUE 1 START 1;

ALTER TABLE urls ALTER column id SET DEFAULT nextval('urls_seq');

CREATE TABLE links (
	id SERIAL PRIMARY KEY,
	url VARCHAR(500) NOT NULL,
	ranking int NOT NULL,
	id_url INT REFERENCES urls(id)
);

CREATE SEQUENCE links_seq INCREMENT 1 MINVALUE 1 START 1;

ALTER TABLE links ALTER column id SET DEFAULT nextval('links_seq');
