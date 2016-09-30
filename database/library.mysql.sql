CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;
-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EMP_USER_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `EMP_CODE` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`EMP_USER_ID`),
  KEY `FK_EMP_USER_ID` (`EMP_USER_ID`),
  CONSTRAINT `FK_EMP_USER_ID` FOREIGN KEY (`EMP_USER_ID`) REFERENCES `usr` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarian`
--

DROP TABLE IF EXISTS `librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `librarian` (
  `LIBRARIAN_USER_ID` bigint(11) NOT NULL,
  `FAV_BOOK` varchar(20) NOT NULL,
  KEY `LIBRARIAN_USER_ID` (`LIBRARIAN_USER_ID`),
  CONSTRAINT `librarian_ibfk_1` FOREIGN KEY (`LIBRARIAN_USER_ID`) REFERENCES `employee` (`EMP_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rack`
--

DROP TABLE IF EXISTS `rack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rack` (
  `RACK_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `NUMBER` int(11) DEFAULT NULL,
  `RACK_SHELF_ID` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`RACK_ID`),
  KEY `FK_RACK_SHELF_ID` (`RACK_SHELF_ID`),
  CONSTRAINT `FK_RACK_SHELF_ID` FOREIGN KEY (`RACK_SHELF_ID`) REFERENCES `shelf` (`SHELF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rack`
--

LOCK TABLES `rack` WRITE;
/*!40000 ALTER TABLE `rack` DISABLE KEYS */;
/*!40000 ALTER TABLE `rack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `library_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library_resource` (
  `RESOURCE_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `RESOURCE_NAME` varchar(20) DEFAULT NULL,
  `AUTHOR` varchar(20) DEFAULT NULL,
  `PUBLICATION` varchar(20) DEFAULT NULL,
  `RESOURCE_YEAR` int(11) DEFAULT NULL,
  `RESOURCE_TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `library_resource` WRITE;
/*!40000 ALTER TABLE `library_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_rack`
--

DROP TABLE IF EXISTS `resource_rack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_rack` (
  `RR_RESOURCE_ID` bigint(11) DEFAULT NULL,
  `RR_RACK_ID` bigint(11) DEFAULT NULL,
  KEY `FK_RR_RESOURCE_ID` (`RR_RESOURCE_ID`),
  KEY `FK_RR_RACK_ID` (`RR_RACK_ID`),
  CONSTRAINT `FK_RR_RACK_ID` FOREIGN KEY (`RR_RACK_ID`) REFERENCES `rack` (`RACK_ID`),
  CONSTRAINT `FK_RR_RESOURCE_ID` FOREIGN KEY (`RR_RESOURCE_ID`) REFERENCES `library_resource` (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_rack`
--

LOCK TABLES `resource_rack` WRITE;
/*!40000 ALTER TABLE `resource_rack` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_rack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_user`
--

DROP TABLE IF EXISTS `resource_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_user` (
  `RU_RESOURCE_ID` bigint(11) NOT NULL,
  `RU_USER_ID` bigint(11) NOT NULL,
  KEY `FK_RU_RESOURCE_ID` (`RU_RESOURCE_ID`),
  KEY `FK_RU_USER_ID` (`RU_USER_ID`),
  CONSTRAINT `FK_RU_RESOURCE_ID` FOREIGN KEY (`RU_RESOURCE_ID`) REFERENCES `library_resource` (`RESOURCE_ID`),
  CONSTRAINT `FK_RU_USER_ID` FOREIGN KEY (`RU_USER_ID`) REFERENCES `usr` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_user`
--

LOCK TABLES `resource_user` WRITE;
/*!40000 ALTER TABLE `resource_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelf`
--

DROP TABLE IF EXISTS `shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shelf` (
  `SHELF_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `LOCATION` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SHELF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelf`
--

LOCK TABLES `shelf` WRITE;
/*!40000 ALTER TABLE `shelf` DISABLE KEYS */;
/*!40000 ALTER TABLE `shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `STUDENT_USR_ID` bigint(11) NOT NULL,
  `ROLL_NO` int(11) DEFAULT NULL,
  KEY `FK_STUDENT_USR_ID` (`STUDENT_USR_ID`),
  CONSTRAINT `FK_STUDENT_USR_ID` FOREIGN KEY (`STUDENT_USR_ID`) REFERENCES `usr` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `TEACHER_USR_ID` bigint(11) NOT NULL,
  `DEPARTMENT` varchar(20) DEFAULT NULL,
  KEY `TEACHER_USR_ID` (`TEACHER_USR_ID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`TEACHER_USR_ID`) REFERENCES `employee` (`EMP_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr`
--

DROP TABLE IF EXISTS `usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usr` (
  `USER_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(20) DEFAULT NULL,
  `LAST_NAME` varchar(20) DEFAULT NULL,
  `JOINING_DATE` date DEFAULT NULL,
  `ADDRESS_LINE_1` varchar(50) DEFAULT NULL,
  `ADDRESS_LINE_2` varchar(50) DEFAULT NULL,
  `ADDRESS_LINE_3` varchar(50) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `STATE` varchar(20) DEFAULT NULL,
  `COUNTRY` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr`
--

LOCK TABLES `usr` WRITE;
/*!40000 ALTER TABLE `usr` DISABLE KEYS */;
/*!40000 ALTER TABLE `usr` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-29 21:47:46
