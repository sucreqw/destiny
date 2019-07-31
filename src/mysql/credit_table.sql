create database if not exists credit;
use credit;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) NOT NULL,
  `card_num` varchar(36) NOT NULL,
  `phone` varchar(20) NULL,
  `card_date` varchar(10) NOT NULL,
  `card_code` varchar(10) NOT NULL,
  `bill_date` varchar(10) NULL,
  `pay_date` varchar(10) NULL,
  `min_use_count` int(10) NULL,
  `card_detail` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `pay_record`;
CREATE TABLE `pay_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(0) NOT NULL,
  `pay_date` date NOT NULL,
  `amount` decimal(20, 0) NOT NULL,
  `currency` int(10) NOT NULL,
  `detail` varchar(255) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `card_id` FOREIGN KEY (`card_id`) REFERENCES `credit`.`card` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;