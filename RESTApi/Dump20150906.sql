-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: res_db
-- ------------------------------------------------------
-- Server version	5.7.8-rc-log

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
-- Current Database: `res_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `res_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `res_db`;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `DATE1` date DEFAULT NULL,
  `START_TIME` time DEFAULT NULL,
  `END_TIME` time DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PHONE` varchar(10) DEFAULT NULL,
  `PARTY_SIZE` smallint(5) NOT NULL,
  `SPECIAL_NEED` varchar(50) DEFAULT NULL,
  `TABLE_NUMBER` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PHONE` (`PHONE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `emp_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `emp_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `emp_db`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS1` varchar(50) DEFAULT NULL,
  `ADDRESS2` varchar(50) DEFAULT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `ZIP` int(11) DEFAULT NULL,
  `PHONE` varchar(10) DEFAULT NULL,
  `STATE` varchar(50) DEFAULT NULL,
  `LOGIN_USR` varchar(45) NOT NULL,
  `LOGIN_PWD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_USR` (`LOGIN_USR`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1001,'PENELOPE','GUINESS','PENELOPE.GUINESS@abc.com','28 MySQL Boulevard','','Palo Alto',94301,'2830338429','California','Penelope123','pen'),(1002,'NICK','WAHLBERG','NICK.WAHLBERG@abc.com','23 Workhaven Lane','','Richmond',23294,'8386352866','Virginia','Nick123','nick'),(1003,'ED','CHASE','ED.CHASE@abc.com','1411 Lillydale Drive','','Seattle',98101,'4484771904','Washington','ed123','ed'),(1004,'JENNIFER','DAVIS','JENNIFER.DAVIS@abc.com','1913 Hanoi Way','','Chicago',60007,'7058140035','Illinois','jen123','jen'),(1005,'JOHNNY','LOLLOBRIGIDA','JOHNNY.LOLLOBRIGIDA@abc.com','1121 Loja Avenue','','Dallas',75001,'1065564866','Texas','john123','john'),(1006,'BETTE','NICHOLSON','BETTE.NICHOLSON@abc.com','692 Joliet Street','','Salt Lake City',84044,'8604526264','Utah','betty123','betty'),(1007,'GRACE','MOSTEL','GRACE.MOSTEL@abc.com','53 Idfu Parkway','','Columbus',43002,'7165712203','Ohio','grace123','grace'),(1008,'MATTHEW','JOHANSSON','MATTHEW.JOHANSSON@abc.com','478 Joliet Way','','Denver',80012,'6572822859','Colorado','math123','math'),(1009,'JOE','SWANK','JOE.SWANK@abc.com','613 Korolev Drive','','Phoenix',85003,'3806575226','Arizona','joe123','joe'),(1010,'CHRISTIAN','GABLE','CHRISTIAN.GABLE@abc.com','1531 Sal Drive','','Miami',33109,'6488569361','Florida','christ','christ'),(1011,'LALITHA','MATHI','CHANDRIKA.ATHI@GMAIL.COM','3800 SW 34TH STREET','string','gAINESVILLE',32608,'3526724284','FLORIDA','MATH1992','MATH');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-06 18:15:55
