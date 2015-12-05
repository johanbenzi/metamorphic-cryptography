/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.24-log : Database - metamorphic_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`metamorphic_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `metamorphic_db`;

/*Table structure for table `inbox_info` */

DROP TABLE IF EXISTS `inbox_info`;

CREATE TABLE `inbox_info` (
  `senderAddress` varchar(25) NOT NULL,
  `toAddress` varchar(25) NOT NULL,
  `cipher_fileName` varchar(150) NOT NULL,
  `cipher_location` varchar(500) NOT NULL,
  `cover_ImageName` varchar(150) NOT NULL,
  `cover_location` varchar(500) NOT NULL,
  `px` int(11) NOT NULL DEFAULT '0',
  `py` int(11) NOT NULL DEFAULT '0',
  `date` varchar(25) NOT NULL,
  `mail_status` varchar(25) NOT NULL,
  KEY `senderAddress` (`senderAddress`),
  CONSTRAINT `inbox_info_ibfk_1` FOREIGN KEY (`senderAddress`) REFERENCES `login_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `inbox_info` */

insert  into `inbox_info`(`senderAddress`,`toAddress`,`cipher_fileName`,`cipher_location`,`cover_ImageName`,`cover_location`,`px`,`py`,`date`,`mail_status`) values ('anoop','Ajith','29-03-2014 10-54-43 CipherImage.png','G:\\Memamorphic Off Line\\MetamorphicServer\\CipherRepo\\Ajith\\29-03-2014 10-54-43 CipherImage.png','logo.png','G:\\Memamorphic Off Line\\MetamorphicServer\\CoverRepo\\Ajith\\29-03-2014 10-54-43 logo.png',3,4,'29-03-2014 10-54-43','MAIL SEND'),('bency','johan','29-03-2014 11-15-12 CipherImage.png','G:\\Memamorphic Off Line\\MetamorphicServer\\CipherRepo\\johan\\29-03-2014 11-15-12 CipherImage.png','logo.png','G:\\Memamorphic Off Line\\MetamorphicServer\\CoverRepo\\johan\\29-03-2014 11-15-12 logo.png',3,4,'29-03-2014 11-15-12','MAIL SEND');

/*Table structure for table `login_info` */

DROP TABLE IF EXISTS `login_info`;

CREATE TABLE `login_info` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `user_type` varchar(25) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login_info` */

insert  into `login_info`(`username`,`password`,`user_type`) values ('admin','admin','admin'),('Ajith','ajith','CLIENT'),('anoop','anoop','CLIENT'),('bency','bency','CLIENT'),('johan','johan','CLIENT');

/*Table structure for table `userdetails` */

DROP TABLE IF EXISTS `userdetails`;

CREATE TABLE `userdetails` (
  `username` varchar(20) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email_id` varchar(35) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  KEY `username` (`username`),
  CONSTRAINT `userdetails_ibfk_1` FOREIGN KEY (`username`) REFERENCES `login_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userdetails_ibfk_2` FOREIGN KEY (`username`) REFERENCES `login_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userdetails` */

insert  into `userdetails`(`username`,`name`,`last_name`,`email_id`,`phone`,`address`) values ('anoop','Anoop','Paul','javavfs4@gmail.com','9744722582','zddfsdfsdf ,sdfsdfsdf'),('Ajith','Ajith','AG','johanbenzi@gmial.com','9447566940','bcfbcfdcfg ,fdgdfgdfgdfg'),('johan','johan','johan','johanbenzi@gmail.com','9447566940','qwerty ,qwerty'),('bency','bency','bency','jo.again3@gmail.com','9447566940','qwerty ,qwerty');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
