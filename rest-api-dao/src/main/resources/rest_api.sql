/*
 Navicat Premium Data Transfer

 Source Server         : loacal
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 127.0.0.1
 Source Database       : rest_api

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : utf-8

 Date: 05/11/2018 14:40:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `rest_client`
-- ----------------------------
DROP TABLE IF EXISTS `rest_client`;
CREATE TABLE `rest_client` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `description` varchar(100) NOT NULL COMMENT '客户端说明',
  `public_key` varchar(2048) NOT NULL COMMENT '客户端公钥',
  `private_key` varchar(2048) NOT NULL COMMENT '客户端私钥',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端';

-- ----------------------------
--  Table structure for `rest_client_right`
-- ----------------------------
DROP TABLE IF EXISTS `rest_client_right`;
CREATE TABLE `rest_client_right` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `client_id` varchar(32) NOT NULL COMMENT '客户端id',
  `right_id` varchar(32) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  KEY `right_id` (`right_id`),
  CONSTRAINT `rest_client_right_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `rest_client` (`id`) ON DELETE CASCADE,
  CONSTRAINT `rest_client_right_ibfk_2` FOREIGN KEY (`right_id`) REFERENCES `rest_right` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端权限';

-- ----------------------------
--  Table structure for `rest_interface`
-- ----------------------------
DROP TABLE IF EXISTS `rest_interface`;
CREATE TABLE `rest_interface` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `interface_uri` varchar(50) NOT NULL COMMENT '接口地址',
  `encrypted` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否加密',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口列表';

-- ----------------------------
--  Table structure for `rest_right`
-- ----------------------------
DROP TABLE IF EXISTS `rest_right`;
CREATE TABLE `rest_right` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `description` varchar(100) NOT NULL COMMENT '权限描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限列表';

-- ----------------------------
--  Table structure for `rest_right_interface`
-- ----------------------------
DROP TABLE IF EXISTS `rest_right_interface`;
CREATE TABLE `rest_right_interface` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `right_id` varchar(32) NOT NULL COMMENT '权限id',
  `interface_id` varchar(32) NOT NULL COMMENT '接口id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `right_id` (`right_id`),
  KEY `interface_id` (`interface_id`),
  CONSTRAINT `rest_right_interface_ibfk_1` FOREIGN KEY (`right_id`) REFERENCES `rest_right` (`id`) ON DELETE CASCADE,
  CONSTRAINT `rest_right_interface_ibfk_2` FOREIGN KEY (`interface_id`) REFERENCES `rest_interface` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限接口关联';

SET FOREIGN_KEY_CHECKS = 1;
