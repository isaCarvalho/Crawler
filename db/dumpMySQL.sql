-- Script para base de daods MySQL -- 

DROP DATABASE IF EXISTS crawler;

CREATE DATABASE crawler;

USE crawler;

CREATE TABLE urls (
id INT PRIMARY KEY AUTO_INCREMENT,
url VARCHAR(500)
);

CREATE TABLE links (
id INT PRIMARY KEY AUTO_INCREMENT,
url VARCHAR(500),
ranking INT NOT NULL,
id_url INT NOT NULL,
CONSTRAINT fk_links_id_url FOREING KEY (id_url) REFERENCES urls(id)
);
