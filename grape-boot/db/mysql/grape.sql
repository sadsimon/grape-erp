/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : grape

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2025-06-20 16:37:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gr_brand
-- ----------------------------
DROP TABLE IF EXISTS `gr_brand`;
CREATE TABLE `gr_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand_category_id` bigint(20) DEFAULT NULL COMMENT '品牌类别',
  `brand_code` varchar(50) DEFAULT NULL COMMENT '品牌编码',
  `brand_name` varchar(50) DEFAULT NULL COMMENT '品牌名称',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='品牌';

-- ----------------------------
-- Records of gr_brand
-- ----------------------------
INSERT INTO `gr_brand` VALUES ('1', '1', 'lengjingxing', '冷静星', '1', '10000', '0', '1', '10000', '2025-03-28 16:17:30', '10000', '2025-03-28 16:25:18');
INSERT INTO `gr_brand` VALUES ('2', '1', 'code', 'name', '1', '10000', '0', '1', '10000', '2025-03-28 16:24:21', '10000', '2025-03-28 16:25:30');
INSERT INTO `gr_brand` VALUES ('3', '2', 'code', 'name', '1', '10000', '0', '1', '10000', '2025-03-28 16:25:04', '10000', '2025-03-28 16:25:30');
INSERT INTO `gr_brand` VALUES ('4', '1', 'code', 'name', '1', '10000', '0', '0', '10000', '2025-03-28 16:25:41', '10000', '2025-04-29 21:44:28');
INSERT INTO `gr_brand` VALUES ('5', '2', 'ff', '空调遥控器', '1', '10000', '0', '0', '10000', '2025-03-28 19:36:41', '10000', '2025-04-19 19:05:19');
INSERT INTO `gr_brand` VALUES ('6', '1', 'qweqwe', '美的', '1', '10000', '0', '0', '10000', '2025-03-28 19:45:19', '10000', '2025-04-19 19:05:22');
INSERT INTO `gr_brand` VALUES ('7', '1', '132432', '格力手机', '0', '10000', '0', '0', '10000', '2025-04-13 20:46:00', '10000', '2025-04-19 00:23:33');

-- ----------------------------
-- Table structure for gr_brand_category
-- ----------------------------
DROP TABLE IF EXISTS `gr_brand_category`;
CREATE TABLE `gr_brand_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `category_name` varchar(50) DEFAULT NULL COMMENT '品牌类别',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='品牌分类';

-- ----------------------------
-- Records of gr_brand_category
-- ----------------------------
INSERT INTO `gr_brand_category` VALUES ('1', null, '格力', '0', '10000', '0', '0', '10000', '2025-03-27 20:47:36', '10000', '2025-03-27 20:47:36');
INSERT INTO `gr_brand_category` VALUES ('2', '1', '格力空调', '0', '10000', '0', '0', '10000', '2025-03-27 20:47:50', '10000', '2025-03-27 20:47:50');
INSERT INTO `gr_brand_category` VALUES ('3', '2', '空调1', '0', '10000', '0', '1', '10000', '2025-04-02 22:13:28', '10000', '2025-06-04 13:13:21');

-- ----------------------------
-- Table structure for gr_contactunits
-- ----------------------------
DROP TABLE IF EXISTS `gr_contactunits`;
CREATE TABLE `gr_contactunits` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contactunits_code` varchar(15) DEFAULT NULL COMMENT '编号',
  `contactunits_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `category_id` bigint(20) DEFAULT NULL COMMENT '类别',
  `contact_id` bigint(20) DEFAULT NULL COMMENT '首要联系人',
  `deposit_bank` varchar(50) DEFAULT NULL COMMENT '开户银行',
  `bank_number` varchar(50) DEFAULT NULL COMMENT '银行账户',
  `taxpayer_id_num` varchar(30) DEFAULT NULL COMMENT '纳税人识别号',
  `init_advance_in` decimal(10,2) DEFAULT NULL COMMENT '期初预收款',
  `init_advance_out` decimal(10,2) DEFAULT NULL COMMENT '期初预付款',
  `advance_in` decimal(10,2) DEFAULT NULL COMMENT '累计预收款',
  `advance_out` decimal(10,2) DEFAULT NULL COMMENT '累计预付款',
  `rate` double DEFAULT NULL COMMENT '增值税率%',
  `init_need_pay` decimal(10,2) DEFAULT NULL COMMENT '期初应付',
  `need_pay` decimal(10,2) DEFAULT NULL COMMENT '累计应付',
  `init_need_get` decimal(10,2) DEFAULT NULL COMMENT '期初应收',
  `need_get` decimal(10,2) DEFAULT NULL COMMENT '累计应收',
  `payable` decimal(10,2) DEFAULT NULL COMMENT '累计预付款',
  `type` varchar(1) DEFAULT NULL COMMENT '类型：0：供应商；1：客户',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='往来单位（供应商、客户）';

-- ----------------------------
-- Records of gr_contactunits
-- ----------------------------
INSERT INTO `gr_contactunits` VALUES ('24', '供应商1', '手机供应商', '3', null, '', '', '', null, null, '25.00', '334.00', null, null, null, null, null, null, '0', null, '10000', '3', '0', '10000', '2025-03-23 19:58:13', '10000', '2025-06-19 13:33:46');
INSERT INTO `gr_contactunits` VALUES ('25', '002', '供应商2', '1', null, '', '', '', null, null, null, null, null, null, null, null, null, null, '0', null, '10000', '1', '0', '10000', '2025-05-22 16:44:40', '10000', '2025-06-19 11:40:47');
INSERT INTO `gr_contactunits` VALUES ('26', '002', '客户1', '3', null, '', '', '', null, null, null, null, null, null, null, null, null, null, '1', null, '10000', '0', '0', '10000', '2025-06-12 12:47:47', '10000', '2025-06-19 17:20:25');

-- ----------------------------
-- Table structure for gr_contactunits_category
-- ----------------------------
DROP TABLE IF EXISTS `gr_contactunits_category`;
CREATE TABLE `gr_contactunits_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `category_name` varchar(50) DEFAULT NULL COMMENT '类别',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='往来单位分类';

-- ----------------------------
-- Records of gr_contactunits_category
-- ----------------------------
INSERT INTO `gr_contactunits_category` VALUES ('1', null, '全国总代', '0', '10000', '0', '0', '10000', '2025-03-15 16:49:37', '10000', '2025-03-15 16:49:37');
INSERT INTO `gr_contactunits_category` VALUES ('2', '1', '上海代理1', '0', '10000', '0', '1', '10000', '2025-03-15 16:49:46', '10000', '2025-06-04 13:11:43');
INSERT INTO `gr_contactunits_category` VALUES ('3', '1', '手机供应商', '0', '10000', '0', '0', '10000', '2025-04-02 22:12:27', '10000', '2025-06-13 11:39:36');

-- ----------------------------
-- Table structure for gr_contactunits_contact
-- ----------------------------
DROP TABLE IF EXISTS `gr_contactunits_contact`;
CREATE TABLE `gr_contactunits_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contactunits_id` bigint(20) NOT NULL COMMENT '往来单位id',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `landline` varchar(15) DEFAULT NULL COMMENT '座机',
  `net_contact` varchar(15) DEFAULT NULL COMMENT 'QQ/微信/e-mail',
  `province` varchar(15) DEFAULT NULL COMMENT '省',
  `city` varchar(15) DEFAULT NULL COMMENT '市',
  `district` varchar(15) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `primary_person` int(11) DEFAULT NULL COMMENT '首要联系人 0：是  1：否',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='往来单位联系人';

-- ----------------------------
-- Records of gr_contactunits_contact
-- ----------------------------
INSERT INTO `gr_contactunits_contact` VALUES ('7', '21', '', '12312313', '', '', null, null, null, '', null, null, '10000', '0', '1', '10000', '2025-03-23 18:04:11', '10000', '2025-03-23 19:43:07');
INSERT INTO `gr_contactunits_contact` VALUES ('8', '21', '啊实打实', 'sdsad', '4545-9877', '1165214575', '北京市', '市辖区', '东城区', 'asd 在地方士大夫士大夫', '0', null, '10000', '0', '0', '10000', '2025-03-23 19:17:43', '10000', '2025-03-23 19:47:19');
INSERT INTO `gr_contactunits_contact` VALUES ('9', '24', '小红', '23123', '123123', '656523', '北京市', '市辖区', '东城区', '啊飒飒', '0', null, '10000', '0', '0', '10000', '2025-03-23 20:04:37', '10000', '2025-06-05 11:17:45');
INSERT INTO `gr_contactunits_contact` VALUES ('10', '24', '小明', '12121212', '', '', null, null, null, '', null, null, '10000', '0', '0', '10000', '2025-03-26 19:49:26', '10000', '2025-06-05 11:17:45');
INSERT INTO `gr_contactunits_contact` VALUES ('11', '24', '张三', '', '', '', null, null, null, '', null, null, '10000', '0', '0', '10000', '2025-03-26 19:49:26', '10000', '2025-06-05 11:17:45');
INSERT INTO `gr_contactunits_contact` VALUES ('12', '24', '李四', '', '', '', null, null, null, '', null, null, '10000', '0', '0', '10000', '2025-03-26 19:51:56', '10000', '2025-06-05 11:17:45');
INSERT INTO `gr_contactunits_contact` VALUES ('13', '25', '供应商2', '188726171', '', '', '北京市', '市辖区', '朝阳区', '', null, null, '10000', '0', '0', '10000', '2025-06-04 17:25:52', '10000', '2025-06-12 16:10:17');

-- ----------------------------
-- Table structure for gr_current_stock
-- ----------------------------
DROP TABLE IF EXISTS `gr_current_stock`;
CREATE TABLE `gr_current_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `stock_count` bigint(20) DEFAULT NULL COMMENT '库存',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='当前库存';

-- ----------------------------
-- Records of gr_current_stock
-- ----------------------------
INSERT INTO `gr_current_stock` VALUES ('32', '6', '38', '-100870', '10000', '96', '0', '10000', '2025-05-29 14:47:56', '10000', '2025-06-13 11:26:58');
INSERT INTO `gr_current_stock` VALUES ('35', '11', '11', '-4', '10000', '2', '0', '10000', '2025-05-29 18:13:33', '10000', '2025-06-03 13:58:33');
INSERT INTO `gr_current_stock` VALUES ('36', '11', '38', '3', '10000', '1', '0', '10000', '2025-05-29 18:13:33', '10000', '2025-05-29 21:57:19');
INSERT INTO `gr_current_stock` VALUES ('45', '6', '11', '4', '10000', '2', '0', '10000', '2025-06-06 12:02:54', '10000', '2025-06-06 12:20:15');
INSERT INTO `gr_current_stock` VALUES ('46', '9', '39', '27', '10000', '1', '0', '10000', '2025-06-06 12:19:11', '10000', '2025-06-17 11:11:38');
INSERT INTO `gr_current_stock` VALUES ('47', '9', '11', '27', '10000', '2', '0', '10000', '2025-06-06 12:19:54', '10000', '2025-06-17 11:11:38');
INSERT INTO `gr_current_stock` VALUES ('48', '9', '38', '2', '10000', '0', '0', '10000', '2025-06-06 12:20:15', '10000', '2025-06-06 12:20:15');

-- ----------------------------
-- Table structure for gr_document
-- ----------------------------
DROP TABLE IF EXISTS `gr_document`;
CREATE TABLE `gr_document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `document_code` varchar(20) DEFAULT NULL COMMENT '单据编号',
  `contactunits_id` bigint(20) DEFAULT NULL COMMENT '往来单位id',
  `document_time` datetime DEFAULT NULL COMMENT '单据日期',
  `delivery_time` datetime DEFAULT NULL COMMENT '交货日期',
  `user_id` bigint(20) DEFAULT NULL COMMENT '业务员id',
  `document_status` varchar(1) DEFAULT NULL COMMENT '单据状态  0：未入库 1：部分入库 2：全部入库  3：关闭',
  `review_status` varchar(1) DEFAULT NULL COMMENT '审核状态 0：未审核 1：已审核',
  `document_type` varchar(50) DEFAULT NULL COMMENT '类型  01：采购订单  02：采购单  03：采购退货单  11：报价单  12：销货订单  13：销货单  14：销货退货单  21：调拨单  22：其他入库单  23：其他出库单  24：成本调整单  25：组装单  26：拆卸单 32：付款单 34：收款单 36：支出单 37：收入单 38：预付款单 39：预收款单\r\n',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '税价合计金额',
  `discounted_rate` varchar(2) DEFAULT NULL COMMENT '优惠率',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `final_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠后金额（最终金额）',
  `amount_type` varchar(1) DEFAULT NULL COMMENT '类型  1：收入; 2:支出',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单据';

-- ----------------------------
-- Records of gr_document
-- ----------------------------

-- ----------------------------
-- Table structure for gr_document_account_detail
-- ----------------------------
DROP TABLE IF EXISTS `gr_document_account_detail`;
CREATE TABLE `gr_document_account_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `document_id` bigint(20) DEFAULT NULL COMMENT '关联单据id',
  `document_settle_id` bigint(20) DEFAULT NULL COMMENT '关联财务单据id',
  `settle_document_code` varchar(20) DEFAULT NULL COMMENT '财务单据编号',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `amount_type` varchar(50) DEFAULT NULL COMMENT '类型  1：收入; 2:支出',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单据支付账号详情';

-- ----------------------------
-- Records of gr_document_account_detail
-- ----------------------------

-- ----------------------------
-- Table structure for gr_document_detail
-- ----------------------------
DROP TABLE IF EXISTS `gr_document_detail`;
CREATE TABLE `gr_document_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `document_id` bigint(20) DEFAULT NULL COMMENT '父级单据id',
  `document_code` varchar(20) DEFAULT NULL COMMENT '单据编号',
  `project_id` bigint(20) DEFAULT NULL COMMENT '关联项目id',
  `barcode` varchar(30) DEFAULT NULL COMMENT '条码id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `quantity` bigint(20) DEFAULT NULL COMMENT '数量',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `inventory_num` int(11) DEFAULT NULL COMMENT '可用库存',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '采购单价',
  `discount_rate` decimal(10,5) DEFAULT NULL COMMENT '折扣率%',
  `tax_rate` decimal(10,5) DEFAULT NULL COMMENT '税率%',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单据详情';

-- ----------------------------
-- Records of gr_document_detail
-- ----------------------------

-- ----------------------------
-- Table structure for gr_document_settle_detail
-- ----------------------------
DROP TABLE IF EXISTS `gr_document_settle_detail`;
CREATE TABLE `gr_document_settle_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `document_id` bigint(20) DEFAULT NULL COMMENT '父级单据id',
  `document_code` varchar(20) DEFAULT NULL COMMENT '单据编号',
  `payment_amount` decimal(10,2) DEFAULT NULL COMMENT '付款金额',
  `advance_amount` decimal(10,2) DEFAULT NULL COMMENT '预付款金额',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `amount_type` varchar(50) DEFAULT NULL COMMENT '类型  1：收入; 2:支出',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单据结算';

