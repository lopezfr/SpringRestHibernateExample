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


docker run -it -d --network=redfinal --ip=192.168.0.1 --name=tomcatwebservice -v /Users/carloscubur/Downloads:/usr/local/tomcat/webapps tomcat_net:8.0.45-jre8

docker run -it --network=miredso --ip=132.17.10.101 -p 8080:8088 --name=tomcat001 -v /Users/carloscubur/Downloads:/usr/local/tomcat/webapps tomcat_net:8.0.45-jre8
http://132.17.10.101:8080/ServicioWeb/rest/datosPersona

docker run -it -d --network=miredso --ip=132.17.10.101 -p 8088:8080 --name=tomcat001 -v /Users/carloscubur/Comp:/usr/local/tomcat/webapps tomcat_net:8.0.45-jre8

docker run -it -d --name=tomcatwebservice -v /Users/carloscubur/tomcatdocker:/usr/local/tomcat/webapps tomcat_net:8.0.45-jre8


Proyecto

docker run -it -d --network=redfinal --ip=192.168.10.1 -p 8081:8080 --name=tomcatwebservice -v /Users/carloscubur/tomcatdocker:/usr/local/tomcat/webapps tomcat_net:8.0.45-jre8
docker run -it -d --network=redfinal --ip=192.168.10.2 -p 61616:61616 -p 8161:8161 --name=colas cloudesire/activemq

1. bajar mysql
docker pull mysql

2. correr el mysql
docker run --name=basedatosmysql -e MYSQL_ROOT_PASSWORD=secret --network=redfinal --ip=192.168.10.8 -p 3306:3306 -d mysql:latest
docker run --name=basedatosmysql -e MYSQL_ROOT_PASSWORD=secret --network=redfinal --ip=192.168.10.8 -p 3336:3306 -d mysql:latest

3. crear la bd para eso entramos al docker
docker exec -it basedatosmysql mysql -uroot -p

les va a pedir el password y es el que pusimos en el docker run "secret"

luego les saldra que ya entraron a mysql:

Guia Mysql:
1. Show databases;
les mostrara las bases de datos default d mysql

2. create database mercadovirtual;
creamos la bd y yo le llame mercadovirtual pues sera para intercambio de dinero en linea :D

3. Show databases;
Ya nos mostrara creada la nuestra

4. Use mercadovirtual;
Con esto seteamos que usaremos esta BD y todos los siguientes comandos se aplicaran en ella.

5. create table consumos (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, mensaje varchar(1000));
Con esto creamos la tabla donde registraremos los Json mas adelante la podemos modificar.

6. show tables;
Mostrara nuetra tabla y ya podremos hacer Selects, inserts, etc.