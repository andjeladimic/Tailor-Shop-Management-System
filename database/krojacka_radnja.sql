/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - krojacka_radnja
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`krojacka_radnja` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `krojacka_radnja`;

/*Table structure for table `kategorija_klijenta` */

DROP TABLE IF EXISTS `kategorija_klijenta`;

CREATE TABLE `kategorija_klijenta` (
  `idKategorijaKlijenta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(80) NOT NULL,
  PRIMARY KEY (`idKategorijaKlijenta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kategorija_klijenta` */

insert  into `kategorija_klijenta`(`idKategorijaKlijenta`,`naziv`) values 
(1,'Standardni'),
(2,'Penzioner'),
(3,'Student');

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `idKlijent` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `idKategorijaKlijenta` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idKlijent`),
  KEY `fk_klijent_kategorija` (`idKategorijaKlijenta`),
  CONSTRAINT `fk_klijent_kategorija` FOREIGN KEY (`idKategorijaKlijenta`) REFERENCES `kategorija_klijenta` (`idKategorijaKlijenta`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`idKlijent`,`ime`,`prezime`,`email`,`idKategorijaKlijenta`) values 
(1,'Milena','Jovovic','milena@gmail.com',1),
(2,'Filip','Dincic','filip@gmail.com',3),
(3,'Aleksa','Todorovic','aleksa@yahoo.com',1),
(4,'Jovana','Samardzic','jovana@gmail.com',3),
(5,'Gordana','Milutinovic','gordana@gmail.com',2),
(7,'Dragana','Krstic','dragana@gmail.com',2);

/*Table structure for table `krojacica` */

DROP TABLE IF EXISTS `krojacica`;

