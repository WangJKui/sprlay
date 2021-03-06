/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80014
 Source Host           : localhost:3306
 Source Schema         : sprlay

 Target Server Type    : MySQL
 Target Server Version : 80014
 File Encoding         : 65001

 Date: 29/03/2019 17:35:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '父id（顶级：0）',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uri` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `permission` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型（1:目录，2：菜单，3：按钮）',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态（0：禁止，1：正常）',
  `orderno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `ctime` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `innercode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '层级码 如：-0-1-',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_s_o`(`type`, `status`, `orderno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '水平导航', NULL, NULL, NULL, 1, 1, 1, NULL, NULL, '-0-1-');
INSERT INTO `menu` VALUES (2, 0, '垂直导航', NULL, NULL, NULL, 1, 1, 2, NULL, NULL, '-0-2-');
INSERT INTO `menu` VALUES (3, 1, '控制台', NULL, NULL, NULL, 1, 1, 3, NULL, NULL, '-0-1-3-');
INSERT INTO `menu` VALUES (4, 1, '商品管理', NULL, NULL, NULL, 1, 1, 1, NULL, NULL, '-0-1-4-');
INSERT INTO `menu` VALUES (5, 2, '系统管理', NULL, NULL, NULL, 1, 1, 5, NULL, NULL, '-0-2-5-');
INSERT INTO `menu` VALUES (6, 5, '用户管理', NULL, NULL, NULL, 2, 1, 6, NULL, NULL, '-0-2-5-6-');
INSERT INTO `menu` VALUES (7, 5, '角色管理', NULL, NULL, NULL, 2, 1, 7, NULL, NULL, '-0-2-5-7-');
INSERT INTO `menu` VALUES (8, 5, '资源管理', NULL, NULL, NULL, 2, 1, 8, NULL, NULL, '-0-2-5-8-');
INSERT INTO `menu` VALUES (9, 6, '查询用户', NULL, NULL, NULL, 3, 1, 9, NULL, NULL, '-0-2-5-6-9-');
INSERT INTO `menu` VALUES (10, 6, '添加用户', NULL, NULL, NULL, 3, 1, 10, NULL, NULL, '-0-2-5-6-10-');
INSERT INTO `menu` VALUES (11, 6, '编辑用户', NULL, NULL, NULL, 3, 1, 11, NULL, NULL, '-0-2-5-6-11-');
INSERT INTO `menu` VALUES (12, 6, '删除用户', NULL, NULL, NULL, 3, 1, 12, NULL, NULL, '-0-2-5-6-12-');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态（0：锁定，1：正常）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ctime` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 'superadmin', 1, '最高级', '2019-01-25 14:01:42');
INSERT INTO `role` VALUES (2, '管理员', 'admin', 1, '高级', '2019-01-25 14:01:42');
INSERT INTO `role` VALUES (3, '系统管理员', 'system', 1, '系统级', '2019-01-25 14:01:42');
INSERT INTO `role` VALUES (4, '开发者', 'developer', 1, '开发人员', '2019-01-25 14:01:42');
INSERT INTO `role` VALUES (6, '普通员工', 'normal', 1, '员工', '2019-03-27 11:03:46');
INSERT INTO `role` VALUES (9, '1', 'qwe1', 1, '1', '2019-03-28 16:34:54');
INSERT INTO `role` VALUES (10, '2', '2qwe', 1, '21', '2019-03-28 16:35:02');
INSERT INTO `role` VALUES (11, '3', '3qe', 1, '123', '2019-03-28 16:35:07');
INSERT INTO `role` VALUES (12, 'qwe', '13qwe', 1, '12', '2019-03-28 16:35:13');
INSERT INTO `role` VALUES (13, 'qew', '123wqe', 0, '123', '2019-03-28 16:35:19');
INSERT INTO `role` VALUES (14, '123123', '123wqe', 1, '', '2019-03-28 16:50:03');
INSERT INTO `role` VALUES (15, 'qwead', '23adeq', 1, 'eqewads', '2019-03-28 16:50:07');
INSERT INTO `role` VALUES (16, 'qwe', 'ads11', 1, 'qwe', '2019-03-28 16:50:13');
INSERT INTO `role` VALUES (17, 'ads', '312add', 1, 'ads', '2019-03-28 16:50:18');
INSERT INTO `role` VALUES (18, 'ad', 'adqe12da', 1, 'qea', '2019-03-28 16:50:23');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像url',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `ctime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态（0：锁定，1：正常）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '13ed682b7463d8f3b38f185369618fac', '', '管理员', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (13, 'wangjkui', '123456', NULL, '超级管理员', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (25, '25', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:41', 1);
INSERT INTO `user` VALUES (26, '26', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:42', 1);
INSERT INTO `user` VALUES (27, '27', '123456', NULL, '1371761114qwe1', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:43', 1);
INSERT INTO `user` VALUES (28, '28', '123456', NULL, '1371761111q41qw', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:43', 1);
INSERT INTO `user` VALUES (30, '30', '123456', NULL, 'qew123', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (31, '31', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (32, '32', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (33, '33', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (34, '34', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:44', 1);
INSERT INTO `user` VALUES (35, '35', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:45', 1);
INSERT INTO `user` VALUES (36, '36', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:45', 1);
INSERT INTO `user` VALUES (37, '37', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:45', 1);
INSERT INTO `user` VALUES (38, '38', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:45', 1);
INSERT INTO `user` VALUES (39, '39', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:45', 1);
INSERT INTO `user` VALUES (40, '40', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:45', 1);
INSERT INTO `user` VALUES (41, '41', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:46', 1);
INSERT INTO `user` VALUES (42, '42', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:46', 1);
INSERT INTO `user` VALUES (43, '43', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:46', 1);
INSERT INTO `user` VALUES (44, '44', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:46', 1);
INSERT INTO `user` VALUES (45, '45', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:56', 1);
INSERT INTO `user` VALUES (46, '46', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:56', 1);
INSERT INTO `user` VALUES (47, '47', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:56', 1);
INSERT INTO `user` VALUES (48, '48', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:56', 1);
INSERT INTO `user` VALUES (49, '49', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:56', 1);
INSERT INTO `user` VALUES (51, '51', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:57', 1);
INSERT INTO `user` VALUES (52, '52', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:57', 1);
INSERT INTO `user` VALUES (53, '53', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:01:57', 1);
INSERT INTO `user` VALUES (55, '55', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:05:57', 1);
INSERT INTO `user` VALUES (56, '56', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:05:58', 1);
INSERT INTO `user` VALUES (57, '57', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:05:59', 1);
INSERT INTO `user` VALUES (58, '58', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:00', 1);
INSERT INTO `user` VALUES (59, '59', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:00', 1);
INSERT INTO `user` VALUES (60, '60', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:00', 1);
INSERT INTO `user` VALUES (61, '61', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:04', 1);
INSERT INTO `user` VALUES (62, '62', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:04', 1);
INSERT INTO `user` VALUES (63, '63', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:05', 1);
INSERT INTO `user` VALUES (64, '64', '123456', NULL, '453091806@qq.om', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:06:54', 1);
INSERT INTO `user` VALUES (65, '65', '123456', NULL, '453091806@qq.om', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:07:24', 1);
INSERT INTO `user` VALUES (66, '66', '123456', NULL, '453091806@qq.om', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:07:39', 1);
INSERT INTO `user` VALUES (67, '67', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:08:07', 1);
INSERT INTO `user` VALUES (68, '68', '123456', NULL, '453091806@qq.om', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:10:10', 1);
INSERT INTO `user` VALUES (69, '69', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:11:32', 1);
INSERT INTO `user` VALUES (70, '70', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:13:43', 1);
INSERT INTO `user` VALUES (71, '71', '123456', NULL, '453091806@qq.om', '453091806@qq.om', '13717611141', '男', '2019-01-25 14:14:18', 1);
INSERT INTO `user` VALUES (72, '72', '123456', NULL, '13717611141', '453091806@qq.om', '13717611141', '男', '2019-01-25 15:37:14', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (3, 1, 3);
INSERT INTO `user_role` VALUES (4, 1, 4);

-- ----------------------------
-- Procedure structure for pr_menu
-- ----------------------------
DROP PROCEDURE IF EXISTS `pr_menu`;
delimiter ;;
CREATE PROCEDURE `pr_menu`(IN ids INT)
BEGIN
	SELECT * FROM menu WHERE id = ids;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
