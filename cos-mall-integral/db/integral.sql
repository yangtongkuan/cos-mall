## 辅助系统类
CREATE TABLE `sys_constant_setting` (
  `id` int(18) NOT NULL AUTO_INCREMENT COMMENT '标识 系统参数配置',
  `sysCustomer` varchar(64) DEFAULT NULL COMMENT '客户标识',
  `param` varchar(64) NOT NULL COMMENT '参数',
  `parVal` text COMMENT '数值',
  `delFlag` int(1) DEFAULT '0' COMMENT '删除状态(1删; 0未删)',
  `note` varchar(256) DEFAULT '' COMMENT '备注',
  `UUID` varchar(256) DEFAULT '' COMMENT '唯一标识',
  `createTim` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8
