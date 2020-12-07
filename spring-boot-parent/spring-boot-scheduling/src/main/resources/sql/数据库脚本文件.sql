/*
Navicat MySQL Data Transfer

Source Server         : 106.53.217.86_3306
Source Server Version : 80020
Source Host           : 106.53.217.86:3306
Source Database       : lzx

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-12-07 16:34:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` int NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `bean_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'bean名称',
  `method_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `method_params` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法参数',
  `cron_expression` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'cron表达式',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `job_status` tinyint DEFAULT NULL COMMENT '状态【1-正常  0-暂停】',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('5', 'demoTask', 'taskWithParams', '张三', '0 0/1 * * * ?', '执行有参示例任务', '1', '2020-10-27 17:26:50', '2020-10-27 17:26:42');
INSERT INTO `sys_job` VALUES ('6', 'demoTask', 'taskNoParams', null, '0 0/1 * * * ?', '执行无参示例任务', '1', '2020-12-06 15:09:24', null);
