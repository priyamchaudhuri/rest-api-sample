-- MySQL dump 10.13  Distrib 5.5.47, for Win64 (x86)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.5.47

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `ADDRESS_ID` bigint(11) NOT NULL,
  `ADDRESS_LINE_1` varchar(50) DEFAULT NULL,
  `ADDRESS_LINE_2` varchar(50) DEFAULT NULL,
  `ADDRESS_LINE_3` varchar(50) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `STATE` varchar(20) DEFAULT NULL,
  `COUNTRY` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ADDRESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1001,'Rajpath Road','Janaki Bhavan','First Floor','Bangalore','Karnataka','India'),(1002,'Rajpath Road','Janaki Bhavan','Second Floor','Bangalore','Karnataka','India'),(1003,'HAL ROAD','Krishna Icon','Flat 202','Bangalore','Karnataka','India'),(1004,'Lajvanti Road','Krishna Icon','Flat 202','Delhi','Delhi','India');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EMP_USER_ID` bigint(11) NOT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  `EMP_ID` bigint(11) DEFAULT NULL,
  KEY `FK_EMP_USER_ID` (`EMP_USER_ID`),
  CONSTRAINT `FK_EMP_USER_ID` FOREIGN KEY (`EMP_USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2003,'TEACHER',903897),(2004,'LIBRARIAN',903898);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rack`
--

DROP TABLE IF EXISTS `rack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rack` (
  `RACK_ID` bigint(11) NOT NULL,
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
INSERT INTO `rack` VALUES (5001,1,4001),(5002,2,4001),(5003,3,4001),(5004,1,4002),(5005,2,4002),(5006,3,4002);
/*!40000 ALTER TABLE `rack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `RESOURCE_ID` bigint(11) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `AUTHOR` varchar(20) DEFAULT NULL,
  `PUBLICATION` varchar(20) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (3001,'REST','Satish','TAT',2015,'BOOK'),(3002,'Hibernate','Priyam','TAT',2015,'BOOK'),(3003,'Spring MVC','Seema','TAT',2015,'BOOK'),(3004,'Spring Security','Matt','TAT',2015,'BOOK'),(3005,'Spring Security','Matt','TAT',2015,'CD'),(3006,'Spring MVC','Seema','TAT',2015,'CD'),(3007,'Spring JPA','Priyam','TAT',2015,'CD');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
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
  CONSTRAINT `FK_RR_RESOURCE_ID` FOREIGN KEY (`RR_RESOURCE_ID`) REFERENCES `resource` (`RESOURCE_ID`),
  CONSTRAINT `FK_RR_RACK_ID` FOREIGN KEY (`RR_RACK_ID`) REFERENCES `rack` (`RACK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_rack`
--

LOCK TABLES `resource_rack` WRITE;
/*!40000 ALTER TABLE `resource_rack` DISABLE KEYS */;
INSERT INTO `resource_rack` VALUES (3001,5001),(3002,5002);
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
  CONSTRAINT `FK_RU_RESOURCE_ID` FOREIGN KEY (`RU_RESOURCE_ID`) REFERENCES `resource` (`RESOURCE_ID`),
  CONSTRAINT `FK_RU_USER_ID` FOREIGN KEY (`RU_USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_user`
--

LOCK TABLES `resource_user` WRITE;
/*!40000 ALTER TABLE `resource_user` DISABLE KEYS */;
INSERT INTO `resource_user` VALUES (3003,2001),(3004,2002),(3007,2001),(3005,2003),(3006,2003);
/*!40000 ALTER TABLE `resource_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelf`
--

DROP TABLE IF EXISTS `shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shelf` (
  `SHELF_ID` bigint(11) NOT NULL,
  `LOCATION` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SHELF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelf`
--

LOCK TABLES `shelf` WRITE;
/*!40000 ALTER TABLE `shelf` DISABLE KEYS */;
INSERT INTO `shelf` VALUES (4001,'100-100'),(4002,'100-101');
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
  CONSTRAINT `FK_STUDENT_USR_ID` FOREIGN KEY (`STUDENT_USR_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2001,2015001),(2002,2015002);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` bigint(11) NOT NULL,
  `FIRST_NAME` varchar(20) DEFAULT NULL,
  `LAST_NAME` varchar(20) DEFAULT NULL,
  `JOINING_DATE` date DEFAULT NULL,
  `USER_ADDRESS_ID` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `fk_USER_ADDRESS_ID` (`USER_ADDRESS_ID`),
  CONSTRAINT `fk_USER_ADDRESS_ID` FOREIGN KEY (`USER_ADDRESS_ID`) REFERENCES `address` (`ADDRESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2001,'Satish','Kumar','2015-12-25',1001),(2002,'Priyam','Chaudhari','2015-12-09',1002),(2003,'Seema','Sinha','2015-12-11',1003),(2004,'Matthew','Kerr','2015-11-03',1004);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-27 12:44:35
