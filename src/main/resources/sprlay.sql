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

 Date: 23/01/2019 16:47:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `headImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像url',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `createTime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '', '管理员', '45@qq.com', '110', '男', '');
INSERT INTO `user` VALUES (2, '2', '2', '2', '2', '2', '2', '2', '2');
INSERT INTO `user` VALUES (3, '3', '3', '3', '3', '3', '3', '3', '3');
INSERT INTO `user` VALUES (4, '4', '4', '4', '4', '4', '4', '4', '4');
INSERT INTO `user` VALUES (5, '5', '5', '5', '5', '5', '5', '5', '5');
INSERT INTO `user` VALUES (6, '6', '6', '6', '6', '6', '6', '6', '6');
INSERT INTO `user` VALUES (7, '7', '7', '7', '7', '7', '7', '7', '7');
INSERT INTO `user` VALUES (8, '8', '8', '8', '8', '8', '8', '8', '8');
INSERT INTO `user` VALUES (9, '9', '9', '9', '9', '9', '9', '9', '9');
INSERT INTO `user` VALUES (10, '10', '10', '10', '10', '10', '10', '1', '10');
INSERT INTO `user` VALUES (11, '11', '11', '11', '11', '11', '11', '1', '11');
INSERT INTO `user` VALUES (12, '12', '12', '12', '12', '12', '12', '1', '12');