-- ----------------------------
-- Records of gr_document_settle_detail
-- ----------------------------

-- ----------------------------
-- Table structure for gr_field
-- ----------------------------
DROP TABLE IF EXISTS `gr_field`;
CREATE TABLE `gr_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) DEFAULT NULL COMMENT '功能编码',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `type` varchar(1) DEFAULT NULL COMMENT '类型（0：默认；1：用户）',
  `field_json` varchar(500) DEFAULT NULL COMMENT '字段详情',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='字段管理配置';

-- ----------------------------
-- Records of gr_field
-- ----------------------------
INSERT INTO `gr_field` VALUES ('1', 'producList', '商品列表', '0', '[{ \"name\":\"商品名称\",\"code\":\"name\",\"check\":false},{ \"name\":\"商品编码\",\"code\":\"number\",\"check\":true}]', null, null, '0', null, null, '10000', '2025-05-12 23:10:15');
INSERT INTO `gr_field` VALUES ('8', 'producList', null, '1', '[{\"name\":\"商品名称\",\"code\":\"name\",\"check\":true},{\"name\":\"商品编码\",\"code\":\"number\",\"check\":true}]', null, '31', '0', '10000', '2025-05-12 23:09:15', '10000', '2025-06-18 09:26:16');

-- ----------------------------
-- Table structure for gr_income_expend
-- ----------------------------
DROP TABLE IF EXISTS `gr_income_expend`;
CREATE TABLE `gr_income_expend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `amount_type` varchar(1) DEFAULT NULL COMMENT '类型  1：收入; 2:支出',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `state` int(1) DEFAULT NULL COMMENT '状态 0：生效  1：失效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='收支项目';

-- ----------------------------
-- Records of gr_income_expend
-- ----------------------------
INSERT INTO `gr_income_expend` VALUES ('1', '运费', '1', null, '1', '10000', '0', '0', '10000', '2025-06-05 13:00:25', '10000', '2025-06-05 15:23:47');
INSERT INTO `gr_income_expend` VALUES ('2', '人工', '1', null, '1', '10000', '0', '0', '10000', '2025-06-05 15:23:18', '10000', '2025-06-05 15:23:18');
INSERT INTO `gr_income_expend` VALUES ('3', '运费2', '2', null, '1', '10000', '0', '0', '10000', '2025-06-05 15:23:28', '10000', '2025-06-05 15:23:28');
INSERT INTO `gr_income_expend` VALUES ('4', '人工2', '2', null, '1', '10000', '0', '0', '10000', '2025-06-05 15:23:46', '10000', '2025-06-05 15:26:28');
INSERT INTO `gr_income_expend` VALUES ('5', '测试', '1', null, '1', '10000', '0', '0', '10000', '2025-06-11 09:22:48', '10000', '2025-06-11 09:22:48');

