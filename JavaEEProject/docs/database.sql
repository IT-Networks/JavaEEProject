CREATE DATABASE  IF NOT EXISTS `fis-db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fis-db`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: giesbrechthosting.de    Database: fis-db
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buchung`
--

DROP TABLE IF EXISTS `buchung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buchung` (
  `buchungid` int(11) NOT NULL AUTO_INCREMENT,
  `passagierid` int(11) NOT NULL,
  `flugid` varchar(15) NOT NULL,
  `buchungsdatum` datetime NOT NULL,
  PRIMARY KEY (`buchungid`),
  KEY `fk_buchung_passagier1_idx` (`passagierid`),
  KEY `fk_buchung_flug1_idx` (`flugid`),
  CONSTRAINT `fk_buchung_flug1` FOREIGN KEY (`flugid`) REFERENCES `flug` (`flugid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_buchung_passagier1` FOREIGN KEY (`passagierid`) REFERENCES `passagier` (`passagierid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buchung`
--

LOCK TABLES `buchung` WRITE;
/*!40000 ALTER TABLE `buchung` DISABLE KEYS */;
INSERT INTO `buchung` VALUES (1,1,'MH1/4','2018-04-02 00:47:35'),(2,1,'MH1/4','2018-04-02 12:37:34'),(3,1,'MH1/4','2018-04-02 12:37:44'),(4,1,'MH1/4','2018-04-02 12:37:53'),(5,1,'MH1/4','2018-04-02 12:37:58'),(6,1,'MH1/4','2018-04-02 12:38:03'),(7,1,'MH1/4','2018-04-02 12:38:07'),(8,1,'MH1/4','2018-04-02 12:38:16'),(9,1,'MH1/4','2018-04-02 12:38:22'),(10,1,'MH1/4','2018-04-02 12:38:26'),(11,1,'MH1/4','2018-04-02 12:38:30'),(12,3,'MH10/1','2018-04-02 19:31:25'),(13,3,'MH10/1','2018-04-02 19:31:28'),(15,1,'MH1/5','2018-04-02 20:20:28');
/*!40000 ALTER TABLE `buchung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flug`
--

DROP TABLE IF EXISTS `flug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flug` (
  `flugid` varchar(15) NOT NULL,
  `relationid` int(10) unsigned NOT NULL,
  `flugzeugid` int(11) DEFAULT NULL,
  `mahlzeitid` int(11) DEFAULT NULL,
  `abflug` datetime NOT NULL,
  `ankunft` datetime NOT NULL,
  `preis` decimal(5,2) NOT NULL,
  PRIMARY KEY (`flugid`),
  KEY `fk_flug_relation1_idx` (`relationid`),
  KEY `fk_flug_flugzeug1_idx` (`flugzeugid`),
  KEY `fk_flug_mahlzeit1_idx` (`mahlzeitid`),
  CONSTRAINT `fk_flug_mahlzeit1` FOREIGN KEY (`mahlzeitid`) REFERENCES `mahlzeit` (`mahlzeitid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_flug_relation1` FOREIGN KEY (`relationid`) REFERENCES `relation` (`relationid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `flug_ibfk_1` FOREIGN KEY (`flugzeugid`) REFERENCES `flugzeug` (`flugzeugid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flug`
--

LOCK TABLES `flug` WRITE;
/*!40000 ALTER TABLE `flug` DISABLE KEYS */;
INSERT INTO `flug` VALUES ('MH1/1',1,2,1,'2018-04-01 01:26:34','2018-04-01 01:26:34',25.00),('MH1/10',1,1,1,'2018-01-01 01:26:00','2018-01-01 11:56:00',25.00),('MH1/2',1,1,2,'2018-04-01 01:37:58','2018-04-01 01:37:58',25.00),('MH1/3',1,1,1,'2018-04-01 23:14:13','2018-04-01 23:14:13',25.00),('MH1/4',1,5,1,'2018-04-01 23:14:36','2018-04-01 23:14:36',25.00),('MH1/5',1,1,1,'2018-01-01 01:26:00','2018-01-01 11:56:00',25.00),('MH1/6',1,2,2,'2018-01-01 01:26:00','2018-01-01 11:56:00',25.00),('MH1/7',1,1,6,'2018-01-01 01:26:00','2018-01-01 11:56:00',25.00),('MH1/8',1,1,1,'2018-01-01 01:26:00','2018-01-01 11:56:00',25.00),('MH1/9',1,1,1,'2018-01-01 01:26:00','2018-01-01 11:56:00',25.00),('MH10/1',10,1,1,'2018-07-12 16:02:26','2018-07-12 18:05:26',56.00),('MH5/1',5,9,1,'2018-01-01 15:00:00','2018-01-02 08:07:00',25.00),('MH5/2',5,1,1,'2018-01-05 15:00:00','2018-01-06 08:07:00',25.00),('MH5/3',5,1,1,'2018-04-05 15:00:00','2018-04-06 08:07:00',25.00),('MH5/4',5,1,1,'2018-04-17 17:46:00','2018-04-18 10:53:00',25.00),('MH7/1',7,1,1,'2018-04-19 17:59:57','2018-04-19 18:59:57',523.00),('MH8/1',8,1,1,'2018-04-16 11:00:00','2018-04-17 01:34:00',340.00);
/*!40000 ALTER TABLE `flug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flughafen`
--

DROP TABLE IF EXISTS `flughafen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flughafen` (
  `flughafenid` varchar(3) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ort` varchar(45) DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `land` varchar(45) DEFAULT NULL,
  `zeitzone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`flughafenid`),
  UNIQUE KEY `flughafenid_UNIQUE` (`flughafenid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flughafen`
--

LOCK TABLES `flughafen` WRITE;
/*!40000 ALTER TABLE `flughafen` DISABLE KEYS */;
INSERT INTO `flughafen` VALUES ('ATL','Hartsfield–Jackson Atlanta International Airport','Atlanta','Georgia','USA','EDT'),('BOM','Chhatrapati Shivaji International Airport','Bombay','Maharashtra','Indien','IST'),('CDG','Aéroport Roissy-Charles-de-Gaulle','Paris','Île-de-France','Frankreich','MEZ'),('DXB','Internationaler Flughafen Dubai','Dubai','Dubai (Emirat)','Vereinigte Arabische Emirate','GST'),('FRA','Flughafen Frankfurt am Main','Frankfurt am Main','Hessen','Deutschland','MEZ'),('IST','Flughafen Atatürk','Istanbul','Istanbul','Türkei','TRT'),('JFK','Internationaler Flughafen „John F. Kennedy“','New York City','New York','USA','EDT'),('LAX','Los Angeles International','Los Angeles','Kalifornien','USA','PDT'),('LHR','London-Heathrow','London','England','Großbritannien','GMT'),('MAD','Madrid-Barajas','Madrid','Madrid','Spanien','MEZ'),('NRT','Flughafen Tokio-Narita','Tokio','Tokio','Japan','JST'),('PAD','Flughafen Paderborn/Lippstadt','Paderborn/Lippstadt','Nordrhein-Westfalen','Deutschland','MEZ'),('PEK','Flughafen Peking','Peking','Peking','China','CST');
/*!40000 ALTER TABLE `flughafen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flugzeug`
--

DROP TABLE IF EXISTS `flugzeug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flugzeug` (
  `flugzeugid` int(11) NOT NULL AUTO_INCREMENT,
  `hersteller` varchar(45) DEFAULT NULL,
  `typ` varchar(45) DEFAULT NULL,
  `sitzplaetze` int(11) DEFAULT NULL,
  PRIMARY KEY (`flugzeugid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flugzeug`
--

LOCK TABLES `flugzeug` WRITE;
/*!40000 ALTER TABLE `flugzeug` DISABLE KEYS */;
INSERT INTO `flugzeug` VALUES (1,'leer','leer',0),(2,'Airbus','A380-800',853),(3,'Airbus','A380-800',853),(4,'Airbus','A380-800',853),(5,'Airbus','A380-800',853),(6,'Airbus','A380-800',853),(7,'Airbus','A380-800',853),(8,'Boeing','777-222er',350),(9,'Boeing','777-222er',350),(10,'Boeing','777-333er',50),(11,'Boeing','666-222er',200),(12,'Boeing','777-333er',35);
/*!40000 ALTER TABLE `flugzeug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mahlzeit`
--

DROP TABLE IF EXISTS `mahlzeit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mahlzeit` (
  `mahlzeitid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `art` varchar(45) DEFAULT NULL,
  `vegetarisch` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`mahlzeitid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mahlzeit`
--

LOCK TABLES `mahlzeit` WRITE;
/*!40000 ALTER TABLE `mahlzeit` DISABLE KEYS */;
INSERT INTO `mahlzeit` VALUES (1,'leer','leer',0),(2,'Pizza Margarita','Teigwaren',1),(3,'Schnitzel mit Pommes','Deftiges',0),(4,'Lasagne','Hausgemacht',0),(5,'Lasagne Spezial','Hausgemacht',1),(6,'Burger','Deftiges',0);
/*!40000 ALTER TABLE `mahlzeit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nutzer`
--

DROP TABLE IF EXISTS `nutzer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nutzer` (
  `nutzerrid` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(45) DEFAULT NULL,
  `nachname` varchar(45) DEFAULT NULL,
  `anmeldename` varchar(45) DEFAULT NULL,
  `passwort` varchar(45) DEFAULT NULL,
  `nutzertyp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nutzerrid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutzer`
--

LOCK TABLES `nutzer` WRITE;
/*!40000 ALTER TABLE `nutzer` DISABLE KEYS */;
INSERT INTO `nutzer` VALUES (1,'Halil','Özdogan','Halil','oH4pP9cGFQ00UF1HnymakA==','Manager'),(2,'Dagobert','Duck','dagobert','oH4pP9cGFQ00UF1HnymakA==','Mitarbeiter'),(3,'Halil','Özdogan','Halil2','oH4pP9cGFQ00UF1HnymakA==','Manager'),(4,'Markus','Giesbrecht','Markus','+KgakCr4wnJOyoX7FSi/RA==','Manager');
/*!40000 ALTER TABLE `nutzer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passagier`
--

DROP TABLE IF EXISTS `passagier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passagier` (
  `passagierid` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(45) NOT NULL,
  `nachname` varchar(45) NOT NULL,
  `anschrift` varchar(45) NOT NULL,
  `geburtsdatum` varchar(45) NOT NULL,
  `nationalitaet` varchar(45) NOT NULL,
  PRIMARY KEY (`passagierid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passagier`
--

LOCK TABLES `passagier` WRITE;
/*!40000 ALTER TABLE `passagier` DISABLE KEYS */;
INSERT INTO `passagier` VALUES (1,'Halil','Özdogan','Am Stockhof 2, 31785 Hameln','08.09.1995','deutsch'),(3,'Markus','Giesbrecht','Pirolweg 1, 48231 Warendorf','Mon Dec 02 00:00:00 CET 1996','deutsch'),(4,'Herbert','Grönemeier','Am Stockhof 2, 31785 Hameln','02.12.1996','deutsch');
/*!40000 ALTER TABLE `passagier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation` (
  `relationid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `startort` varchar(3) NOT NULL,
  `zielort` varchar(3) NOT NULL,
  `flugzeit` time(5) DEFAULT NULL,
  `distanz` int(11) DEFAULT NULL,
  PRIMARY KEY (`relationid`),
  KEY `fk_relation_flughafen_idx` (`startort`),
  KEY `fk_relation_flughafen1_idx` (`zielort`),
  CONSTRAINT `fk_relation_flughafen` FOREIGN KEY (`startort`) REFERENCES `flughafen` (`flughafenid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_relation_flughafen1` FOREIGN KEY (`zielort`) REFERENCES `flughafen` (`flughafenid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation`
--

LOCK TABLES `relation` WRITE;
/*!40000 ALTER TABLE `relation` DISABLE KEYS */;
INSERT INTO `relation` VALUES (1,'FRA','BOM','10:30:00.00000',1500),(2,'FRA','PAD','10:30:00.00000',1500),(3,'MAD','BOM','10:30:00.00000',1500),(4,'ATL','ATL','02:53:05.00000',457),(5,'CDG','IST','17:07:15.00000',414),(6,'BOM','MAD','00:18:46.00000',623),(7,'NRT','FRA','01:00:00.00000',34),(8,'DXB','LAX','14:34:21.00000',3245),(9,'DXB','LHR','03:04:00.00000',2),(10,'JFK','LAX','02:03:00.00000',523);
/*!40000 ALTER TABLE `relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 21:37:44
