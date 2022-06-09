# Host: 119.91.192.180  (Version: 5.7.36)
# Date: 2022-06-09 14:49:18
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin_msg_template"
#

DROP TABLE IF EXISTS `admin_msg_template`;
CREATE TABLE `admin_msg_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '模板名称',
  `msg_type_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型：10-通知类，20-营销类，30-验证码类',
  `cron` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送时间cron表达式：0-立即发送,cron表达式-定时发送或周期发送',
  `crowd_id_type_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '人群文件中id的类型：10-手机号码，20-邮箱地址，30-微信openid，40-push token',
  `call_type_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调用类型：10-运营类，20-技术调用类',
  `file_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '人群文件id',
  `department` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门单位',
  `desc` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `msg_content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '消息内容。占位符用${name}表示',
  `send_channel_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息发送渠道：10-短信，20-邮件，30-微信公众号，40-push',
  `send_account_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '发送账号，0为默认。（如短信可选腾讯云、阿里云）',
  `channel_attr_json` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '渠道属性json字符串',
  `audit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前消息审核状态，运营类需审核：10-无需审核审核，20-待审核，30-审核中，40-审核成功，50-被拒绝',
  `flow_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '流水号',
  `msg_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前消息状态：10-技术调研，20-新建，30-等待发送，40-停用，50-发送中，60-发送成功，70-发送失败，',
  `cron_task_id` bigint(20) DEFAULT NULL COMMENT '定时任务Id',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0-不删除；1-删除',
  `creator_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者',
  `updator_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后的更新者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_cron_task_id` (`cron_task_id`),
  KEY `idx_creator_id` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息模板信息';

#
# Structure for table "admin_user"
#

DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `job_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '员工工号',
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `nickname` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '平台昵称',
  `phone` char(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `email_addr` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱地址',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `department` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '工作部门',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '账号等级：0-冻结，10-root权限，20-高级权限，30-普通权限',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0-不删除；1-删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_phone` (`phone`),
  KEY `idx_email_addr` (`email_addr`),
  KEY `idx_employee_ID` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='发送渠道表';

#
# Structure for table "support_crowd_file"
#

DROP TABLE IF EXISTS `support_crowd_file`;
CREATE TABLE `support_crowd_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件路径',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '文件大小',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始文件名',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0-不删除；1-删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='人群文件表';
