CREATE TABLE `gr_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_number` varchar(10) DEFAULT NULL COMMENT '仓库编号',
  `store_name` varchar(50) DEFAULT NULL COMMENT '仓库名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '仓管人员',
  `state` int(1) DEFAULT NULL COMMENT '状态 0：生效  1：失效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='仓库';