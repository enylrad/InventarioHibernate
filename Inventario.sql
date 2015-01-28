CREATE DATABASE IF NOT EXISTS inventario

USE inventario

CREATE TABLE IF NOT EXISTS inventario.tipo(
	cod INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS inventario.subtipo(
	cod INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	cod_tipo INT,
	FOREIGN KEY (cod_tipo) REFERENCES inventario.tipo(cod) (
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS inventario.componente(
	cod INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(200),
	precio_p DOUBLE,
	precio_c DOUBLE,
	stock INT,
	descripcion TEXT,
	fecha_compra DATE,
	estado BOOLEAN,
	foto VARCHAR(300),
	cod_subtipo INT,
	FOREIGN KEY (cod_subtipo) REFERENCES inventario.subtipo(cod)
) engine = InnoDB;