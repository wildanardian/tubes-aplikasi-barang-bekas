# ************************************************************
# Sequel Ace SQL dump
# Version 20062
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.4.28-MariaDB)
# Database: barang_bekas
# Generation Time: 2024-01-10 12:20:41 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table barang
# ------------------------------------------------------------

DROP TABLE IF EXISTS `barang`;

CREATE TABLE `barang` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kategori` (`kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;

INSERT INTO `barang` (`id`, `nama`, `kategori`, `harga`, `stok`)
VALUES
	(1,'Laptop Macbook','Elektronik',2500,18),
	(2,'T-shirt','Pakaian',25,100),
	(3,'Snack','Makanan',5,200),
	(4,'Novel','Buku',15,30),
	(5,'Sepatu Lari','Olahraga',100,30),
	(6,'Smartphone','Elektronik',600,15),
	(7,'Jeans','Pakaian',40,80),
	(8,'Coklat','Makanan',3,150),
	(9,'Pelajaran Matematika','Buku',20,10),
	(10,'Bola Basket','Olahraga',30,24),
	(11,'Tablet','Elektronik',300,4),
	(12,'Jaket','Pakaian',50,60),
	(13,'Minuman Energi','Makanan',2,119),
	(14,'Ensiklopedia','Buku',50,14),
	(15,'Raket Tenis','Olahraga',60,20),
	(16,'Barang baru pakaian','Pakaian',700,5);

/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table barang_transaksi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `barang_transaksi`;

CREATE TABLE `barang_transaksi` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_barang` int(11) unsigned NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_barang` (`id_barang`),
  KEY `fk_transaksi` (`id_transaksi`),
  CONSTRAINT `fk_barang` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `barang_transaksi` WRITE;
/*!40000 ALTER TABLE `barang_transaksi` DISABLE KEYS */;

INSERT INTO `barang_transaksi` (`id`, `id_barang`, `id_transaksi`, `nama_barang`)
VALUES
	(1,10,1,'Bola Basket'),
	(2,11,1,'Tablet'),
	(3,13,2,'Minuman Energi'),
	(4,14,2,'Ensiklopedia');

/*!40000 ALTER TABLE `barang_transaksi` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table kategori
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `kategori` WRITE;
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;

INSERT INTO `kategori` (`id`, `nama`)
VALUES
	(1,'Elektronik'),
	(2,'Pakaian'),
	(3,'Makanan'),
	(4,'Buku'),
	(5,'Olahraga'),
	(6,'Mainan Anak');

/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table toko
# ------------------------------------------------------------

DROP TABLE IF EXISTS `toko`;

CREATE TABLE `toko` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `nama_pemilik` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `toko` WRITE;
/*!40000 ALTER TABLE `toko` DISABLE KEYS */;

INSERT INTO `toko` (`id`, `nama`, `nama_pemilik`)
VALUES
	(1,'toko jaya','pak penjual');

/*!40000 ALTER TABLE `toko` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table transaksi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pembeli` varchar(50) DEFAULT NULL,
  `penjual` varchar(50) DEFAULT NULL,
  `tanggal_transaksi` date DEFAULT NULL,
  `metode_pembayaran` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pembeli` (`pembeli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;

INSERT INTO `transaksi` (`id`, `pembeli`, `penjual`, `tanggal_transaksi`, `metode_pembayaran`)
VALUES
	(1,'budi','penjual','2024-01-10','Gopay'),
	(2,'budi','penjual','2024-01-10','Ovo');

/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `nama`, `email`, `no_hp`, `alamat`, `username`, `password`)
VALUES
	(1,'budi','budi@gmail.com','08223344322','sukabirus','budi','budi123'),
	(2,'pak penjual','penjual@gmail.com','088439193912','sukapura','penjual','penjual123');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
