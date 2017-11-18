CREATE DATABASE IF NOT EXISTS sct_gateway COLLATE = 'utf8_general_ci' CHARACTER SET = 'utf8';

CREATE TABLE `service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `is_active` bit(1) DEFAULT NULL COMMENT '是否存在',
  `service_id` varchar(255) DEFAULT NULL COMMENT '目标服务名',
  `service_name` varchar(255) DEFAULT NULL COMMENT '服务名',
  `url` varchar(255) DEFAULT NULL COMMENT '目标服务url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `group0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `group_name` varchar(255) DEFAULT NULL COMMENT '组名',
  `is_active` bit(1) DEFAULT NULL COMMENT '是否存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否启用',
  `group_id` bigint(20) DEFAULT NULL COMMENT '组ID',
  `is_active` bit(1) DEFAULT NULL COMMENT '是否存在',
  `order0` int(11) DEFAULT NULL COMMENT '次序',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `retryable` bit(1) DEFAULT NULL COMMENT '是否需要失败重试',
  `resource_name` varchar(255) DEFAULT NULL COMMENT '资源名',
  `sensitive_headers` varchar(1000) DEFAULT NULL COMMENT '敏感Headers',
  `service_id` bigint(20) DEFAULT NULL COMMENT '服务ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `method` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authentication` bit(1) DEFAULT NULL COMMENT '是否需要身份认证',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否启用',
  `is_active` bit(1) DEFAULT NULL COMMENT '是否存在',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名',
  `authorization` bit(1) DEFAULT NULL COMMENT '是否需要授权',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(255) DEFAULT NULL COMMENT '权限名',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否启用',
  `is_active` bit(1) DEFAULT NULL COMMENT '是否存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

