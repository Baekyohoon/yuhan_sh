-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: yuhan_sh
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `count` int NOT NULL,
  `gid` int DEFAULT NULL,
  `invenid` int NOT NULL AUTO_INCREMENT,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invenid`),
  KEY `FKrndw94qfh82to97w584e3gace` (`gid`),
  CONSTRAINT `FKrndw94qfh82to97w584e3gace` FOREIGN KEY (`gid`) REFERENCES `goods` (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (99,2,5,'260'),(99,2,6,'270'),(99,2,7,'280'),(99,2,8,'290'),(99,3,9,'260'),(99,3,10,'270'),(99,3,11,'280'),(99,3,12,'290'),(99,5,17,'260'),(99,5,18,'270'),(99,5,19,'280'),(99,5,20,'290'),(99,6,21,'260'),(99,6,22,'270'),(99,6,23,'280'),(99,6,24,'290'),(99,7,25,'260'),(99,7,26,'270'),(99,7,27,'280'),(99,7,28,'290'),(99,8,29,'260'),(99,8,30,'270'),(99,8,31,'280'),(99,8,32,'290'),(99,9,33,'M'),(99,9,34,'L'),(99,9,35,'XL'),(99,10,36,'M'),(99,10,37,'L'),(99,10,38,'XL'),(99,11,39,'M'),(99,11,40,'L'),(99,11,41,'XL'),(99,12,42,'M'),(99,12,43,'L'),(99,12,44,'XL'),(99,13,45,'M'),(99,13,46,'L'),(99,13,47,'XL'),(99,14,48,'M'),(99,14,49,'L'),(99,14,50,'XL'),(99,15,51,'M'),(99,15,52,'L'),(99,15,53,'XL'),(99,16,54,'M'),(99,16,55,'L'),(99,16,56,'XL'),(99,17,57,'M'),(99,17,58,'L'),(99,17,59,'XL'),(99,18,60,'M'),(99,18,61,'L'),(99,18,62,'XL'),(99,19,63,'M'),(99,19,64,'L'),(99,19,65,'XL'),(99,20,66,'M'),(99,20,67,'L'),(99,20,68,'XL'),(99,21,69,'화이트/블랙'),(99,22,70,'4호'),(99,23,71,'3호'),(99,23,72,'4호'),(99,23,73,'5호'),(99,24,74,'3호'),(99,24,75,'4호'),(99,24,76,'5호'),(99,25,77,'3호'),(99,25,78,'4호'),(99,25,79,'5호'),(99,26,80,'3호'),(99,26,81,'4호'),(99,26,82,'5호'),(99,27,83,'화이트'),(99,27,84,'블루'),(99,29,87,'6호'),(99,29,88,'7호'),(99,29,89,'8호'),(99,30,90,'6호'),(99,30,91,'7호'),(99,30,92,'8호'),(99,31,93,'6호'),(99,31,94,'7호'),(99,31,95,'8호'),(99,32,96,'6호'),(99,32,97,'7호'),(99,32,98,'8호'),(99,33,99,'6호'),(99,33,100,'7호'),(99,33,101,'8호'),(99,34,102,'M'),(99,34,103,'L'),(99,34,104,'XL'),(99,35,105,'M'),(99,35,106,'L'),(99,35,107,'XL'),(99,36,108,'M'),(99,36,109,'L'),(99,36,110,'XL'),(99,37,111,'M'),(99,37,112,'L'),(99,37,113,'XL'),(99,38,114,'M'),(99,38,115,'L'),(99,38,116,'XL'),(99,39,117,'M'),(99,39,118,'L'),(99,39,119,'XL');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-09 19:03:12
