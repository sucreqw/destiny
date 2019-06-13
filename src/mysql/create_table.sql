create database if not exists destiny;
use destiny;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(36) NOT NULL COMMENT '账号',
  `name` varchar(36) NOT NULL COMMENT '用户名称',
  `mobile`  varchar(64)  COMMENT '手机号',
  `openid` varchar (255) COMMENT '第三方登录token',
  `password`  varchar(128) NOT NULL COMMENT '密码',
  `status` int NOT NULL default 1 COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `d_person`;
CREATE TABLE `d_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(36) NOT NULL COMMENT '命造名称',
  `detail` varchar(128) COMMENT '详细八字',
  `create_id` int NOT NULL  COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;