/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 26/06/2019 11:18:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秘钥',
  `access_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'access_token',
  `token_expires_in` int(13) NULL DEFAULT NULL COMMENT 'token有效期至',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '状态，0为正常，默认0',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`aid`, `user_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '1', 'admin', '12345678', '1234', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE0NDU5MTIsInVzZXJuYW1lIjoiYWRtaW4ifQ.kaBw0KN9jeP6yKzkjNBCwFbsW7QbUN6FzFsHKw7ugII', 123, '2019-06-23 17:53:38', b'0', '2019-06-25 06:53:32');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `aid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `category_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态，0-正常（发布），1-删除，2-记录（待发布）',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `html_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '生成的html',
  `cover` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '封面图',
  `sub_message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章简介',
  `pageview` int(11) NULL DEFAULT 0 COMMENT '文章阅读数',
  `is_encrypt` bit(1) NULL DEFAULT b'0' COMMENT '是否加密，0否，1是，默认0',
  PRIMARY KEY (`aid`, `id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_tag_mapper
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_mapper`;
CREATE TABLE `article_tag_mapper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_config
-- ----------------------------
DROP TABLE IF EXISTS `blog_config`;
CREATE TABLE `blog_config`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `blog_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客名称',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `sign` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个性签名',
  `wxpay_qrcode` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '微信支付二维码',
  `alipay_qrcode` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付宝支付二维码',
  `github` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'github',
  `view_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阅读加密密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阅读加密秘钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_config
-- ----------------------------
INSERT INTO `blog_config` VALUES (1, 'northcity', '', '', '', '', 'jhdhsdsdsds', '', NULL);
INSERT INTO `blog_config` VALUES (2, 'northcity', '', '', '', '', 'jhdhsdsdsds', '123456', NULL);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `aid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '分类id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '状态，0为正常，1为删除，默认0',
  `article_count` int(11) NULL DEFAULT 0 COMMENT '该分类的文章数量',
  `can_del` bit(1) NULL DEFAULT b'0' COMMENT '0表示不可删除，1表示可删除，默认1',
  PRIMARY KEY (`aid`, `id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '0', 'Blog', '2019-06-23 18:33:19', '2019-06-24 20:06:57', b'1', 0, b'1');
INSERT INTO `category` VALUES (1, '1', 'C++', '2019-06-23 18:33:21', '2019-06-24 20:05:57', b'1', 0, b'1');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `parent_id` int(10) NOT NULL DEFAULT 0 COMMENT '父id, 默认0',
  `reply_id` int(10) NULL DEFAULT NULL COMMENT '回复的评论id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者名称',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者邮箱',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容json',
  `source_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容（原始内容）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态，0正常，1删除，默认0',
  `is_author` bit(1) NULL DEFAULT b'0' COMMENT '是否是作者，0否，1是，默认0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `aid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `friend_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '友链名称',
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应链接',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '状态，0表示可用，1表示删除，默认0',
  `type_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属分类id',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (1, '1', 'JAVA', '123', '2019-06-25 09:44:12', NULL, NULL, b'0', 1);

-- ----------------------------
-- Table structure for friends_type
-- ----------------------------
DROP TABLE IF EXISTS `friends_type`;
CREATE TABLE `friends_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类名称',
  `count` int(11) NULL DEFAULT 0 COMMENT '该分类的友链数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends_type
-- ----------------------------
INSERT INTO `friends_type` VALUES (1, '普通', 1);
INSERT INTO `friends_type` VALUES (2, '尊贵', 0);

-- ----------------------------
-- Table structure for pages
-- ----------------------------
DROP TABLE IF EXISTS `pages`;
CREATE TABLE `pages`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '页面名称',
  `md` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'markdown内容',
  `html` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '生成的html内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `time` datetime(0) NOT NULL COMMENT '时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日志内容',
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端IP地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标签id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '状态，0表示正常，1表示删除，默认0',
  `article_count` int(11) NULL DEFAULT 0 COMMENT '该标签的文章数量',
  PRIMARY KEY (`aid`, `id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '0', 'Python', '2019-06-24 08:10:33', '2019-06-24 19:08:57', b'1', 0);
INSERT INTO `tag` VALUES (1, '1', 'fggf', '2019-06-24 08:10:37', NULL, b'1', 0);
INSERT INTO `tag` VALUES (1, '2', 'ddsdsds', '2019-06-24 08:10:41', '2019-06-24 20:07:02', b'1', 0);
INSERT INTO `tag` VALUES (1, '3', 'rererere', '2019-06-24 08:10:48', NULL, b'1', 0);
INSERT INTO `tag` VALUES (1, '4', 'ddsds', '2019-06-24 08:10:51', '2019-06-24 20:07:06', b'1', 0);
INSERT INTO `tag` VALUES (1, '5', 'dddsds', '2019-06-24 08:10:54', '2019-06-24 20:07:09', b'1', 0);
INSERT INTO `tag` VALUES (1, '6', 'dsdsdsds', '2019-06-24 08:10:58', '2019-06-24 20:07:13', b'1', 0);

SET FOREIGN_KEY_CHECKS = 1;
