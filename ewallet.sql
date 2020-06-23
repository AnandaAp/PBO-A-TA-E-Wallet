-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: e-wallet.mysql.database.azure.com    Database: ewallet
-- ------------------------------------------------------
-- Server version	5.6.42.0

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
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  `saldo` double(15,0) NOT NULL,
  `waktu_transaksi` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_email` (`email`),
  CONSTRAINT `fk_email` FOREIGN KEY (`email`) REFERENCES `user_akun` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,'inul@inul.com','Pemasukan sebesar $1',14865,'2020-06-07'),(2,'inul@inul.com','Pengeluaran sebesar Rp.1000',13865,'2020-06-07'),(3,'akira@yahoo.com','Pemasukan sebesar Rp.150000',150000,'2020-06-10'),(4,'akira@yahoo.com','Pemasukan sebesar $500',7582500,'2020-06-10');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_akun`
--

DROP TABLE IF EXISTS `user_akun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_akun` (
  `email` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `password` varchar(16) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `tglLahir` varchar(17) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_akun`
--

LOCK TABLES `user_akun` WRITE;
/*!40000 ALTER TABLE `user_akun` DISABLE KEYS */;
INSERT INTO `user_akun` VALUES ('admin','admin','admin','mysql','12 april 2020'),('admin@admin.com','admin','Admin1234!','mysql','12 april 2020'),('akira@yahoo.com','akira','Korazer150!','di janti','15 januari 2000'),('inul@inul.com','inul','Inul1234!','jakarta','8 agustus 2020');
/*!40000 ALTER TABLE `user_akun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_saldo`
--

DROP TABLE IF EXISTS `user_saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_saldo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `saldo` double(15,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`email`),
  CONSTRAINT `fk_user` FOREIGN KEY (`email`) REFERENCES `user_akun` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_saldo`
--

LOCK TABLES `user_saldo` WRITE;
/*!40000 ALTER TABLE `user_saldo` DISABLE KEYS */;
INSERT INTO `user_saldo` VALUES (1,'admin',0),(2,'admin@admin.com',0),(3,'inul@inul.com',13865),(4,'akira@yahoo.com',7582500);
/*!40000 ALTER TABLE `user_saldo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 13:04:03
