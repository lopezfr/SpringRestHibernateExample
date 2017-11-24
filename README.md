# SpringRestHibernateExample
Proyecto de Sistemas Operativos con SpringRest, Mysql

Participantes:
Carlos Cubur
Francisca Chajon
Aless Paredes

Seccion B

#Script de base de datos mysql

create database mercadovirtual;

use mercadovirtual;

CREATE TABLE `mercadovirtual`.`servidordestino` (
  `idPlatformDestiny` INT NOT NULL AUTO_INCREMENT,
  `servername` VARCHAR(100) NOT NULL,
  `serverip` VARCHAR(100) NOT NULL,
  `serverport` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idPlatformDestiny`));

CREATE TABLE `mercadovirtual`.`transaccion` (
  `idTransaccion` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idCoin` INT NOT NULL,
  `idPlatformOrigin` INT NOT NULL,
  `idPlatformDestiny` INT NOT NULL,
  `idProduct` INT NOT NULL,
  `Mount` DECIMAL(13,2) NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`idTransaccion`));

CREATE TABLE `mercadovirtual`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombreUsuario` VARCHAR(1000) NOT NULL,
  `coins` INT NOT NULL,
  PRIMARY KEY (`idusuario`));

CREATE TABLE `mercadovirtual`.`trxoperada` (
  `idTrxOperada` INT NOT NULL AUTO_INCREMENT,
  `idTransaccion` INT NOT NULL,
  `serverName` VARCHAR(1000) NOT NULL,
  `serverIp` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`idTrxOperada`));