-- ----------------------------
-- Table structure for gr_inventory
-- ----------------------------
DROP TABLE IF EXISTS `gr_inventory`;
CREATE TABLE `gr_inventory` (
  `id` bigint(20) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gr_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for gr_product
-- ----------------------------
DROP TABLE IF EXISTS `gr_product`;
CREATE TABLE `gr_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `barcode` varchar(30) DEFAULT NULL COMMENT '条码',
  `specs` varchar(30) DEFAULT NULL COMMENT '规格',
  `category_id` bigint(20) DEFAULT NULL COMMENT '类别id',
  `default_store_id` bigint(20) DEFAULT NULL COMMENT '默认仓库',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '首选入库单位',
  `origin` varchar(100) DEFAULT NULL COMMENT '产地',
  `regist_code` varchar(50) DEFAULT NULL COMMENT '注册证号',
  `production_license` varchar(30) DEFAULT NULL COMMENT '生产许可',
  `default_contactunits_id` bigint(20) DEFAULT NULL COMMENT '默认供应商id',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌',
  `plu` int(11) DEFAULT NULL COMMENT 'PLU码(1-99999数字)',
  `weight` double DEFAULT NULL COMMENT '重量(KG)',
  `length` double DEFAULT NULL COMMENT '长',
  `wide` double DEFAULT NULL COMMENT '宽',
  `tall` double DEFAULT NULL COMMENT '高',
  `volume_unit` varchar(2) DEFAULT NULL COMMENT '长宽高单位（1-cm；2-m）',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of gr_product
-- ----------------------------
INSERT INTO `gr_product` VALUES ('6', '编码', '名称', '1212', '型号', '8', '38', '41', '南通', '123123', '12123', '24', '5', '123123', '20', '1', '2', '4', '2', '备注', '0', '10000', null, '0', null, null, null, null);
INSERT INTO `gr_product` VALUES ('7', '123', '222', '', '', '17', null, '46', '', '', '', '24', '6', null, null, '1', '2', '3', '1', '', '0', '10000', null, '1', null, null, '10000', '2025-05-14 20:44:01');
INSERT INTO `gr_product` VALUES ('8', '123iusdj', 'iphone15', '撒大苏打3123', '现场', '9', '38', '46', '南通', '阿萨', '大飒飒的', '24', '4', '1232131231', '20', '1', '2', '3', '1', '这是一个备注', '0', '10000', null, '0', null, '2025-04-02 22:43:18', null, null);
INSERT INTO `gr_product` VALUES ('9', 'cskc', '测试库存', '', '', null, '39', '47', '', '', '', '24', null, null, null, null, null, null, '', '', '1', '10000', '0', '0', '10000', '2025-05-05 14:59:24', '10000', '2025-05-05 14:59:24');
INSERT INTO `gr_product` VALUES ('11', '003', 'iphone16', '121212', '', '8', '11', '41', '', '', '', null, null, null, null, null, null, null, '', '', '1', '10000', null, '1', null, '2025-05-29 18:13:33', '10000', '2025-06-04 13:07:00');
INSERT INTO `gr_product` VALUES ('12', 'xiaomi', '小米手机1', '', '', null, null, null, '', '', '', null, null, null, null, null, null, null, '', '', '1', '10000', '0', '1', '10000', '2025-06-01 21:00:04', '10000', '2025-06-04 12:59:01');
INSERT INTO `gr_product` VALUES ('13', 'sanxinshouji', '三星手机', '', '', null, null, null, '', '', '', null, null, null, null, null, null, null, '', '', '1', '10000', '0', '1', '10000', '2025-06-01 21:24:21', '10000', '2025-06-01 21:24:58');
INSERT INTO `gr_product` VALUES ('14', 'sanxing', '三星手机', '', '', null, null, null, '', '', '', null, null, null, null, null, null, null, '', '', '1', '10000', '0', '1', '10000', '2025-06-01 21:25:20', '10000', '2025-06-04 13:07:40');

-- ----------------------------
-- Table structure for gr_product_barcode
-- ----------------------------
DROP TABLE IF EXISTS `gr_product_barcode`;
CREATE TABLE `gr_product_barcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `barcode` varchar(30) DEFAULT NULL COMMENT '条码',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `retail_price` decimal(10,2) DEFAULT NULL COMMENT '零售价',
  `wholesale_price` decimal(10,2) DEFAULT NULL COMMENT '批发价',
  `membership_price` decimal(10,2) DEFAULT NULL COMMENT 'VIP会员价',
  `discount_rate` decimal(10,2) DEFAULT NULL COMMENT '折扣率',
  `lowest_selling_price` decimal(10,2) DEFAULT NULL COMMENT '最低售价',
  `expect_purchase_price` decimal(10,2) DEFAULT NULL COMMENT '预计采购价',
  `highest_purchase_price` decimal(10,2) DEFAULT NULL COMMENT '最高采购价',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='商品条码';

-- ----------------------------
-- Records of gr_product_barcode
-- ----------------------------
INSERT INTO `gr_product_barcode` VALUES ('99', '1', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '1', '10000', '2025-04-26 10:47:47', '10000', '2025-04-28 21:37:24');
INSERT INTO `gr_product_barcode` VALUES ('100', '4', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '1', '10000', '2025-04-26 10:47:47', '10000', '2025-04-28 21:37:24');
INSERT INTO `gr_product_barcode` VALUES ('101', '7', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '1', '10000', '2025-04-26 10:47:47', '10000', '2025-04-28 21:37:24');
INSERT INTO `gr_product_barcode` VALUES ('102', '77', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '1', '10000', '2025-04-28 21:37:24', '10000', '2025-05-05 11:16:02');
INSERT INTO `gr_product_barcode` VALUES ('103', '11', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '1', '10000', '2025-04-28 21:37:24', '10000', '2025-05-05 11:16:02');
INSERT INTO `gr_product_barcode` VALUES ('104', '44', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '1', '10000', '2025-04-28 21:37:24', '10000', '2025-05-05 11:16:02');
INSERT INTO `gr_product_barcode` VALUES ('105', '11', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '1', '10000', '2025-05-05 11:16:02', '10000', '2025-05-22 15:34:03');
INSERT INTO `gr_product_barcode` VALUES ('106', '44', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '1', '10000', '2025-05-05 11:16:02', '10000', '2025-05-22 15:34:03');
INSERT INTO `gr_product_barcode` VALUES ('107', '77', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '1', '10000', '2025-05-05 11:16:02', '10000', '2025-05-22 15:34:03');
INSERT INTO `gr_product_barcode` VALUES ('108', '1123', '9', '47', null, null, null, null, null, null, null, '10000', '0', '0', '10000', '2025-05-05 14:59:24', '10000', '2025-05-05 14:59:24');
INSERT INTO `gr_product_barcode` VALUES ('109', '11', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '1', '10000', '2025-05-22 15:34:03', '10000', '2025-05-24 14:08:57');
INSERT INTO `gr_product_barcode` VALUES ('110', '44', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '1', '10000', '2025-05-22 15:34:03', '10000', '2025-05-24 14:08:57');
INSERT INTO `gr_product_barcode` VALUES ('111', '77', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '1', '10000', '2025-05-22 15:34:03', '10000', '2025-05-24 14:08:57');
INSERT INTO `gr_product_barcode` VALUES ('112', '11', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '1', '10000', '2025-05-24 14:08:57', '10000', '2025-05-24 14:19:14');
INSERT INTO `gr_product_barcode` VALUES ('113', '44', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '1', '10000', '2025-05-24 14:08:57', '10000', '2025-05-24 14:19:14');
INSERT INTO `gr_product_barcode` VALUES ('114', '77', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '1', '10000', '2025-05-24 14:08:57', '10000', '2025-05-24 14:19:14');
INSERT INTO `gr_product_barcode` VALUES ('115', '11', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '1', '10000', '2025-05-24 14:19:14', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_barcode` VALUES ('116', '44', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '1', '10000', '2025-05-24 14:19:14', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_barcode` VALUES ('117', '77', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '1', '10000', '2025-05-24 14:19:14', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_barcode` VALUES ('121', '12123', '11', '41', null, null, null, null, null, null, null, '10000', '0', '1', '10000', '2025-05-29 18:13:33', '10000', '2025-05-29 18:18:14');
INSERT INTO `gr_product_barcode` VALUES ('122', '123123', '11', '42', null, null, null, null, null, null, null, '10000', '0', '1', '10000', '2025-05-29 18:13:33', '10000', '2025-05-29 18:18:14');
INSERT INTO `gr_product_barcode` VALUES ('123', '12312', '11', '45', null, null, null, null, null, null, null, '10000', '0', '1', '10000', '2025-05-29 18:13:33', '10000', '2025-05-29 18:18:14');
INSERT INTO `gr_product_barcode` VALUES ('124', '1', '11', '41', null, null, null, null, null, null, null, '10000', '0', '1', '10000', '2025-05-29 18:18:14', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_barcode` VALUES ('125', '2', '11', '42', null, null, null, null, null, null, null, '10000', '0', '1', '10000', '2025-05-29 18:18:14', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_barcode` VALUES ('126', '3', '11', '45', null, null, null, null, null, null, null, '10000', '0', '1', '10000', '2025-05-29 18:18:14', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_barcode` VALUES ('127', '2', '11', '42', null, null, null, null, null, null, null, '10000', '0', '0', '10000', '2025-06-01 22:14:16', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_barcode` VALUES ('128', '3', '11', '45', null, null, null, null, null, null, null, '10000', '0', '0', '10000', '2025-06-01 22:14:16', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_barcode` VALUES ('129', '1', '11', '41', null, null, null, null, null, null, null, '10000', '0', '0', '10000', '2025-06-01 22:14:16', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_barcode` VALUES ('130', '77', '6', '45', '8.00', '9.00', null, null, '16.00', '17.00', null, '10000', '0', '0', '10000', '2025-06-11 10:02:54', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_barcode` VALUES ('131', '11', '6', '41', '2.00', '3.00', null, null, '12.00', '13.00', null, '10000', '0', '0', '10000', '2025-06-11 10:02:54', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_barcode` VALUES ('132', '44', '6', '42', '5.00', '6.00', null, null, '14.00', '15.00', null, '10000', '0', '0', '10000', '2025-06-11 10:02:54', '10000', '2025-06-11 10:02:54');

-- ----------------------------
-- Table structure for gr_product_category
-- ----------------------------
DROP TABLE IF EXISTS `gr_product_category`;
CREATE TABLE `gr_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `category_name` varchar(50) DEFAULT NULL COMMENT '商品类别',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- ----------------------------
-- Records of gr_product_category
-- ----------------------------
INSERT INTO `gr_product_category` VALUES ('8', null, '数码', '0', '10000', '0', '0', '10000', '2025-04-02 22:18:40', '10000', '2025-04-02 22:18:40');
INSERT INTO `gr_product_category` VALUES ('9', '8', '手机', '0', '10000', '0', '0', '10000', '2025-04-02 22:18:46', '10000', '2025-04-02 22:18:46');
INSERT INTO `gr_product_category` VALUES ('10', '8', '相机啊', '0', '10000', '0', '0', '10000', '2025-04-02 22:18:59', '10000', '2025-04-02 22:20:12');
INSERT INTO `gr_product_category` VALUES ('11', '9', '小米手机', '0', '10000', '0', '0', '10000', '2025-04-02 22:19:12', '10000', '2025-04-02 22:19:12');
INSERT INTO `gr_product_category` VALUES ('12', '9', '苹果手机', '0', '10000', '0', '0', '10000', '2025-04-12 20:59:25', '10000', '2025-04-12 20:59:25');
INSERT INTO `gr_product_category` VALUES ('13', '9', '华为手机', '0', '10000', '0', '0', '10000', '2025-04-12 20:59:41', '10000', '2025-04-12 20:59:41');
INSERT INTO `gr_product_category` VALUES ('14', '8', '电脑', '0', '10000', '0', '0', '10000', '2025-04-12 20:59:49', '10000', '2025-04-12 20:59:49');
INSERT INTO `gr_product_category` VALUES ('15', '14', '联想', '0', '10000', '0', '0', '10000', '2025-04-12 20:59:58', '10000', '2025-04-12 20:59:58');
INSERT INTO `gr_product_category` VALUES ('16', '14', '华硕', '0', '10000', '0', '0', '10000', '2025-04-12 21:00:10', '10000', '2025-04-12 21:00:10');
INSERT INTO `gr_product_category` VALUES ('17', null, '食品', '0', '10000', '0', '0', '10000', '2025-04-12 21:00:19', '10000', '2025-04-12 21:00:19');
INSERT INTO `gr_product_category` VALUES ('18', '17', '酱油', '0', '10000', '0', '0', '10000', '2025-04-12 21:00:28', '10000', '2025-04-12 21:00:28');
INSERT INTO `gr_product_category` VALUES ('19', '17', '醋', '0', '10000', '0', '0', '10000', '2025-04-12 21:00:34', '10000', '2025-04-12 21:00:34');
INSERT INTO `gr_product_category` VALUES ('20', '17', '五谷', '0', '10000', '0', '0', '10000', '2025-04-12 21:00:45', '10000', '2025-04-12 21:00:45');
INSERT INTO `gr_product_category` VALUES ('21', null, '酒水', '0', '10000', '0', '0', '10000', '2025-04-12 21:01:11', '10000', '2025-04-12 21:01:11');
INSERT INTO `gr_product_category` VALUES ('22', '21', '白酒', '0', '10000', '0', '0', '10000', '2025-04-12 21:01:17', '10000', '2025-04-12 21:01:17');
INSERT INTO `gr_product_category` VALUES ('23', '21', '红酒', '0', '10000', '0', '0', '10000', '2025-04-12 21:01:23', '10000', '2025-04-12 21:01:23');
INSERT INTO `gr_product_category` VALUES ('24', '22', '茅台', '0', '10000', '0', '0', '10000', '2025-04-12 21:01:51', '10000', '2025-04-12 21:01:51');
INSERT INTO `gr_product_category` VALUES ('25', null, '饮料', '0', '10000', '0', '0', '10000', '2025-04-12 21:09:41', '10000', '2025-04-12 21:09:41');
INSERT INTO `gr_product_category` VALUES ('26', '25', '可乐', '0', '10000', '0', '0', '10000', '2025-04-12 21:09:47', '10000', '2025-04-12 21:09:47');
INSERT INTO `gr_product_category` VALUES ('27', '25', '雪碧', '0', '10000', '0', '0', '10000', '2025-04-12 21:09:53', '10000', '2025-04-12 21:09:53');
INSERT INTO `gr_product_category` VALUES ('28', '25', '矿泉水', '0', '10000', '0', '0', '10000', '2025-04-12 21:10:01', '10000', '2025-04-12 21:10:01');
INSERT INTO `gr_product_category` VALUES ('29', null, '床上用品', '0', '10000', '0', '0', '10000', '2025-04-12 21:10:11', '10000', '2025-04-12 21:10:11');
INSERT INTO `gr_product_category` VALUES ('30', '29', '被子', '0', '10000', '0', '0', '10000', '2025-04-12 21:10:16', '10000', '2025-04-12 21:10:16');
INSERT INTO `gr_product_category` VALUES ('31', '29', '枕头', '0', '10000', '0', '1', '10000', '2025-04-12 21:10:27', '10000', '2025-06-04 13:11:33');

-- ----------------------------
-- Table structure for gr_product_img
-- ----------------------------
DROP TABLE IF EXISTS `gr_product_img`;
CREATE TABLE `gr_product_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `url` varchar(200) DEFAULT NULL COMMENT 'url',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8 COMMENT='商品图片';

-- ----------------------------
-- Records of gr_product_img
-- ----------------------------
INSERT INTO `gr_product_img` VALUES ('210', '6', 'http://localhost:8080/upload/20250423/0232d112_423160_76057.jpeg', null, '10000', '0', '1', '10000', '2025-04-23 21:07:38', '10000', '2025-04-23 21:08:33');
INSERT INTO `gr_product_img` VALUES ('211', '6', 'http://localhost:8080/upload/20250423/0232d112_423160_76112.jpeg', null, '10000', '0', '1', '10000', '2025-04-23 21:08:33', '10000', '2025-04-23 21:49:01');
INSERT INTO `gr_product_img` VALUES ('212', '6', 'http://localhost:8080/upload/20250423/屏幕截图 2025-04-22 233543_78540.png', null, '10000', '0', '1', '10000', '2025-04-23 21:49:01', '10000', '2025-04-26 09:43:30');
INSERT INTO `gr_product_img` VALUES ('213', '6', 'http://localhost:8080/upload/20250426/屏幕截图 2025-04-22 233543_35006.png', null, '10000', '0', '1', '10000', '2025-04-26 09:43:30', '10000', '2025-04-26 10:47:47');
INSERT INTO `gr_product_img` VALUES ('214', '6', 'http://localhost:8080/upload/20250426/屏幕截图 2025-04-22 233543_35006.png', null, '10000', '0', '1', '10000', '2025-04-26 10:47:47', '10000', '2025-04-28 21:37:24');
INSERT INTO `gr_product_img` VALUES ('215', '7', '', null, '10000', '0', '0', '10000', '2025-04-26 18:01:16', '10000', '2025-04-26 18:01:16');
INSERT INTO `gr_product_img` VALUES ('216', '6', 'http://localhost:8080/upload/20250426/屏幕截图 2025-04-22 233543_35006.png', null, '10000', '0', '1', '10000', '2025-04-28 21:37:24', '10000', '2025-05-05 11:16:02');
INSERT INTO `gr_product_img` VALUES ('217', '6', 'http://localhost:8080/upload/20250426/屏幕截图 2025-04-22 233543_35006.png', null, '10000', '0', '1', '10000', '2025-05-05 11:16:02', '10000', '2025-05-22 15:34:03');
INSERT INTO `gr_product_img` VALUES ('218', '6', 'http://localhost:8080/upload/20250522/wechat_2025-05-22_153347_166_56041.png', null, '10000', '0', '1', '10000', '2025-05-22 15:34:03', '10000', '2025-05-24 14:08:57');
INSERT INTO `gr_product_img` VALUES ('219', '6', 'http://localhost:8080/upload/20250524/wechat_2025-05-24_140842_973_50935.png', null, '10000', '0', '1', '10000', '2025-05-24 14:08:58', '10000', '2025-05-24 14:19:14');
INSERT INTO `gr_product_img` VALUES ('220', '6', 'http://localhost:8080/upload/20250524/wechat_2025-05-24_141857_356_51552.png', null, '10000', '0', '1', '10000', '2025-05-24 14:19:14', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_img` VALUES ('221', '11', '', null, '10000', '0', '1', '10000', '2025-05-29 18:18:14', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_img` VALUES ('222', '11', '', null, '10000', '0', '0', '10000', '2025-06-01 22:14:16', '10000', '2025-06-01 22:14:16');
INSERT INTO `gr_product_img` VALUES ('223', '6', 'http://localhost:8080/upload/20250611/微信截图_20250611100244_36172.png', null, '10000', '0', '0', '10000', '2025-06-11 10:02:54', '10000', '2025-06-11 10:02:54');

-- ----------------------------
-- Table structure for gr_product_stock
-- ----------------------------
DROP TABLE IF EXISTS `gr_product_stock`;
CREATE TABLE `gr_product_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `stock_count` bigint(20) DEFAULT NULL COMMENT '库存',
  `max_stock_count` bigint(20) DEFAULT NULL COMMENT '最高安全库存',
  `min_stock_count` bigint(20) DEFAULT NULL COMMENT '最低安全库存',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='期初库存';

-- ----------------------------
-- Records of gr_product_stock
-- ----------------------------
INSERT INTO `gr_product_stock` VALUES ('15', '13', '11', '1', '4', '3', '10000', '0', '0', '10000', '2025-06-01 21:24:21', '10000', '2025-06-01 21:24:21');
INSERT INTO `gr_product_stock` VALUES ('16', '13', '38', '2', '7', '5', '10000', '0', '0', '10000', '2025-06-01 21:24:21', '10000', '2025-06-01 21:24:21');
INSERT INTO `gr_product_stock` VALUES ('17', '14', '11', '1', '5', '3', '10000', '0', '0', '10000', '2025-06-01 21:25:21', '10000', '2025-06-01 21:25:21');
INSERT INTO `gr_product_stock` VALUES ('18', '14', '38', '2', '6', '4', '10000', '0', '0', '10000', '2025-06-01 21:25:21', '10000', '2025-06-01 21:25:21');
INSERT INTO `gr_product_stock` VALUES ('19', '11', '11', null, null, null, '10000', '0', '0', '10000', '2025-06-01 22:14:17', '10000', '2025-06-01 22:14:17');
INSERT INTO `gr_product_stock` VALUES ('20', '11', '38', null, null, null, '10000', '0', '0', '10000', '2025-06-01 22:14:17', '10000', '2025-06-01 22:14:17');
INSERT INTO `gr_product_stock` VALUES ('21', '6', '11', null, null, null, '10000', '0', '0', '10000', '2025-06-11 10:02:54', '10000', '2025-06-11 10:02:54');
INSERT INTO `gr_product_stock` VALUES ('22', '6', '38', null, null, null, '10000', '0', '0', '10000', '2025-06-11 10:02:54', '10000', '2025-06-11 10:02:54');

-- ----------------------------
-- Table structure for gr_settlement_account
-- ----------------------------
DROP TABLE IF EXISTS `gr_settlement_account`;
CREATE TABLE `gr_settlement_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_code` varchar(20) DEFAULT NULL COMMENT '账户编号',
  `account_name` varchar(50) DEFAULT NULL COMMENT '账户名称',
  `opening_balance` decimal(10,2) DEFAULT NULL COMMENT '期初余额',
  `account_type` varchar(1) DEFAULT NULL COMMENT '账户类型  0：现金  1：银行存款',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='结算账户';

-- ----------------------------
-- Records of gr_settlement_account
-- ----------------------------
INSERT INTO `gr_settlement_account` VALUES ('1', '05105412', '农行', '202.00', '1', '1', '10000', null, '0', null, null, '10000', '2025-05-23 09:35:54');
INSERT INTO `gr_settlement_account` VALUES ('2', '923487932', '现金', '5125.33', '0', '1', '10000', '0', '0', '10000', '2025-04-06 14:43:05', '10000', '2025-04-19 00:25:24');

-- ----------------------------
-- Table structure for gr_store
-- ----------------------------
DROP TABLE IF EXISTS `gr_store`;
CREATE TABLE `gr_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_number` varchar(10) DEFAULT NULL COMMENT '仓库编号',
  `store_name` varchar(50) DEFAULT NULL COMMENT '仓库名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '仓管人员',
  `state` int(11) DEFAULT NULL COMMENT '状态 0：失效  1：生效',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='仓库';

-- ----------------------------
-- Records of gr_store
-- ----------------------------
INSERT INTO `gr_store` VALUES ('11', '001', '仓库1', '10001', '1', '10000', '0', '0', '10000', '2025-03-27 15:22:07', '10000', '2025-05-22 14:48:45');
INSERT INTO `gr_store` VALUES ('38', '002', '仓库2', '10001', '1', '10000', '0', '0', '10000', '2025-03-27 15:25:20', '10000', '2025-04-19 00:25:28');
INSERT INTO `gr_store` VALUES ('39', '333', '仓库3', null, '0', '10000', '0', '1', '10000', '2025-05-05 14:21:33', '10000', '2025-05-12 23:25:42');

-- ----------------------------
-- Table structure for gr_unit
-- ----------------------------
DROP TABLE IF EXISTS `gr_unit`;
CREATE TABLE `gr_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '基本单位名称',
  `type` int(11) DEFAULT NULL COMMENT '单位类型 0：基本单位  1：副单位',
  `model` int(11) DEFAULT NULL COMMENT '1：单单位  2：多单位',
  `many_name` varchar(50) DEFAULT NULL COMMENT '多单位名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级基本单位id',
  `capacity` int(11) DEFAULT NULL COMMENT '上级基本单位的容量',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='计量单位';

-- ----------------------------
-- Records of gr_unit
-- ----------------------------
INSERT INTO `gr_unit` VALUES ('41', '个', '0', '2', '个/把（1：6）', null, null, '10000', '0', '0', '10000', '2025-03-31 22:01:15', '10000', '2025-03-31 22:04:54');
INSERT INTO `gr_unit` VALUES ('42', '把', '1', '2', null, '41', '6', '10000', '0', '0', '10000', '2025-03-31 22:01:15', '10000', '2025-03-31 22:04:54');
INSERT INTO `gr_unit` VALUES ('43', '袋', '0', '2', '袋/包（1：10）', null, null, '10000', '0', '0', '10000', '2025-03-31 22:02:44', '10000', '2025-03-31 22:02:44');
INSERT INTO `gr_unit` VALUES ('44', '包', '1', '2', null, '43', '10', '10000', '0', '0', '10000', '2025-03-31 22:02:44', '10000', '2025-03-31 22:02:44');
INSERT INTO `gr_unit` VALUES ('45', '箱', '1', '2', null, '41', '30', '10000', '0', '0', '10000', '2025-03-31 22:04:54', '10000', '2025-03-31 22:04:54');
INSERT INTO `gr_unit` VALUES ('46', '框', '0', '1', null, null, null, '10000', '0', '0', '10000', '2025-03-31 22:19:38', '10000', '2025-03-31 22:19:38');
INSERT INTO `gr_unit` VALUES ('47', '个', '0', '1', null, null, null, '10000', '0', '0', '10000', '2025-04-04 14:14:39', '10000', '2025-04-04 14:14:39');
INSERT INTO `gr_unit` VALUES ('48', '包', '0', '1', null, null, null, '10000', '0', '0', '10000', '2025-04-04 14:25:18', '10000', '2025-06-01 21:32:55');
INSERT INTO `gr_unit` VALUES ('49', '盒', '0', '2', '盒', null, null, '10000', '0', '1', '10000', '2025-06-05 13:15:00', '10000', '2025-06-05 13:16:33');
INSERT INTO `gr_unit` VALUES ('50', '箱', '1', '2', null, '49', '2', '10000', '0', '0', '10000', '2025-06-05 13:15:00', '10000', '2025-06-05 13:15:00');

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '附件名称',
  `url` varchar(255) NOT NULL COMMENT '附件地址',
  `size` bigint(20) DEFAULT NULL COMMENT '附件大小',
  `platform` varchar(50) DEFAULT NULL COMMENT '存储平台',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='附件管理';

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
INSERT INTO `sys_attachment` VALUES ('1', '111.jpg', 'http://localhost:8080/upload/20250504/111_80454.jpg', '199988', 'LOCAL', '10000', '0', '0', '10000', '2025-05-04 22:20:54', '10000', '2025-05-04 22:20:54');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_type_id` bigint(20) NOT NULL COMMENT '字典类型ID',
  `dict_label` varchar(255) NOT NULL COMMENT '字典标签',
  `dict_value` varchar(255) DEFAULT NULL COMMENT '字典值',
  `label_class` varchar(100) DEFAULT NULL COMMENT '标签样式',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '停用', '0', 'danger', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('2', '1', '正常', '1', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('3', '2', '男', '0', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('4', '2', '女', '1', 'success', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '未知', '2', 'warning', '', '2', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('6', '3', '正常', '1', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('7', '3', '停用', '0', 'danger', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('8', '4', '全部数据', '0', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('9', '4', '本机构及子机构数据', '1', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('10', '4', '本机构数据', '2', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('11', '4', '本人数据', '3', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_data` VALUES ('12', '4', '自定义数据', '4', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('13', '5', '禁用', '0', 'danger', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('14', '5', '启用', '1', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('15', '6', '失败', '0', 'danger', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('16', '6', '成功', '1', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('17', '7', '登录成功', '0', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('18', '7', '退出成功', '1', 'warning', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('19', '7', '验证码错误', '2', 'danger', '', '2', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('20', '7', '账号密码错误', '3', 'danger', '', '3', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('21', '8', '否', '0', 'primary', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('22', '8', '是', '1', 'danger', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('23', '9', '是', '1', 'danger', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('24', '9', '否', '0', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('25', '10', '其它', '0', 'info', '', '10', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('26', '10', '查询', '1', 'primary', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('27', '10', '新增', '2', 'success', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('28', '10', '修改', '3', 'warning', '', '2', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('29', '10', '删除', '4', 'danger', '', '3', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('30', '10', '导出', '5', 'info', '', '4', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('31', '10', '导入', '6', 'info', '', '5', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('32', '11', '阿里云', '0', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('33', '11', '腾讯云', '1', '', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('34', '11', '七牛云', '2', '', '', '2', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('35', '11', '华为云', '3', '', '', '3', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('36', '12', '本地', '-1', '', '', '0', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('37', '12', '阿里云', '0', '', '', '1', '10000', '0', '0', '10000', '2025-03-22 20:31:07', '10000', '2025-03-22 20:31:07');
INSERT INTO `sys_dict_data` VALUES ('38', '14', 'cm', '1', '', '厘米', '0', null, '0', '0', '10000', '2025-03-27 20:54:40', '10000', '2025-03-27 20:54:40');
INSERT INTO `sys_dict_data` VALUES ('39', '14', 'm', '2', '', '米', '1', null, '0', '0', '10000', '2025-03-27 20:55:00', '10000', '2025-03-27 20:55:00');
INSERT INTO `sys_dict_data` VALUES ('40', '15', '生效', '1', 'primary', '', '0', null, '0', '0', '10000', '2025-04-06 13:57:59', '10000', '2025-04-18 22:34:51');
INSERT INTO `sys_dict_data` VALUES ('41', '15', '失效', '0', 'danger', '', '1', null, '0', '0', '10000', '2025-04-06 13:58:14', '10000', '2025-04-18 22:34:46');
INSERT INTO `sys_dict_data` VALUES ('42', '16', '现金', '0', '', '', '0', null, '0', '0', '10000', '2025-04-06 13:59:43', '10000', '2025-04-06 13:59:43');
INSERT INTO `sys_dict_data` VALUES ('43', '16', '银行存款', '1', '', '', '1', null, '0', '0', '10000', '2025-04-06 13:59:59', '10000', '2025-04-06 14:00:22');
INSERT INTO `sys_dict_data` VALUES ('44', '17', '草稿', '0', '', '', '0', null, '0', '0', '10000', '2025-05-22 12:23:47', '10000', '2025-05-24 15:01:00');
INSERT INTO `sys_dict_data` VALUES ('45', '17', '待入库', '1', '', '', '1', null, '0', '0', '10000', '2025-05-22 12:23:58', '10000', '2025-05-24 15:01:25');
INSERT INTO `sys_dict_data` VALUES ('46', '17', '部分入库', '2', '', '', '2', null, '0', '0', '10000', '2025-05-22 12:24:09', '10000', '2025-05-24 15:01:35');
INSERT INTO `sys_dict_data` VALUES ('47', '17', '全部入库', '3', '', '', '3', null, '0', '0', '10000', '2025-05-22 12:24:23', '10000', '2025-05-24 15:02:47');
INSERT INTO `sys_dict_data` VALUES ('48', '18', '采购订单', '01', '', '', '0', null, '0', '0', '10000', '2025-05-22 12:25:40', '10000', '2025-05-22 12:25:40');
INSERT INTO `sys_dict_data` VALUES ('49', '18', '采购入库', '02', '', '', '1', null, '0', '0', '10000', '2025-05-22 12:25:53', '10000', '2025-05-22 12:25:53');
INSERT INTO `sys_dict_data` VALUES ('50', '18', '采购退货', '03', '', '', '2', null, '0', '0', '10000', '2025-05-22 12:26:12', '10000', '2025-05-22 12:26:12');
INSERT INTO `sys_dict_data` VALUES ('51', '17', '关闭', '4', '', '', '4', null, '0', '0', '10000', '2025-05-24 15:03:03', '10000', '2025-05-24 15:03:03');
INSERT INTO `sys_dict_data` VALUES ('52', '19', '草稿', '0', '', '', '0', null, '0', '0', '10000', '2025-05-24 15:05:18', '10000', '2025-05-24 15:05:18');
INSERT INTO `sys_dict_data` VALUES ('53', '19', '待核算', '1', '', '', '1', null, '0', '0', '10000', '2025-05-24 15:05:30', '10000', '2025-05-24 15:05:30');
INSERT INTO `sys_dict_data` VALUES ('54', '19', '核算完成', '2', '', '', '2', null, '0', '0', '10000', '2025-05-24 15:05:42', '10000', '2025-05-24 15:05:42');
INSERT INTO `sys_dict_data` VALUES ('55', '19', '关闭', '3', '', '', '3', null, '0', '0', '10000', '2025-05-24 15:05:54', '10000', '2025-05-24 15:05:54');
INSERT INTO `sys_dict_data` VALUES ('56', '18', '付款单', '32', '', '', '32', null, '0', '0', '10000', '2025-05-24 15:08:59', '10000', '2025-05-28 21:53:29');
INSERT INTO `sys_dict_data` VALUES ('57', '18', '收款单', '34', '', '', '34', null, '0', '0', '10000', '2025-05-24 15:09:18', '10000', '2025-05-28 21:53:22');
INSERT INTO `sys_dict_data` VALUES ('58', '18', '支出单', '36', '', '', '36', null, '0', '0', '10000', '2025-05-24 15:09:33', '10000', '2025-06-05 17:17:05');
INSERT INTO `sys_dict_data` VALUES ('59', '18', '收入单', '37', '', '', '37', null, '0', '0', '10000', '2025-05-24 15:09:50', '10000', '2025-06-05 17:17:12');
INSERT INTO `sys_dict_data` VALUES ('60', '18', '销售出库单', '13', '', '', '13', null, '0', '0', '10000', '2025-05-28 21:52:57', '10000', '2025-05-28 21:52:57');
INSERT INTO `sys_dict_data` VALUES ('61', '18', '销售退货单', '14', '', '', '14', null, '0', '0', '10000', '2025-05-28 21:53:52', '10000', '2025-05-28 21:53:52');
INSERT INTO `sys_dict_data` VALUES ('62', '20', '供应商', '0', '', '', '0', null, '0', '0', '10000', '2025-06-04 19:14:29', '10000', '2025-06-04 19:14:29');
INSERT INTO `sys_dict_data` VALUES ('63', '20', '客户', '1', '', '', '1', null, '0', '0', '10000', '2025-06-04 19:14:35', '10000', '2025-06-04 19:14:35');
INSERT INTO `sys_dict_data` VALUES ('64', '21', '收入', '1', '', '', '0', null, '0', '0', '10000', '2025-06-05 13:01:52', '10000', '2025-06-05 13:01:52');
INSERT INTO `sys_dict_data` VALUES ('65', '21', '支出', '2', '', '', '1', null, '0', '0', '10000', '2025-06-05 13:02:01', '10000', '2025-06-05 13:02:01');
INSERT INTO `sys_dict_data` VALUES ('66', '18', '调拨单', '21', '', '', '21', null, '0', '0', '10000', '2025-06-17 11:41:38', '10000', '2025-06-17 11:41:38');
INSERT INTO `sys_dict_data` VALUES ('67', '18', '其他入库单', '22', '', '', '22', null, '0', '0', '10000', '2025-06-17 11:42:03', '10000', '2025-06-17 11:42:03');
INSERT INTO `sys_dict_data` VALUES ('68', '18', '其他出库单', '23', '', '', '23', null, '0', '0', '10000', '2025-06-17 11:42:14', '10000', '2025-06-17 11:42:14');
INSERT INTO `sys_dict_data` VALUES ('69', '18', '预付款单', '38', '', '', '38', null, '0', '0', '10000', '2025-06-18 15:50:06', '10000', '2025-06-18 15:50:06');
INSERT INTO `sys_dict_data` VALUES ('70', '18', '预收款单', '39', '', '', '39', null, '0', '0', '10000', '2025-06-18 15:50:20', '10000', '2025-06-18 15:50:20');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_type` varchar(100) NOT NULL COMMENT '字典类型',
  `dict_name` varchar(255) NOT NULL COMMENT '字典名称',
  `dict_source` tinyint(4) DEFAULT '0' COMMENT '来源  0：字典数据  1：动态SQL',
  `dict_sql` varchar(500) DEFAULT NULL COMMENT '动态SQL',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级节点',
  `has_child` tinyint(4) DEFAULT '0' COMMENT '是否有子节点',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', 'post_status', '状态', '0', null, '岗位管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('2', 'user_gender', '性别', '0', null, '用户管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('3', 'user_status', '状态', '0', null, '用户管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('4', 'role_data_scope', '数据范围', '0', null, '角色管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('5', 'enable_disable', '状态', '0', null, '功能状态：启用 | 禁用 ', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('6', 'success_fail', '状态', '0', null, '操作状态：成功 | 失败', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('7', 'login_operation', '操作信息', '0', null, '登录管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('8', 'params_type', '系统参数', '0', null, '参数管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('9', 'user_super_admin', '用户是否是超管', '0', null, '用户是否是超管', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('10', 'log_operate_type', '操作类型', '0', null, '操作日志', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('11', 'sms_platform', '短信平台类型', '0', null, '短信管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('12', 'mail_platform', '邮件平台类型', '0', null, '邮件管理', '0', null, '0', '10000', '0', '0', '10000', '2025-03-22 20:31:06', '10000', '2025-03-22 20:31:06');
INSERT INTO `sys_dict_type` VALUES ('14', 'volume_unit', '长宽高单位', '0', '', '商品管理', '0', null, '0', null, '0', '0', '10000', '2025-03-27 20:53:52', '10000', '2025-03-27 20:53:52');
INSERT INTO `sys_dict_type` VALUES ('15', 'state', '通用状态', '0', null, '业务通用状态', '0', null, '0', null, '0', '0', '10000', '2025-04-06 13:57:09', '10000', '2025-04-06 13:57:09');
INSERT INTO `sys_dict_type` VALUES ('16', 'account_type', '账户类型', '0', null, '账户管理', '0', null, '0', null, '0', '0', '10000', '2025-04-06 13:59:19', '10000', '2025-04-06 13:59:19');
INSERT INTO `sys_dict_type` VALUES ('17', 'document_status', '单据状态', '0', '', '', '0', null, '0', null, '0', '0', '10000', '2025-05-22 12:23:21', '10000', '2025-05-22 12:23:21');
INSERT INTO `sys_dict_type` VALUES ('18', 'document_type', '单据类型', '0', '', '', '1', null, '0', null, '0', '0', '10000', '2025-05-22 12:25:15', '10000', '2025-05-24 14:53:09');
INSERT INTO `sys_dict_type` VALUES ('19', 'settlement_document_status', '财务单据状态', '0', '', '', '3', null, '0', null, '0', '0', '10000', '2025-05-24 15:04:54', '10000', '2025-05-24 15:04:54');
INSERT INTO `sys_dict_type` VALUES ('20', 'contactunits_type', '类型', '0', '', '往来单位', '4', null, '0', null, '0', '0', '10000', '2025-06-04 19:14:06', '10000', '2025-06-04 19:35:38');
INSERT INTO `sys_dict_type` VALUES ('21', 'amount_type', '收支类型', '0', '', '单据', '5', null, '0', null, '0', '0', '10000', '2025-06-05 13:01:31', '10000', '2025-06-05 13:01:38');

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
  `address` varchar(32) DEFAULT NULL COMMENT '登录地点',
  `user_agent` varchar(500) DEFAULT NULL COMMENT 'User Agent',
  `status` tinyint(4) DEFAULT NULL COMMENT '登录状态  0：失败   1：成功',
  `operation` tinyint(3) unsigned DEFAULT NULL COMMENT '操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES ('1', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '0', null, '2025-06-13 11:26:23');
INSERT INTO `sys_log_login` VALUES ('2', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '0', null, '2025-06-13 11:38:47');
INSERT INTO `sys_log_login` VALUES ('3', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '1', null, '2025-06-17 11:42:28');
INSERT INTO `sys_log_login` VALUES ('4', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '0', null, '2025-06-17 11:42:30');
INSERT INTO `sys_log_login` VALUES ('5', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '0', null, '2025-06-19 10:08:26');
INSERT INTO `sys_log_login` VALUES ('6', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '0', null, '2025-06-19 13:30:40');
INSERT INTO `sys_log_login` VALUES ('7', 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '1', '0', null, '2025-06-20 14:33:53');

-- ----------------------------
-- Table structure for sys_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate`;
CREATE TABLE `sys_log_operate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module` varchar(100) DEFAULT NULL COMMENT '模块名',
  `name` varchar(100) DEFAULT NULL COMMENT '操作名',
  `req_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(20) DEFAULT NULL COMMENT '请求方法',
  `req_params` text COMMENT '请求参数',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作IP',
  `address` varchar(32) DEFAULT NULL COMMENT '登录地点',
  `user_agent` varchar(500) DEFAULT NULL COMMENT 'User Agent',
  `operate_type` tinyint(4) DEFAULT NULL COMMENT '操作类型',
  `duration` int(11) NOT NULL COMMENT '执行时长',
  `status` tinyint(4) DEFAULT NULL COMMENT '操作状态  0：失败   1：成功',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `real_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `result_msg` varchar(500) DEFAULT NULL COMMENT '返回消息',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_log_operate
-- ----------------------------
INSERT INTO `sys_log_operate` VALUES ('1', '往来单位', '保存', '/product/grContactunits', 'POST', '{\"vo\":{\"id\":26,\"contactunitsCode\":\"002\",\"contactunitsName\":\"客户1\",\"categoryId\":null,\"contactId\":null,\"depositBank\":\"\",\"bankNumber\":\"\",\"taxpayerIdNum\":\"\",\"initialAdvancePayment\":null,\"rate\":null,\"initialPayable\":null,\"type\":\"1\",\"state\":null,\"contactList\":[]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '18', '1', '10000', 'admin', null, '10000', '2025-06-12 12:47:55');
INSERT INTO `sys_log_operate` VALUES ('2', '往来单位', '修改', '/product/grContactunits', 'PUT', '{\"vo\":{\"id\":25,\"contactunitsCode\":\"002\",\"contactunitsName\":\"供应商2\",\"categoryId\":1,\"contactId\":null,\"depositBank\":\"\",\"bankNumber\":\"\",\"taxpayerIdNum\":\"\",\"initialAdvancePayment\":null,\"rate\":null,\"initialPayable\":null,\"type\":\"0\",\"state\":null,\"contactList\":[{\"id\":13,\"contactunitsId\":25,\"contactName\":\"供应商2\",\"mobile\":\"188726171\",\"landline\":\"\",\"netContact\":\"\",\"province\":\"北京市\",\"city\":\"市辖区\",\"district\":\"朝阳区\",\"address\":\"\",\"primaryPerson\":null,\"state\":null}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '50', '1', '10000', 'admin', null, '10000', '2025-06-12 16:10:22');
INSERT INTO `sys_log_operate` VALUES ('3', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":21,\"documentCode\":\"XSCK2025061300002\",\"contactunitsId\":26,\"contactunitsName\":null,\"documentTime\":\"2025/06/13 11:26:30\",\"deliveryTime\":null,\"userId\":10001,\"realName\":null,\"documentStatus\":\"2\",\"reviewStatus\":null,\"documentType\":\"13\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":17,\"amountType\":\"1\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":[{\"id\":null,\"documentId\":21,\"documentCode\":\"XSCK2025061300002\",\"barcode\":\"77\",\"projectId\":null,\"productId\":6,\"productName\":null,\"productNumber\":null,\"quantity\":1,\"unitId\":45,\"unitName\":\"箱\",\"storeId\":38,\"inStoreId\":null,\"storeName\":null,\"inventoryNum\":null,\"unitPrice\":17,\"discountRate\":null,\"taxRate\":null,\"amount\":17,\"imgList\":null}],\"documentAccountDetailList\":[{\"id\":null,\"documentId\":21,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"\",\"amount\":20,\"amountType\":\"1\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":21,\"documentCode\":\"XSCK2025061300002\",\"paymentAmount\":20,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":\"1\"}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '12411', '1', '10000', 'admin', null, '10000', '2025-06-13 11:27:08');
INSERT INTO `sys_log_operate` VALUES ('4', '往来单位分类', '修改', '/product/grContactunitsCategory', 'PUT', '{\"vo\":{\"id\":3,\"pid\":1,\"children\":[],\"categoryName\":\"手机供应商\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '6', '1', '10000', 'admin', null, '10000', '2025-06-13 11:39:39');
INSERT INTO `sys_log_operate` VALUES ('5', '菜单管理', '删除', '/sys/menu/66', 'DELETE', '{\"id\":66}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '4', '32', '1', '10000', 'admin', null, '10000', '2025-06-13 12:56:34');
INSERT INTO `sys_log_operate` VALUES ('6', '菜单管理', '保存', '/sys/menu', 'POST', '{\"vo\":{\"id\":null,\"pid\":74,\"children\":[{\"id\":78,\"pid\":74,\"children\":[],\"name\":\"调拨单\",\"url\":\"stock/allot/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-sync\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-05-23 15:39:58\",\"parentName\":null},{\"id\":79,\"pid\":74,\"children\":[],\"name\":\"其他入库单\",\"url\":\"stock/otherIn/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-vertical-align-botto\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-05-23 15:40:26\",\"parentName\":null},{\"id\":80,\"pid\":74,\"children\":[],\"name\":\"其他出库单\",\"url\":\"stock/otherOut/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-vertical-align-top\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-05-23 15:40:40\",\"parentName\":null},{\"id\":82,\"pid\":74,\"children\":[],\"name\":\"库存状况查询\",\"url\":\"stock/report/inventoryStatus/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":8,\"createTime\":\"2025-05-29 09:59:11\",\"parentName\":null}],\"name\":\"库存变动单据查询\",\"url\":\"\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-05-23 15:26:28\",\"parentName\":\"\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '30', '1', '10000', 'admin', null, '10000', '2025-06-17 10:56:32');
INSERT INTO `sys_log_operate` VALUES ('7', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":88,\"pid\":74,\"children\":[],\"name\":\"库存变动单据查询\",\"url\":\"stock/report/report/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-06-17 10:56:23\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '19', '1', '10000', 'admin', null, '10000', '2025-06-17 11:03:02');
INSERT INTO `sys_log_operate` VALUES ('8', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":88,\"pid\":74,\"children\":[],\"name\":\"库存变动单据查询\",\"url\":\"stock/report/change/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-06-17 10:56:23\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '15', '1', '10000', 'admin', null, '10000', '2025-06-17 11:03:52');
INSERT INTO `sys_log_operate` VALUES ('9', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":88,\"pid\":74,\"children\":[],\"name\":\"库存变动单据查询\",\"url\":\"stock/report/change/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-06-17 10:56:23\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '15', '1', '10000', 'admin', null, '10000', '2025-06-17 11:07:22');
INSERT INTO `sys_log_operate` VALUES ('10', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":22,\"documentCode\":\"DBD2025061700008\",\"contactunitsId\":null,\"contactunitsName\":null,\"documentTime\":\"2025/06/17 11:11:26\",\"deliveryTime\":null,\"userId\":10002,\"realName\":null,\"documentStatus\":\"2\",\"reviewStatus\":null,\"documentType\":\"21\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":null,\"amountType\":\"3\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":[{\"id\":null,\"documentId\":22,\"documentCode\":\"DBD2025061700008\",\"barcode\":\"1123\",\"projectId\":null,\"productId\":9,\"productName\":null,\"productNumber\":null,\"quantity\":1,\"unitId\":47,\"unitName\":\"个\",\"storeId\":39,\"inStoreId\":11,\"storeName\":null,\"inventoryNum\":null,\"unitPrice\":null,\"discountRate\":null,\"taxRate\":null,\"amount\":null,\"imgList\":null}],\"documentAccountDetailList\":null,\"documentSettleDetailList\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '1643', '1', '10000', 'admin', null, '10000', '2025-06-17 11:11:43');
INSERT INTO `sys_log_operate` VALUES ('11', '字典数据', '保存', '/sys/dict/data', 'POST', '{\"vo\":{\"id\":null,\"dictTypeId\":18,\"dictLabel\":\"调拨单\",\"labelClass\":\"\",\"dictValue\":\"21\",\"remark\":\"\",\"sort\":21,\"createTime\":null,\"updateTime\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '11', '1', '10000', 'admin', null, '10000', '2025-06-17 11:41:48');
INSERT INTO `sys_log_operate` VALUES ('12', '字典数据', '保存', '/sys/dict/data', 'POST', '{\"vo\":{\"id\":null,\"dictTypeId\":18,\"dictLabel\":\"其他入库单\",\"labelClass\":\"\",\"dictValue\":\"22\",\"remark\":\"\",\"sort\":22,\"createTime\":null,\"updateTime\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '7', '1', '10000', 'admin', null, '10000', '2025-06-17 11:42:08');
INSERT INTO `sys_log_operate` VALUES ('13', '字典数据', '保存', '/sys/dict/data', 'POST', '{\"vo\":{\"id\":null,\"dictTypeId\":18,\"dictLabel\":\"其他出库单\",\"labelClass\":\"\",\"dictValue\":\"23\",\"remark\":\"\",\"sort\":23,\"createTime\":null,\"updateTime\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '7', '1', '10000', 'admin', null, '10000', '2025-06-17 11:42:18');
INSERT INTO `sys_log_operate` VALUES ('14', '字段管理配置', '更新和保存', '/system/grField', 'POST', '{\"vo\":{\"id\":null,\"code\":\"producList\",\"remark\":null,\"fieldJson\":\"[{\\\"name\\\":\\\"商品名称\\\",\\\"code\\\":\\\"name\\\",\\\"check\\\":false},{\\\"name\\\":\\\"商品编码\\\",\\\"code\\\":\\\"number\\\",\\\"check\\\":true}]\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '13', '1', '10000', 'admin', null, '10000', '2025-06-18 09:26:20');
INSERT INTO `sys_log_operate` VALUES ('15', '字段管理配置', '更新和保存', '/system/grField', 'POST', '{\"vo\":{\"id\":null,\"code\":\"producList\",\"remark\":null,\"fieldJson\":\"[{\\\"name\\\":\\\"商品名称\\\",\\\"code\\\":\\\"name\\\",\\\"check\\\":true},{\\\"name\\\":\\\"商品编码\\\",\\\"code\\\":\\\"number\\\",\\\"check\\\":true}]\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '9', '1', '10000', 'admin', null, '10000', '2025-06-18 09:26:20');
INSERT INTO `sys_log_operate` VALUES ('16', '菜单管理', '保存', '/sys/menu', 'POST', '{\"vo\":{\"id\":null,\"pid\":70,\"children\":[{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null},{\"id\":84,\"pid\":70,\"children\":[],\"name\":\"收入单\",\"url\":\"settlement/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-06-04 19:45:10\",\"parentName\":null},{\"id\":71,\"pid\":70,\"children\":[],\"name\":\"付款单\",\"url\":\"settlement/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":2,\"createTime\":\"2025-05-23 15:10:13\",\"parentName\":null},{\"id\":72,\"pid\":70,\"children\":[],\"name\":\"收款单\",\"url\":\"settlement/receivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-05-23 15:11:53\",\"parentName\":null},{\"id\":81,\"pid\":70,\"children\":[],\"name\":\"收/付款单据查询\",\"url\":\"settlement/report/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":5,\"createTime\":\"2025-05-28 11:26:40\",\"parentName\":null}],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-05-23 15:07:03\",\"parentName\":\"\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '10', '1', '10000', 'admin', null, '10000', '2025-06-18 15:16:25');
INSERT INTO `sys_log_operate` VALUES ('17', '菜单管理', '保存', '/sys/menu', 'POST', '{\"vo\":{\"id\":null,\"pid\":70,\"children\":[{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null},{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null},{\"id\":84,\"pid\":70,\"children\":[],\"name\":\"收入单\",\"url\":\"settlement/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-06-04 19:45:10\",\"parentName\":null},{\"id\":71,\"pid\":70,\"children\":[],\"name\":\"付款单\",\"url\":\"settlement/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":2,\"createTime\":\"2025-05-23 15:10:13\",\"parentName\":null},{\"id\":72,\"pid\":70,\"children\":[],\"name\":\"收款单\",\"url\":\"settlement/receivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-05-23 15:11:53\",\"parentName\":null},{\"id\":81,\"pid\":70,\"children\":[],\"name\":\"收/付款单据查询\",\"url\":\"settlement/report/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":5,\"createTime\":\"2025-05-28 11:26:40\",\"parentName\":null}],\"name\":\"预收款单\",\"url\":\"settlement/receivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-05-23 15:07:03\",\"parentName\":\"\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '14', '1', '10000', 'admin', null, '10000', '2025-06-18 15:17:05');
INSERT INTO `sys_log_operate` VALUES ('18', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '16', '1', '10000', 'admin', null, '10000', '2025-06-18 15:17:35');
INSERT INTO `sys_log_operate` VALUES ('19', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":90,\"pid\":70,\"children\":[],\"name\":\"预收款单\",\"url\":\"settlement/reReceivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:17:01\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '15', '1', '10000', 'admin', null, '10000', '2025-06-18 15:17:55');
INSERT INTO `sys_log_operate` VALUES ('20', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '6', '1', '10000', 'admin', null, '10000', '2025-06-18 15:19:15');
INSERT INTO `sys_log_operate` VALUES ('21', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '16', '1', '10000', 'admin', null, '10000', '2025-06-18 15:19:35');
INSERT INTO `sys_log_operate` VALUES ('22', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":90,\"pid\":70,\"children\":[],\"name\":\"预收款单\",\"url\":\"settlement/reReceivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:17:01\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '6', '1', '10000', 'admin', null, '10000', '2025-06-18 15:20:05');
INSERT INTO `sys_log_operate` VALUES ('23', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":90,\"pid\":70,\"children\":[],\"name\":\"预收款单\",\"url\":\"settlement/reReceivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-06-18 15:17:01\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '14', '1', '10000', 'admin', null, '10000', '2025-06-18 15:20:25');
INSERT INTO `sys_log_operate` VALUES ('24', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":2,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '5', '1', '10000', 'admin', null, '10000', '2025-06-18 15:20:35');
INSERT INTO `sys_log_operate` VALUES ('25', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":84,\"pid\":70,\"children\":[],\"name\":\"收入单\",\"url\":\"settlement/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":13,\"createTime\":\"2025-06-04 19:45:10\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '15', '1', '10000', 'admin', null, '10000', '2025-06-18 15:20:55');
INSERT INTO `sys_log_operate` VALUES ('26', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":14,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '7', '1', '10000', 'admin', null, '10000', '2025-06-18 15:21:16');
INSERT INTO `sys_log_operate` VALUES ('27', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":8,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '14', '1', '10000', 'admin', null, '10000', '2025-06-18 15:21:36');
INSERT INTO `sys_log_operate` VALUES ('28', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":84,\"pid\":70,\"children\":[],\"name\":\"收入单\",\"url\":\"settlement/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":9,\"createTime\":\"2025-06-04 19:45:10\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '16', '1', '10000', 'admin', null, '10000', '2025-06-18 15:21:46');
INSERT INTO `sys_log_operate` VALUES ('29', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":81,\"pid\":70,\"children\":[],\"name\":\"收/付款单据查询\",\"url\":\"settlement/report/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":15,\"createTime\":\"2025-05-28 11:26:40\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '17', '1', '10000', 'admin', null, '10000', '2025-06-18 15:21:56');
INSERT INTO `sys_log_operate` VALUES ('30', '字典数据', '保存', '/sys/dict/data', 'POST', '{\"vo\":{\"id\":null,\"dictTypeId\":18,\"dictLabel\":\"支出单\",\"labelClass\":\"\",\"dictValue\":\"36\",\"remark\":\"\",\"sort\":36,\"createTime\":null,\"updateTime\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '11', '0', '10000', 'admin', null, '10000', '2025-06-18 15:49:47');
INSERT INTO `sys_log_operate` VALUES ('31', '字典数据', '保存', '/sys/dict/data', 'POST', '{\"vo\":{\"id\":null,\"dictTypeId\":18,\"dictLabel\":\"预付款单\",\"labelClass\":\"\",\"dictValue\":\"38\",\"remark\":\"\",\"sort\":38,\"createTime\":null,\"updateTime\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '9', '1', '10000', 'admin', null, '10000', '2025-06-18 15:50:08');
INSERT INTO `sys_log_operate` VALUES ('32', '字典数据', '保存', '/sys/dict/data', 'POST', '{\"vo\":{\"id\":null,\"dictTypeId\":18,\"dictLabel\":\"预收款单\",\"labelClass\":\"\",\"dictValue\":\"39\",\"remark\":\"\",\"sort\":39,\"createTime\":null,\"updateTime\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '18', '1', '10000', 'admin', null, '10000', '2025-06-18 15:50:28');
INSERT INTO `sys_log_operate` VALUES ('33', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":23,\"documentCode\":\"FKD2025061900009\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/19 10:24:53\",\"deliveryTime\":null,\"userId\":null,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"32\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"2\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":23,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"1\",\"amount\":23,\"amountType\":\"2\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":23,\"documentCode\":\"CGRK2025060900807\",\"paymentAmount\":23,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":\"2\"}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '93', '1', '10000', 'admin', null, '10000', '2025-06-19 10:41:06');
INSERT INTO `sys_log_operate` VALUES ('34', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":null,\"documentCode\":\"YFKD2025061900015\",\"contactunitsId\":25,\"contactunitsName\":null,\"documentTime\":\"2025/06/19 10:43:41\",\"deliveryTime\":null,\"userId\":10001,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"38\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"2\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":null,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"1\",\"amount\":20,\"amountType\":\"2\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":null,\"documentCode\":\"\",\"paymentAmount\":null,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":null}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '52', '0', '10000', 'admin', null, '10000', '2025-06-19 11:39:40');
INSERT INTO `sys_log_operate` VALUES ('35', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":25,\"documentCode\":\"YFKD2025061900015\",\"contactunitsId\":25,\"contactunitsName\":null,\"documentTime\":\"2025/06/19 10:43:41\",\"deliveryTime\":null,\"userId\":10001,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"38\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"2\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":25,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"1\",\"amount\":20,\"amountType\":\"2\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":null,\"documentCode\":\"\",\"paymentAmount\":null,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":null}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '90', '1', '10000', 'admin', null, '10000', '2025-06-19 11:40:53');
INSERT INTO `sys_log_operate` VALUES ('36', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":26,\"documentCode\":\"YFKD2025061900019\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/19 11:46:33\",\"deliveryTime\":null,\"userId\":null,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"38\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"2\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":26,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"1\",\"amount\":22,\"amountType\":\"2\",\"remark\":\"\"},{\"id\":null,\"documentId\":26,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":2,\"accountName\":\"2\",\"amount\":12,\"amountType\":\"2\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":null,\"documentCode\":\"\",\"paymentAmount\":null,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":null}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '96', '1', '10000', 'admin', null, '10000', '2025-06-19 11:46:47');
INSERT INTO `sys_log_operate` VALUES ('37', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":27,\"documentCode\":\"YFKD2025061900023\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/19 13:30:44\",\"deliveryTime\":null,\"userId\":null,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"38\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"2\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":27,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"1\",\"amount\":12,\"amountType\":\"2\",\"remark\":\"备注\"},{\"id\":null,\"documentId\":27,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":2,\"accountName\":\"2\",\"amount\":13,\"amountType\":\"2\",\"remark\":\"备注1\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":null,\"documentCode\":\"\",\"paymentAmount\":null,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":null}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '104', '1', '10000', 'admin', null, '10000', '2025-06-19 13:33:29');
INSERT INTO `sys_log_operate` VALUES ('38', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":28,\"documentCode\":\"YSKD2025061900024\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/19 13:33:35\",\"deliveryTime\":null,\"userId\":null,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"39\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"1\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":28,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"1\",\"amount\":112,\"amountType\":\"1\",\"remark\":\"\"},{\"id\":null,\"documentId\":28,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":2,\"accountName\":\"2\",\"amount\":222,\"amountType\":\"1\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":null,\"documentCode\":\"\",\"paymentAmount\":null,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":null}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '44', '1', '10000', 'admin', null, '10000', '2025-06-19 13:33:49');
INSERT INTO `sys_log_operate` VALUES ('39', '往来单位', '修改', '/product/grContactunits', 'PUT', '{\"vo\":{\"id\":26,\"contactunitsCode\":\"002\",\"contactunitsName\":\"客户1\",\"categoryId\":3,\"contactId\":null,\"depositBank\":\"\",\"bankNumber\":\"\",\"taxpayerIdNum\":\"\",\"initAdvanceIn\":null,\"initAdvanceOut\":null,\"advanceIn\":null,\"advanceOut\":null,\"rate\":null,\"initNeedPay\":null,\"needPay\":null,\"initNeedGet\":null,\"needGet\":null,\"payable\":null,\"type\":\"1\",\"state\":null,\"contactList\":[]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '50', '1', '10000', 'admin', null, '10000', '2025-06-19 17:20:33');
INSERT INTO `sys_log_operate` VALUES ('40', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":57,\"pid\":59,\"children\":[],\"name\":\"供应商 / 客户\",\"url\":\"product/contactunits/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-bulb\",\"authority\":\"\",\"sort\":5,\"createTime\":\"2025-02-23 23:05:48\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '33', '1', '10000', 'admin', null, '10000', '2025-06-19 17:34:36');
INSERT INTO `sys_log_operate` VALUES ('41', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":62,\"pid\":59,\"children\":[],\"name\":\"计量单位\",\"url\":\"product/unit/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-golden-fill\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-03-26 14:43:04\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '8', '1', '10000', 'admin', null, '10000', '2025-06-19 17:35:06');
INSERT INTO `sys_log_operate` VALUES ('42', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":62,\"pid\":59,\"children\":[],\"name\":\"计量单位\",\"url\":\"product/unit/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-golden-fill\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-03-26 14:43:04\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '15', '1', '10000', 'admin', null, '10000', '2025-06-19 17:35:16');
INSERT INTO `sys_log_operate` VALUES ('43', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":61,\"pid\":59,\"children\":[],\"name\":\"仓库\",\"url\":\"product/store/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-bank\",\"authority\":\"\",\"sort\":2,\"createTime\":\"2025-03-26 13:45:08\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '7', '1', '10000', 'admin', null, '10000', '2025-06-19 17:35:36');
INSERT INTO `sys_log_operate` VALUES ('44', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":62,\"pid\":59,\"children\":[],\"name\":\"计量单位\",\"url\":\"product/unit/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-gold\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-03-26 14:43:04\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '17', '1', '10000', 'admin', null, '10000', '2025-06-19 17:56:38');
INSERT INTO `sys_log_operate` VALUES ('45', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":81,\"pid\":70,\"children\":[],\"name\":\"收/付款单据查询\",\"url\":\"settlement/report/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":15,\"createTime\":\"2025-05-28 11:26:40\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '23', '1', '10000', 'admin', null, '10000', '2025-06-20 14:43:19');
INSERT INTO `sys_log_operate` VALUES ('46', '菜单管理', '保存', '/sys/menu', 'POST', '{\"vo\":{\"id\":null,\"pid\":70,\"children\":[{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null},{\"id\":90,\"pid\":70,\"children\":[],\"name\":\"预收款单\",\"url\":\"settlement/reReceivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-06-18 15:17:01\",\"parentName\":null},{\"id\":71,\"pid\":70,\"children\":[],\"name\":\"付款单\",\"url\":\"settlement/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":2,\"createTime\":\"2025-05-23 15:10:13\",\"parentName\":null},{\"id\":72,\"pid\":70,\"children\":[],\"name\":\"收款单\",\"url\":\"settlement/receivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-05-23 15:11:53\",\"parentName\":null},{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":8,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null},{\"id\":84,\"pid\":70,\"children\":[],\"name\":\"收入单\",\"url\":\"settlement/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":9,\"createTime\":\"2025-06-04 19:45:10\",\"parentName\":null},{\"id\":81,\"pid\":70,\"children\":[],\"name\":\"收/付款单据查询\",\"url\":\"settlement/report/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":15,\"createTime\":\"2025-05-28 11:26:40\",\"parentName\":null}],\"name\":\"收入/支出单据查询\",\"url\":\"settlement/report/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-05-23 15:07:03\",\"parentName\":\"\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '7', '1', '10000', 'admin', null, '10000', '2025-06-20 14:47:09');
INSERT INTO `sys_log_operate` VALUES ('47', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":16,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '18', '1', '10000', 'admin', null, '10000', '2025-06-20 14:47:49');
INSERT INTO `sys_log_operate` VALUES ('48', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '19', '1', '10000', 'admin', null, '10000', '2025-06-20 14:48:09');
INSERT INTO `sys_log_operate` VALUES ('49', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":91,\"pid\":70,\"children\":[],\"name\":\"收入/支出单据查询\",\"url\":\"settlement/report/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":17,\"createTime\":\"2025-06-20 14:47:04\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '19', '1', '10000', 'admin', null, '10000', '2025-06-20 14:48:29');
INSERT INTO `sys_log_operate` VALUES ('50', '菜单管理', '保存', '/sys/menu', 'POST', '{\"vo\":{\"id\":null,\"pid\":70,\"children\":[{\"id\":89,\"pid\":70,\"children\":[],\"name\":\"预付款单\",\"url\":\"settlement/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":0,\"createTime\":\"2025-06-18 15:16:19\",\"parentName\":null},{\"id\":90,\"pid\":70,\"children\":[],\"name\":\"预收款单\",\"url\":\"settlement/reReceivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":1,\"createTime\":\"2025-06-18 15:17:01\",\"parentName\":null},{\"id\":71,\"pid\":70,\"children\":[],\"name\":\"付款单\",\"url\":\"settlement/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":2,\"createTime\":\"2025-05-23 15:10:13\",\"parentName\":null},{\"id\":72,\"pid\":70,\"children\":[],\"name\":\"收款单\",\"url\":\"settlement/receivePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":3,\"createTime\":\"2025-05-23 15:11:53\",\"parentName\":null},{\"id\":85,\"pid\":70,\"children\":[],\"name\":\"支出单\",\"url\":\"settlement/expend/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-creditcard\",\"authority\":\"\",\"sort\":8,\"createTime\":\"2025-06-04 19:45:30\",\"parentName\":null},{\"id\":84,\"pid\":70,\"children\":[],\"name\":\"收入单\",\"url\":\"settlement/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-moneycollect\",\"authority\":\"\",\"sort\":9,\"createTime\":\"2025-06-04 19:45:10\",\"parentName\":null},{\"id\":81,\"pid\":70,\"children\":[],\"name\":\"收/付款单据查询\",\"url\":\"settlement/report/payment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":15,\"createTime\":\"2025-05-28 11:26:40\",\"parentName\":null},{\"id\":91,\"pid\":70,\"children\":[],\"name\":\"收入/支出单据查询\",\"url\":\"settlement/report/income/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":17,\"createTime\":\"2025-06-20 14:47:04\",\"parentName\":null}],\"name\":\"预付/收款单据查询\",\"url\":\"settlement/report/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"\",\"authority\":\"\",\"sort\":18,\"createTime\":\"2025-05-23 15:07:03\",\"parentName\":\"\"}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '5', '1', '10000', 'admin', null, '10000', '2025-06-20 15:07:51');
INSERT INTO `sys_log_operate` VALUES ('51', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":92,\"pid\":70,\"children\":[],\"name\":\"预付/收款单据查询\",\"url\":\"settlement/report/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":18,\"createTime\":\"2025-06-20 15:07:49\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '10', '1', '10000', 'admin', null, '10000', '2025-06-20 15:08:31');
INSERT INTO `sys_log_operate` VALUES ('52', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":29,\"documentCode\":\"ZCD2025062000008\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/20 15:09:46\",\"deliveryTime\":null,\"userId\":10001,\"realName\":null,\"documentStatus\":\"2\",\"reviewStatus\":null,\"documentType\":\"36\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":22,\"amountType\":\"2\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":[{\"id\":null,\"documentId\":29,\"documentCode\":\"ZCD2025062000008\",\"barcode\":\"\",\"projectId\":3,\"productId\":null,\"productName\":null,\"productNumber\":null,\"quantity\":null,\"unitId\":null,\"unitName\":null,\"storeId\":null,\"inStoreId\":null,\"storeName\":null,\"inventoryNum\":null,\"unitPrice\":null,\"discountRate\":null,\"taxRate\":null,\"amount\":22,\"imgList\":null}],\"documentAccountDetailList\":[{\"id\":null,\"documentId\":29,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"\",\"amount\":22,\"amountType\":\"2\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":29,\"documentCode\":\"ZCD2025062000008\",\"paymentAmount\":22,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":\"2\"}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '89', '1', '10000', 'admin', null, '10000', '2025-06-20 15:10:04');
INSERT INTO `sys_log_operate` VALUES ('53', '菜单管理', '修改', '/sys/menu', 'PUT', '{\"vo\":{\"id\":92,\"pid\":70,\"children\":[],\"name\":\"预付/收款单据查询\",\"url\":\"settlement/report/rePayment/index\",\"type\":0,\"openStyle\":0,\"icon\":\"icon-item\",\"authority\":\"\",\"sort\":14,\"createTime\":\"2025-06-20 15:07:49\",\"parentName\":null}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '3', '22', '1', '10000', 'admin', null, '10000', '2025-06-20 15:10:34');
INSERT INTO `sys_log_operate` VALUES ('54', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":30,\"documentCode\":\"SRD2025062000014\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/20 15:54:45\",\"deliveryTime\":null,\"userId\":null,\"realName\":null,\"documentStatus\":\"2\",\"reviewStatus\":null,\"documentType\":\"37\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"1\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":[{\"id\":null,\"documentId\":30,\"documentCode\":\"SRD2025062000014\",\"barcode\":\"\",\"projectId\":2,\"projectName\":null,\"productId\":null,\"productName\":null,\"productNumber\":null,\"quantity\":null,\"unitId\":null,\"unitName\":null,\"storeId\":null,\"inStoreId\":null,\"storeName\":null,\"inventoryNum\":null,\"unitPrice\":null,\"discountRate\":null,\"taxRate\":null,\"amount\":null,\"imgList\":null}],\"documentAccountDetailList\":[{\"id\":null,\"documentId\":30,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":1,\"accountName\":\"\",\"amount\":11,\"amountType\":\"1\",\"remark\":\"\"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":30,\"documentCode\":\"SRD2025062000014\",\"paymentAmount\":11,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":\"1\"}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '63', '1', '10000', 'admin', null, '10000', '2025-06-20 15:54:53');
INSERT INTO `sys_log_operate` VALUES ('55', '单据', '保存', '/order/grDocument', 'POST', '{\"vo\":{\"id\":31,\"documentCode\":\"SKD2025062000015\",\"contactunitsId\":24,\"contactunitsName\":null,\"documentTime\":\"2025/06/20 16:26:00\",\"deliveryTime\":null,\"userId\":null,\"realName\":null,\"documentStatus\":\"1\",\"reviewStatus\":null,\"documentType\":\"34\",\"totalPrice\":null,\"discountedRate\":null,\"discountAmount\":null,\"finalAmount\":0,\"amountType\":\"1\",\"pendingAmount\":null,\"historyPayAmount\":null,\"historyReceivePayment\":null,\"advancePaymentAmount\":null,\"creatorName\":null,\"documentDetailList\":null,\"documentAccountDetailList\":[{\"id\":null,\"documentId\":31,\"documentSettleId\":null,\"settleDocumentCode\":null,\"accountId\":2,\"accountName\":\"2\",\"amount\":11,\"amountType\":\"1\",\"remark\":\"beizh \"}],\"documentSettleDetailList\":[{\"id\":null,\"documentId\":31,\"documentCode\":\"XSCK2025061300002\",\"paymentAmount\":null,\"advanceAmount\":null,\"discountAmount\":null,\"amountType\":\"1\"}]}}', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36', '2', '32', '1', '10000', 'admin', null, '10000', '2025-06-20 16:26:15');

-- ----------------------------
-- Table structure for sys_mail_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail_config`;
CREATE TABLE `sys_mail_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform` tinyint(4) DEFAULT NULL COMMENT '平台类型  -1：本地   0：阿里云',
  `group_name` varchar(100) DEFAULT NULL COMMENT '分组名称，发送邮件时，可指定分组',
  `mail_host` varchar(100) DEFAULT NULL COMMENT 'SMTP服务器',
  `mail_port` int(11) DEFAULT NULL COMMENT 'SMTP端口',
  `mail_from` varchar(100) DEFAULT NULL COMMENT '发件人邮箱',
  `mail_pass` varchar(100) DEFAULT NULL COMMENT '发件人密码',
  `region_id` varchar(100) DEFAULT NULL COMMENT 'regionId',
  `endpoint` varchar(100) DEFAULT NULL COMMENT '阿里云 endpoint',
  `access_key` varchar(100) DEFAULT NULL COMMENT 'AccessKey',
  `secret_key` varchar(100) DEFAULT NULL COMMENT 'SecretKey',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：启用',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件平台';

-- ----------------------------
-- Records of sys_mail_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_mail_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail_log`;
CREATE TABLE `sys_mail_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform_id` bigint(20) DEFAULT NULL COMMENT '平台ID',
  `platform` tinyint(4) DEFAULT NULL COMMENT '平台类型',
  `mail_from` varchar(100) DEFAULT NULL COMMENT '发件人邮箱',
  `mail_tos` varchar(1000) DEFAULT NULL COMMENT '接受人邮箱',
  `subject` varchar(200) DEFAULT NULL COMMENT '邮件主题',
  `content` text COMMENT '邮件内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：失败   1：成功',
  `error` varchar(2000) DEFAULT NULL COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件日志';

-- ----------------------------
-- Records of sys_mail_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `authority` varchar(500) DEFAULT NULL COMMENT '授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型   0：菜单   1：按钮   2：接口',
  `open_style` tinyint(4) DEFAULT NULL COMMENT '打开方式   0：内部   1：外部',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '系统设置', null, null, '0', '0', 'icon-setting', '6', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-05-23 15:26:49');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', 'sys/menu/index', null, '0', '0', 'icon-menu', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('3', '2', '查看', '', 'sys:menu:list', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('4', '2', '新增', '', 'sys:menu:save', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('5', '2', '修改', '', 'sys:menu:update,sys:menu:info', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('6', '2', '删除', '', 'sys:menu:delete', '1', '0', '', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('7', '1', '数据字典', 'sys/dict/type', '', '0', '0', 'icon-insertrowabove', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('8', '7', '查询', '', 'sys:dict:page', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('9', '7', '新增', '', 'sys:dict:save', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('10', '7', '修改', '', 'sys:dict:update,sys:dict:info', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('11', '7', '删除', '', 'sys:dict:delete', '1', '0', '', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('12', '58', '权限管理', '', '', '0', '0', 'icon-safetycertificate', '8', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-05-24 13:53:03');
INSERT INTO `sys_menu` VALUES ('13', '12', '岗位管理', 'sys/post/index', '', '0', '0', 'icon-solution', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('14', '13', '查询', '', 'sys:post:page', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('15', '13', '新增', '', 'sys:post:save', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('16', '13', '修改', '', 'sys:post:update,sys:post:info', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('17', '13', '删除', '', 'sys:post:delete', '1', '0', '', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('18', '58', '机构管理', 'sys/org/index', '', '0', '0', 'icon-cluster', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-03-16 19:16:00');
INSERT INTO `sys_menu` VALUES ('19', '18', '查询', '', 'sys:org:list', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('20', '18', '新增', '', 'sys:org:save', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('21', '18', '修改', '', 'sys:org:update,sys:org:info', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('22', '18', '删除', '', 'sys:org:delete', '1', '0', '', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('23', '12', '角色管理', 'sys/role/index', '', '0', '0', 'icon-team', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('24', '23', '查询', '', 'sys:role:page', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('25', '23', '新增', '', 'sys:role:save,sys:role:menu,sys:org:list', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('26', '23', '修改', '', 'sys:role:update,sys:role:info,sys:role:menu,sys:org:list,sys:user:page', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('27', '23', '删除', '', 'sys:role:delete', '1', '0', '', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('28', '58', '用户管理', 'sys/user/index', '', '0', '0', 'icon-user', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-03-16 19:16:42');
INSERT INTO `sys_menu` VALUES ('29', '28', '查询', '', 'sys:user:page', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('30', '28', '新增', '', 'sys:user:save,sys:role:list', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('31', '28', '修改', '', 'sys:user:update,sys:user:info,sys:role:list', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('32', '28', '删除', '', 'sys:user:delete', '1', '0', '', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('33', null, '应用管理', '', '', '0', '0', 'icon-appstore', '6', '0', '1', '10000', '2025-02-23 22:45:54', '10000', '2025-04-23 21:48:35');
INSERT INTO `sys_menu` VALUES ('34', '58', '日志管理', '', '', '0', '0', 'icon-filedone', '7', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-05-24 13:53:25');
INSERT INTO `sys_menu` VALUES ('35', '34', '登录日志', 'sys/log/login', 'sys:log:login', '0', '0', 'icon-solution', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('36', '28', '导入', '', 'sys:user:import', '1', '0', '', '5', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('37', '28', '导出', '', 'sys:user:export', '1', '0', '', '6', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('38', '1', '参数管理', 'sys/params/index', 'sys:params:all', '0', '0', 'icon-filedone', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('39', '1', '接口文档', '{{apiUrl}}/doc.html', null, '0', '1', 'icon-file-text-fill', '10', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('40', '34', '操作日志', 'sys/log/operate', 'sys:operate:all', '0', '0', 'icon-file-text', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('41', '1', '系统配置', 'sys/config/index', null, '0', '0', 'icon-safetycertificate', '4', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('42', '41', '短信配置', '', 'sys:sms:config', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('43', '41', '邮件配置', '', 'sys:mail:config', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('44', '41', '第三方登录', '', 'sys:third:config', '1', '0', '', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('45', '1', '基础工具', '', '', '0', '0', 'icon-wrench-fill', '8', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-05-24 13:52:22');
INSERT INTO `sys_menu` VALUES ('46', '45', '短信发送', 'sys/tool/sms/index', 'sys:sms:log', '0', '0', 'icon-message', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('47', '45', '邮件发送', 'sys/tool/mail/index', 'sys:mail:log', '0', '0', 'icon-mail', '2', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('48', '45', '附件管理', 'sys/attachment/index', null, '0', '0', 'icon-folder-fill', '3', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('49', '48', '查看', '', 'sys:attachment:page', '1', '0', '', '0', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('50', '48', '上传', '', 'sys:attachment:save', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('51', '48', '删除', '', 'sys:attachment:delete', '1', '0', '', '1', '0', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-02-23 22:45:54');
INSERT INTO `sys_menu` VALUES ('53', null, '商品', '', '', '0', '0', 'icon-shopping-fill', '0', '0', '1', '10000', '2025-02-23 22:50:36', '10000', '2025-04-19 19:49:11');
INSERT INTO `sys_menu` VALUES ('54', '53', '商品资料', '', '', '0', '0', 'icon-file-text-fill', '0', '0', '1', '10000', '2025-02-23 22:59:12', '10000', '2025-04-19 19:49:06');
INSERT INTO `sys_menu` VALUES ('55', '59', '商品', 'product/product/index', '', '0', '0', 'icon-shopping', '0', '0', '0', '10000', '2025-02-23 23:00:10', '10000', '2025-04-19 19:48:41');
INSERT INTO `sys_menu` VALUES ('56', '59', '类别', 'product/category/index', '', '0', '0', 'icon-unorderedlist', '3', '0', '0', '10000', '2025-02-23 23:03:27', '10000', '2025-05-31 21:18:12');
INSERT INTO `sys_menu` VALUES ('57', '59', '供应商 / 客户', 'product/contactunits/index', '', '0', '0', 'icon-bulb', '5', '0', '0', '10000', '2025-02-23 23:05:48', '10000', '2025-06-19 17:34:35');
INSERT INTO `sys_menu` VALUES ('58', null, '系统管理', '', '', '0', '0', 'icon-setting', '7', '0', '0', '10000', '2025-03-16 19:02:52', '10000', '2025-05-23 15:35:18');
INSERT INTO `sys_menu` VALUES ('59', null, '资料', '', '', '0', '0', 'icon-detail', '5', '0', '0', '10000', '2025-03-16 19:05:30', '10000', '2025-05-23 15:27:17');
INSERT INTO `sys_menu` VALUES ('60', '58', '用户管理', '', '', '0', '0', 'icon-post', '0', '0', '1', '10000', '2025-03-16 19:13:02', '10000', '2025-03-16 19:15:23');
INSERT INTO `sys_menu` VALUES ('61', '59', '仓库', 'product/store/index', '', '0', '0', 'icon-bank', '2', '0', '0', '10000', '2025-03-26 13:45:08', '10000', '2025-06-19 17:35:28');
INSERT INTO `sys_menu` VALUES ('62', '59', '计量单位', 'product/unit/index', '', '0', '0', 'icon-gold', '1', '0', '0', '10000', '2025-03-26 14:43:04', '10000', '2025-06-19 17:56:35');
INSERT INTO `sys_menu` VALUES ('63', '59', '品牌', 'product/brand/index', '', '0', '0', 'icon-safetycertificate', '4', '0', '0', '10000', '2025-03-27 22:03:11', '10000', '2025-05-31 21:18:21');
INSERT INTO `sys_menu` VALUES ('64', null, '采购', '', '', '0', '0', 'icon-shopping', '0', '0', '0', '10000', '2025-04-03 21:12:35', '10000', '2025-05-23 15:23:36');
INSERT INTO `sys_menu` VALUES ('65', '64', '采购入库单', 'purchase/order/index', '', '0', '0', 'icon-bank', '0', '0', '0', '10000', '2025-04-03 21:13:23', '10000', '2025-06-09 13:39:33');
INSERT INTO `sys_menu` VALUES ('66', '65', '新增购货', '', '', '1', '0', '', '0', '0', '1', '10000', '2025-04-05 21:12:05', '10000', '2025-06-13 12:56:31');
INSERT INTO `sys_menu` VALUES ('67', '59', '结算账号', 'product/settlementAccount/index', '', '0', '0', 'icon-creditcard', '7', '0', '0', '10000', '2025-04-06 14:22:29', '10000', '2025-05-31 21:17:13');
INSERT INTO `sys_menu` VALUES ('68', '64', '采购退货单', 'purchase/return/index', '', '0', '0', 'icon-left-square-fill', '1', '0', '0', '10000', '2025-04-13 09:39:45', '10000', '2025-06-05 17:15:15');
INSERT INTO `sys_menu` VALUES ('69', '64', '采购单据查询', 'purchase/report/index', '', '0', '0', 'icon-item', '2', '0', '0', '10000', '2025-04-13 10:00:25', '10000', '2025-05-24 13:54:25');
INSERT INTO `sys_menu` VALUES ('70', null, '财务', '', '', '0', '0', 'icon-YUAN', '3', '0', '0', '10000', '2025-05-23 15:07:03', '10000', '2025-05-23 15:36:57');
INSERT INTO `sys_menu` VALUES ('71', '70', '付款单', 'settlement/payment/index', '', '0', '0', 'icon-creditcard', '2', '0', '0', '10000', '2025-05-23 15:10:13', '10000', '2025-06-04 19:46:47');
INSERT INTO `sys_menu` VALUES ('72', '70', '收款单', 'settlement/receivePayment/index', '', '0', '0', 'icon-moneycollect', '3', '0', '0', '10000', '2025-05-23 15:11:53', '10000', '2025-06-04 19:47:01');
INSERT INTO `sys_menu` VALUES ('73', null, '销售', '', '', '0', '0', 'icon-shop', '1', '0', '0', '10000', '2025-05-23 15:25:32', '10000', '2025-05-23 15:35:54');
INSERT INTO `sys_menu` VALUES ('74', null, '库存', '', '', '0', '0', 'icon-bank', '2', '0', '0', '10000', '2025-05-23 15:26:28', '10000', '2025-05-23 15:37:03');
INSERT INTO `sys_menu` VALUES ('75', '73', '销售出库单', 'sale/salesOutbound/index', '', '0', '0', 'icon-vertical-align-top', '0', '0', '0', '10000', '2025-05-23 15:38:54', '10000', '2025-06-05 17:15:23');
INSERT INTO `sys_menu` VALUES ('76', '73', '销售退货单', 'sale/return/index', '', '0', '0', 'icon-vertical-align-botto', '1', '0', '0', '10000', '2025-05-23 15:39:05', '10000', '2025-06-05 17:15:28');
INSERT INTO `sys_menu` VALUES ('77', '73', '销售单据查询', 'sale/report/index', '', '0', '0', 'icon-item', '2', '0', '0', '10000', '2025-05-23 15:39:20', '10000', '2025-06-03 14:04:56');
INSERT INTO `sys_menu` VALUES ('78', '74', '调拨单', 'stock/allot/index', '', '0', '0', 'icon-sync', '0', '0', '0', '10000', '2025-05-23 15:39:58', '10000', '2025-06-06 08:55:18');
INSERT INTO `sys_menu` VALUES ('79', '74', '其他入库单', 'stock/otherIn/index', '', '0', '0', 'icon-vertical-align-botto', '0', '0', '0', '10000', '2025-05-23 15:40:26', '10000', '2025-06-06 12:29:18');
INSERT INTO `sys_menu` VALUES ('80', '74', '其他出库单', 'stock/otherOut/index', '', '0', '0', 'icon-vertical-align-top', '0', '0', '0', '10000', '2025-05-23 15:40:40', '10000', '2025-06-06 12:39:40');
INSERT INTO `sys_menu` VALUES ('81', '70', '收/付款单据查询', 'settlement/report/payment/index', '', '0', '0', 'icon-item', '15', '0', '0', '10000', '2025-05-28 11:26:40', '10000', '2025-06-20 14:43:12');
INSERT INTO `sys_menu` VALUES ('82', '74', '库存状况查询', 'stock/report/inventoryStatus/index', '', '0', '0', 'icon-item', '8', '0', '0', '10000', '2025-05-29 09:59:11', '10000', '2025-06-04 20:01:13');
INSERT INTO `sys_menu` VALUES ('83', '59', '客户', 'product/customer/index', '', '0', '0', 'icon-team', '6', '0', '1', '10000', '2025-05-31 19:04:45', '10000', '2025-06-04 12:32:19');
INSERT INTO `sys_menu` VALUES ('84', '70', '收入单', 'settlement/income/index', '', '0', '0', 'icon-moneycollect', '9', '0', '0', '10000', '2025-06-04 19:45:10', '10000', '2025-06-18 15:21:40');
INSERT INTO `sys_menu` VALUES ('85', '70', '支出单', 'settlement/expend/index', '', '0', '0', 'icon-creditcard', '8', '0', '0', '10000', '2025-06-04 19:45:30', '10000', '2025-06-18 15:21:33');
INSERT INTO `sys_menu` VALUES ('86', '74', '新增盘点', '', '', '0', '0', 'icon-Field-time', '4', '0', '1', '10000', '2025-06-04 20:06:02', '10000', '2025-06-09 09:07:25');
INSERT INTO `sys_menu` VALUES ('87', '59', '收支项目', 'product/incomeExpenditure/index', '', '0', '0', 'icon-book', '10', '0', '0', '10000', '2025-06-05 12:11:06', '10000', '2025-06-05 13:07:33');
INSERT INTO `sys_menu` VALUES ('88', '74', '库存变动单据查询', 'stock/report/change/index', '', '0', '0', 'icon-item', '3', '0', '0', '10000', '2025-06-17 10:56:23', '10000', '2025-06-17 11:07:13');
INSERT INTO `sys_menu` VALUES ('89', '70', '预付款单', 'settlement/rePayment/index', '', '0', '0', 'icon-creditcard', '0', '0', '0', '10000', '2025-06-18 15:16:19', '10000', '2025-06-20 14:48:06');
INSERT INTO `sys_menu` VALUES ('90', '70', '预收款单', 'settlement/reReceivePayment/index', '', '0', '0', 'icon-moneycollect', '1', '0', '0', '10000', '2025-06-18 15:17:01', '10000', '2025-06-18 15:20:25');
INSERT INTO `sys_menu` VALUES ('91', '70', '收入/支出单据查询', 'settlement/report/income/index', '', '0', '0', 'icon-item', '17', '0', '0', '10000', '2025-06-20 14:47:04', '10000', '2025-06-20 14:48:25');
INSERT INTO `sys_menu` VALUES ('92', '70', '预付/收款单据查询', 'settlement/report/rePayment/index', '', '0', '0', 'icon-item', '14', '0', '0', '10000', '2025-06-20 15:07:49', '10000', '2025-06-20 15:10:28');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `leader_id` bigint(20) DEFAULT NULL COMMENT '负责人ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='机构管理';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', null, '总公司', '0', null, '10000', '0', '0', '10000', '2025-03-05 21:54:09', '10000', '2025-03-05 21:54:09');
INSERT INTO `sys_org` VALUES ('2', '1', '上海', '1', null, '10000', '0', '0', '10000', '2025-03-12 22:28:45', '10000', '2025-03-12 22:28:45');
INSERT INTO `sys_org` VALUES ('3', '1', '南京', '0', null, '10000', '0', '0', '10000', '2025-03-27 12:34:20', '10000', '2025-03-27 12:34:20');

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `param_name` varchar(100) DEFAULT NULL COMMENT '参数名称',
  `param_type` tinyint(4) NOT NULL COMMENT '系统参数   0：否   1：是',
  `param_key` varchar(100) DEFAULT NULL COMMENT '参数键',
  `param_value` varchar(2000) DEFAULT NULL COMMENT '参数值',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='参数管理';

-- ----------------------------
-- Records of sys_params
-- ----------------------------
INSERT INTO `sys_params` VALUES ('1', '用户登录-验证码开关', '1', 'LOGIN_CAPTCHA', 'false', '是否开启验证码（true：开启，false：关闭）', '10000', '0', '0', '10000', '2025-02-23 22:45:55', '10000', '2025-02-23 22:45:55');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `post_code` varchar(100) DEFAULT NULL COMMENT '岗位编码',
  `post_name` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='岗位管理';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', '001', '岗位测试', '0', '1', '10000', '0', '0', '10000', '2025-04-18 23:21:35', '10000', '2025-04-18 23:21:35');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint(4) DEFAULT NULL COMMENT '数据范围  0：全部数据  1：本机构及子机构数据  2：本机构数据  3：本人数据  4：自定义数据',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色管理';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_data_scope`;
CREATE TABLE `sys_role_data_scope` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色数据权限';

-- ----------------------------
-- Records of sys_role_data_scope
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_sms_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_config`;
CREATE TABLE `sys_sms_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform` tinyint(4) DEFAULT NULL COMMENT '平台类型  0：阿里云   1：腾讯云   2：七牛云   3：华为云',
  `group_name` varchar(100) DEFAULT NULL COMMENT '分组名称，发送短信时，可指定分组',
  `sign_name` varchar(100) DEFAULT NULL COMMENT '短信签名',
  `template_id` varchar(100) DEFAULT NULL COMMENT '短信模板',
  `app_id` varchar(100) DEFAULT NULL COMMENT '短信应用ID，如：腾讯云等',
  `sender_id` varchar(100) DEFAULT NULL COMMENT '腾讯云国际短信、华为云等需要',
  `url` varchar(200) DEFAULT NULL COMMENT '接入地址，如：华为云',
  `access_key` varchar(100) DEFAULT NULL COMMENT 'AccessKey',
  `secret_key` varchar(100) DEFAULT NULL COMMENT 'SecretKey',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：启用',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='短信配置';

-- ----------------------------
-- Records of sys_sms_config
-- ----------------------------
INSERT INTO `sys_sms_config` VALUES ('1', '0', '11', '111', '11', '', '', '', '11', '111', '0', '0', '0', '10000', '2025-03-15 14:31:50', '10000', '2025-03-15 14:31:50');

-- ----------------------------
-- Table structure for sys_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_log`;
CREATE TABLE `sys_sms_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform_id` bigint(20) DEFAULT NULL COMMENT '平台ID',
  `platform` tinyint(4) DEFAULT NULL COMMENT '平台类型',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `params` varchar(200) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：失败   1：成功',
  `error` varchar(2000) DEFAULT NULL COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信日志';

-- ----------------------------
-- Records of sys_sms_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_third_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_login`;
CREATE TABLE `sys_third_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `open_type` varchar(50) DEFAULT NULL COMMENT '开放平台类型',
  `open_id` varchar(100) DEFAULT NULL COMMENT '开放平台，唯一标识',
  `username` varchar(100) DEFAULT NULL COMMENT '昵称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方登录';

-- ----------------------------
-- Records of sys_third_login
-- ----------------------------

-- ----------------------------
-- Table structure for sys_third_login_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_login_config`;
CREATE TABLE `sys_third_login_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `open_type` varchar(50) DEFAULT NULL COMMENT '开放平台类型',
  `client_id` varchar(200) DEFAULT NULL COMMENT 'ClientID',
  `client_secret` varchar(200) DEFAULT NULL COMMENT 'ClientSecret',
  `redirect_uri` varchar(200) DEFAULT NULL COMMENT 'RedirectUri',
  `agent_id` varchar(200) DEFAULT NULL COMMENT 'AgentID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='第三方登录配置';

-- ----------------------------
-- Records of sys_third_login_config
-- ----------------------------
INSERT INTO `sys_third_login_config` VALUES ('1', 'feishu', 'cli_a541d3aa03f8500b', '5Chz39zvEhZtxSVZz3vLjfQHdkvavQaH', 'http://localhost:8080/sys/third/callback/feishu', '', '10000', '0', '0', '2025-02-23 22:45:55');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `super_admin` tinyint(4) DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8mb4 COMMENT='用户管理';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10000', 'admin', 'dc1fd00e3eeeb940ff46f457bf97d66ba7fcc36e0b20802383de142860e76ae6', 'admin', 'http://localhost:8080/upload/20250429/屏幕截图 2025-04-22 233543_72159.png', '0', '1161095164@qq.com', '18221078462', null, '1', '1', '10000', '2', '0', '10000', '2025-02-23 22:45:54', '10000', '2025-06-06 12:50:47');
INSERT INTO `sys_user` VALUES ('10001', '测试1', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '测试啊', null, '0', '', '11111111111', '2', '0', '1', null, '0', '0', '10000', '2025-03-19 22:25:53', '10000', '2025-03-19 22:25:53');
INSERT INTO `sys_user` VALUES ('10002', '小刚', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '小刚', null, '0', '', '1212', '1', '0', '1', null, '0', '0', '10000', '2025-06-10 12:51:08', '10000', '2025-06-10 12:51:08');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `post_id` bigint(20) DEFAULT NULL COMMENT '岗位ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_post_id` (`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户岗位关系';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `access_token` varchar(32) DEFAULT NULL COMMENT 'accessToken',
  `access_token_expire` datetime DEFAULT NULL COMMENT 'accessToken 过期时间',
  `refresh_token` varchar(32) DEFAULT NULL COMMENT 'refreshToken',
  `refresh_token_expire` datetime DEFAULT NULL COMMENT 'refreshToken 过期时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '10000', '7ae2bb421aad480b94fc1c3c9c505b7d', '2025-06-20 22:33:53', '5ecbea903248428cbe1896a27d392b1b', '2025-07-04 14:33:53', null, '2025-02-23 23:55:19');