CREATE TABLE `krojacica` (
  `idKrojacica` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`idKrojacica`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `krojacica` */

insert  into `krojacica`(`idKrojacica`,`ime`,`prezime`,`email`,`username`,`password`) values 
(1,'Anita','Maricic','anita@gmail.com','anita','anita'),
(2,'Jelisaveta','Ostojic','jela@gmail.com','jela','jela'),
(3,'Marija','Simic','mara@gmail.com','mara','mara'),
(4,'Branka','Pekic','branka@gmail.com','branka','branka');

/*Table structure for table `krojacica_ss` */

DROP TABLE IF EXISTS `krojacica_ss`;

CREATE TABLE `krojacica_ss` (
  `idKrojacica` int(10) unsigned NOT NULL,
  `idStrucnaSprema` int(10) unsigned NOT NULL,
  `datumDobijanja` date NOT NULL,
  PRIMARY KEY (`idKrojacica`,`idStrucnaSprema`,`datumDobijanja`),
  KEY `fk_krojacicass_strucnaSprema` (`idStrucnaSprema`),
  CONSTRAINT `fk_krojacicass_krojacica` FOREIGN KEY (`idKrojacica`) REFERENCES `krojacica` (`idKrojacica`),
  CONSTRAINT `fk_krojacicass_strucnaSprema` FOREIGN KEY (`idStrucnaSprema`) REFERENCES `strucna_sprema` (`idStrucnaSprema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `krojacica_ss` */

insert  into `krojacica_ss`(`idKrojacica`,`idStrucnaSprema`,`datumDobijanja`) values 
(1,1,'2025-01-28'),
(1,2,'2024-07-01'),
(2,2,'2018-10-10'),
(2,3,'2023-09-05'),
(3,1,'2020-03-17'),
(4,2,'2021-06-11'),
(4,4,'2025-08-12');

/*Table structure for table `krojacka_usluga` */

DROP TABLE IF EXISTS `krojacka_usluga`;

CREATE TABLE `krojacka_usluga` (
  `idKrojackaUsluga` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `opis` varchar(120) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idKrojackaUsluga`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `krojacka_usluga` */

insert  into `krojacka_usluga`(`idKrojackaUsluga`,`naziv`,`opis`,`cena`) values 
(1,'Skracivanje pantalona','Skracivanje nogavica uz zadrzavanje originalnog poruba ili pravljenje novog poruba.',859.00),
(2,'Suzavanje pantalona u struku','Uzimanje pantalona u predelu struka radi boljeg pristajanja, ukljucuje korekciju pojasa i rajsferslusa.',399.00),
(3,'Skracivanje rukava na kosulji ili sakou','Skracivanje rukava uz ocuvanje manzetne ili dugmadi.',389.00),
(4,'Suzavanje kosulje u struku','Korekcija širine košulje radi boljeg pristajanja.',300.00),
(5,'Prepravka haljine','Korekcija sirine, duzine ili bretela bez menjanja osnovne konstrukcije haljine.',900.00),
(6,'Zamena rajsferslusa','Skidanje starog i ugradnja novog rajsferšlusa na pantalonama, suknji, jakni ili haljini.',420.00),
(7,'Zamena dugmadi','Usivanje novih dugmadi.',200.00),
(8,'Popravka rasparanog sava','Sivenje puknutog ili rasparanog sava, ojačavanje sava ako je potrebno.',280.00),
(9,'Krpljenje odece','Popravka ostecenog dela tkanine (rupe, cepanja).',520.00),
(10,'Sivenje haljine po meri','Potpuna izrada haljine prema modelu i merama klijenta.',5000.00);

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `idRacun` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `datumIzdavanja` datetime NOT NULL,
  `mestoIzdavanja` varchar(20) NOT NULL,
  `ukupanIznos` decimal(12,2) NOT NULL,
  `idKrojacica` int(10) unsigned NOT NULL,
  `idKlijent` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idRacun`),
  KEY `fk_racun_krojacica` (`idKrojacica`),
  KEY `fk_racun_klijent` (`idKlijent`),
  CONSTRAINT `fk_racun_klijent` FOREIGN KEY (`idKlijent`) REFERENCES `klijent` (`idKlijent`),
  CONSTRAINT `fk_racun_krojacica` FOREIGN KEY (`idKrojacica`) REFERENCES `krojacica` (`idKrojacica`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racun` */

insert  into `racun`(`idRacun`,`datumIzdavanja`,`mestoIzdavanja`,`ukupanIznos`,`idKrojacica`,`idKlijent`) values 
(1,'2026-01-18 18:25:05','Palilula',1180.00,1,2),
(2,'2024-03-04 08:59:29','Palilula',789.00,1,3),
(4,'2025-12-12 13:05:48','Zvezdara',2338.00,4,4),
(6,'2026-03-11 18:56:41','novi sad',2026.00,2,3),
(12,'2026-03-15 11:48:44','Bgd',900.00,2,5),
(14,'2026-03-17 10:29:14','Proba',1189.00,1,7),
(15,'2026-03-18 15:51:22','Kaludjerica',6300.00,1,4);

/*Table structure for table `stavka_racuna` */

DROP TABLE IF EXISTS `stavka_racuna`;

CREATE TABLE `stavka_racuna` (
  `idRacun` int(10) unsigned NOT NULL,
  `rb` int(11) NOT NULL,
  `idKrojackaUsluga` int(10) unsigned NOT NULL,
  `kolicina` int(11) NOT NULL,
  `prodajnaCena` decimal(10,2) NOT NULL,
  `iznosStavke` decimal(12,2) NOT NULL,
  PRIMARY KEY (`idRacun`,`rb`),
  KEY `fk_sr_krojackaUsluga` (`idKrojackaUsluga`),
  CONSTRAINT `fk_sr_krojackaUsluga` FOREIGN KEY (`idKrojackaUsluga`) REFERENCES `krojacka_usluga` (`idKrojackaUsluga`),
  CONSTRAINT `fk_sr_racun` FOREIGN KEY (`idRacun`) REFERENCES `racun` (`idRacun`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavka_racuna` */

insert  into `stavka_racuna`(`idRacun`,`rb`,`idKrojackaUsluga`,`kolicina`,`prodajnaCena`,`iznosStavke`) values 
(1,1,8,1,280.00,280.00),
(1,2,5,1,900.00,900.00),
(2,1,3,1,389.00,389.00),
(2,2,7,2,200.00,400.00),
(4,1,1,2,859.00,1718.00),
(4,2,6,1,420.00,420.00),
(4,3,7,1,200.00,200.00),
(6,1,4,3,300.00,900.00),
(6,2,7,1,200.00,400.00),
(12,1,4,3,300.00,900.00),
(14,1,7,4,200.00,800.00),
(14,2,3,1,389.00,389.00),
(15,1,10,1,5000.00,5000.00),
(15,2,5,1,900.00,900.00),
(15,3,7,2,200.00,400.00);

/*Table structure for table `strucna_sprema` */

DROP TABLE IF EXISTS `strucna_sprema`;

CREATE TABLE `strucna_sprema` (
  `idStrucnaSprema` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(120) NOT NULL,
  `trajanjeObrazovanja` int(11) NOT NULL,
  PRIMARY KEY (`idStrucnaSprema`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `strucna_sprema` */

insert  into `strucna_sprema`(`idStrucnaSprema`,`naziv`,`trajanjeObrazovanja`) values 
(1,'Kurs krojenja',6),
(2,'Srednja tekstilna skola',36),
(3,'Viša strukovna skola',24),
(4,'Fakultet dizajna',48);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
