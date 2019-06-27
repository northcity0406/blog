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

 Date: 27/06/2019 14:45:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秘钥',
  `access_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'access_token',
  `token_expires_in` int(13) NULL DEFAULT NULL COMMENT 'token有效期至',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态，0为正常，默认0',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `admin` bit(1) NULL DEFAULT b'0' COMMENT '是否是Admin',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '12345678', '1234', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0OTcsInVzZXJuYW1lIjoiYWRtaW4ifQ.2NYH11IljHBfN9LWBmiNKVsaS8wLqKZ03Cboy-qyUxk', 123, '2019-06-23 17:53:38', 0, '2019-06-27 10:18:37', NULL, NULL, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE2MzEwMTcsInVzZXJuYW1lIjoiYWRtaW4ifQ.mlMG74tVBGSAPhIqEiLpsQO7pD6sRf_GGu52U_GzAdw');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `userID` int(16) UNSIGNED NOT NULL COMMENT '文章所属用户',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `categoryID` int(128) NOT NULL COMMENT '文章分类id',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态，0-正常（发布），1-删除，2-记录（待发布）',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `cover` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '封面图',
  `pageview` int(11) NOT NULL DEFAULT 0 COMMENT '文章阅读数',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `delete_time` datetime(0) NULL DEFAULT NULL,
  `html_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_encrypt` int(11) NULL DEFAULT NULL COMMENT '是否加密: 0-加密,1-不加密',
  `publish_time` datetime(0) NULL DEFAULT NULL,
  `sub_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 1, 'df', 1, 0, 'dfdffd', '', 0, '2019-06-27 12:05:58', NULL, '<p>dfdffd</p>\n', 1, NULL, 'fdf', NULL);
INSERT INTO `article` VALUES (2, 1, 'dssds', 1, 0, '![dift.png](2)', '', 0, '2019-06-27 12:09:23', NULL, '<p><img src=\"2\" alt=\"dift.png\"></p>\n', 1, NULL, 'dssdsd', NULL);
INSERT INTO `article` VALUES (3, 1, 'sasa', 1, 0, '![图片1.png](undefined)', '', 0, '2019-06-27 12:16:59', NULL, '<p><img src=\"undefined\" alt=\"图片1.png\"></p>\n', 1, NULL, 'sasa', NULL);
INSERT INTO `article` VALUES (4, 1, 're', 1, 0, '![jitter.png](undefined)', '', 0, '2019-06-27 12:18:29', NULL, '<p><img src=\"undefined\" alt=\"jitter.png\"></p>\n', 1, NULL, 'erer', NULL);

-- ----------------------------
-- Table structure for articletags
-- ----------------------------
DROP TABLE IF EXISTS `articletags`;
CREATE TABLE `articletags`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `articleID` int(16) NOT NULL COMMENT '文章id',
  `tagID` int(16) NOT NULL COMMENT '标签id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `articleID`(`articleID`) USING BTREE,
  INDEX `articleTag`(`tagID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_config
-- ----------------------------
DROP TABLE IF EXISTS `blog_config`;
CREATE TABLE `blog_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alipay_qrcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blog_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `github` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wxpay_qrcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_config
-- ----------------------------
INSERT INTO `blog_config` VALUES (1, '', '', 'dssdds', 'sddds', NULL, 'dsdsdsds', '', '');
INSERT INTO `blog_config` VALUES (2, '', '', 'dssdds', 'sdddsds', '', 'dsdsdsds', '', '');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类名称',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态，0为正常，1为删除，默认0',
  `articleCount` int(11) NULL DEFAULT 0 COMMENT '该分类的文章数量',
  `canDel` int(1) NULL DEFAULT 0 COMMENT '0表示不可删除，1表示可删除，默认1',
  `article_count` int(11) NULL DEFAULT NULL,
  `can_del` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Python', NULL, NULL, 0, 0, 0, 0, 0, '2019-06-26 14:03:53', NULL);
INSERT INTO `category` VALUES (2, 'JAVA', NULL, NULL, 0, 0, 0, 0, 0, '2019-06-26 14:05:51', NULL);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  `articleID` int(128) NOT NULL COMMENT '文章id',
  `parentID` int(10) NOT NULL DEFAULT 0 COMMENT '父id, 默认0',
  `replyID` int(10) NULL DEFAULT NULL COMMENT '回复的评论id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者名称',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者邮箱',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容json',
  `sourceContent` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容（原始内容）',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `deleteTime` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态，0正常，1删除，默认0',
  `is_author` int(1) NULL DEFAULT 0 COMMENT '是否是作者，0否，1是，默认0',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `delete_time` datetime(0) NULL DEFAULT NULL,
  `source_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article`(`articleID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` int(16) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '友链编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '友链名称',
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应链接',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态，0表示可用，1表示删除，默认0',
  `typeID` int(11) NOT NULL DEFAULT 0 COMMENT '所属分类id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `delete_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `friendType`(`typeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (2, 'JAVA', '{friendId: friendId}', 0, 1, '2019-06-26 14:32:19', NULL, NULL);

-- ----------------------------
-- Table structure for friends_type
-- ----------------------------
DROP TABLE IF EXISTS `friends_type`;
CREATE TABLE `friends_type`  (
  `id` int(11) NOT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends_type
-- ----------------------------
INSERT INTO `friends_type` VALUES (1, 0, '普通');
INSERT INTO `friends_type` VALUES (2, 0, '尊贵');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTcxODcsInVzZXJuYW1lIjoiYWRtaW4ifQ.Oqgl5EIw6464PwpjiqjLw-MLVCmLjoJJW6FYO_R1-8A\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:48:07.748, admin=null}', '127.0.0.1', '2019-06-26 13:48:08');
INSERT INTO `sys_log` VALUES (2, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTcyMTcsInVzZXJuYW1lIjoiYWRtaW4ifQ.ROflNVtQJyT3WQHR2hQtHHnWBDWAwsUbGJ227N3ouMI\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:48:37.083, admin=null}', '127.0.0.1', '2019-06-26 13:48:37');
INSERT INTO `sys_log` VALUES (3, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTcyNDQsInVzZXJuYW1lIjoiYWRtaW4ifQ.XsyR2ItNt-8rdJ4qsEs4LXepu4ci1HdP0p4R7m9zuXc\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:49:04.643, admin=null}', '127.0.0.1', '2019-06-26 13:49:05');
INSERT INTO `sys_log` VALUES (4, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTcyNTUsInVzZXJuYW1lIjoiYWRtaW4ifQ.oPWX3Jsthz3Cu8lhplj5M0OGEZeTiRwEBTy1-zp24Z8\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:49:15.004, admin=null}', '127.0.0.1', '2019-06-26 13:49:15');
INSERT INTO `sys_log` VALUES (5, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTcyNjAsInVzZXJuYW1lIjoiYWRtaW4ifQ.hGASTqrAIyZ5b8NM8wygEgtc_t6WaP1L8YzuzjrmD7Q\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:49:20.505, admin=null}', '127.0.0.1', '2019-06-26 13:49:21');
INSERT INTO `sys_log` VALUES (6, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTczNDIsInVzZXJuYW1lIjoiYWRtaW4ifQ.bzFdH9VEzlhwphEX_stcbLMLUaIYAi-Am5qyT1ye96k\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:50:42.691, admin=null}', '127.0.0.1', '2019-06-26 13:50:43');
INSERT INTO `sys_log` VALUES (7, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTczNDUsInVzZXJuYW1lIjoiYWRtaW4ifQ.SkWKhpso5sQSB1DIXhOrCRz0vwD_Xgg74YWRPG48RiE\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:50:45.597, admin=null}', '127.0.0.1', '2019-06-26 13:50:46');
INSERT INTO `sys_log` VALUES (8, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTczNTEsInVzZXJuYW1lIjoiYWRtaW4ifQ.u39PEu_N2vUV3F4zePV1pJlE45HASnX92OrAiCEsGlQ\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:50:51.746, admin=null}', '127.0.0.1', '2019-06-26 13:50:52');
INSERT INTO `sys_log` VALUES (9, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0MDAsInVzZXJuYW1lIjoiYWRtaW4ifQ.ie0gZZLjGFCghMe7W8o7-nn3gp8rNXTa_0nEhbjsrFw\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:51:40.169, admin=null}', '127.0.0.1', '2019-06-26 13:51:40');
INSERT INTO `sys_log` VALUES (10, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0MDMsInVzZXJuYW1lIjoiYWRtaW4ifQ.hqC0XQRaJ9r2Vqf-Vc0cbq-Hwnu8t01ulIQdJjYszSA\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:51:43.055, admin=null}', '127.0.0.1', '2019-06-26 13:51:43');
INSERT INTO `sys_log` VALUES (11, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0MDksInVzZXJuYW1lIjoiYWRtaW4ifQ.Wo8IDSwEFuqqCiJhZMdB3tWNIYDKOGN679h2SRpz6LM\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:51:49.702, admin=null}', '127.0.0.1', '2019-06-26 13:51:50');
INSERT INTO `sys_log` VALUES (12, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0MTIsInVzZXJuYW1lIjoiYWRtaW4ifQ.kUrxjIk9NmS8FsCrhajymLSMxs2dht3MNuWyFdx80dw\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:51:52.509, admin=null}', '127.0.0.1', '2019-06-26 13:51:53');
INSERT INTO `sys_log` VALUES (13, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0MzAsInVzZXJuYW1lIjoiYWRtaW4ifQ.-KD-HHJQaWxhc4Rt5gknA58WZxI6u0_yYr1WwIuevu0\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:52:10.759, admin=null}', '127.0.0.1', '2019-06-26 13:52:11');
INSERT INTO `sys_log` VALUES (14, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0MzUsInVzZXJuYW1lIjoiYWRtaW4ifQ.0NugSflvln7qsQ7C16_4y1jUqUug6kG5Z1ulGbuCgzQ\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:52:15.375, admin=null}', '127.0.0.1', '2019-06-26 13:52:15');
INSERT INTO `sys_log` VALUES (15, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0NzYsInVzZXJuYW1lIjoiYWRtaW4ifQ.9mi189tMD4gJzxVTfdaqOqR_qYCCKDfw7q1OUXUZ3o0\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:52:56.881, admin=null}', '127.0.0.1', '2019-06-26 13:52:57');
INSERT INTO `sys_log` VALUES (16, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0ODIsInVzZXJuYW1lIjoiYWRtaW4ifQ.RWd8tc-7VcOlblp-LsQGMlCBOQSKtlt1Ic_rmUG1RQg\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:53:02.022, admin=null}', '127.0.0.1', '2019-06-26 13:53:02');
INSERT INTO `sys_log` VALUES (17, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0ODgsInVzZXJuYW1lIjoiYWRtaW4ifQ.WjP9n9uKKdAdRjHosn_wkMnYRoAbUcRdmw4gVpKTqls\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:53:08.856, admin=null}', '127.0.0.1', '2019-06-26 13:53:09');
INSERT INTO `sys_log` VALUES (18, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0OTIsInVzZXJuYW1lIjoiYWRtaW4ifQ.vdn1crIsFohB39MOqDhYSqG1wMZj9ioQeF_3Up-uaQo\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:53:12.07, admin=null}', '127.0.0.1', '2019-06-26 13:53:12');
INSERT INTO `sys_log` VALUES (19, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0OTQsInVzZXJuYW1lIjoiYWRtaW4ifQ.t4vwaDV30BqjqB-1AVOW9B_zXufBdIOCEC4uTNaWXdA\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:53:14.588, admin=null}', '127.0.0.1', '2019-06-26 13:53:15');
INSERT INTO `sys_log` VALUES (20, 'login success', 'Admin{id=1, name=\'admin\', password=\'12345678\', salt=\'1234\', token=\'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NTc0OTcsInVzZXJuYW1lIjoiYWRtaW4ifQ.2NYH11IljHBfN9LWBmiNKVsaS8wLqKZ03Cboy-qyUxk\', tokenExpiresIn=123, createTime=null, status=0, lastLoginTime=2019-06-26 15:53:17.069, admin=null}', '127.0.0.1', '2019-06-26 13:53:17');
INSERT INTO `sys_log` VALUES (21, 'login success', 'com.northcity.blog.entity.Admin@3a49dfa5', '127.0.0.1', '2019-06-26 14:03:36');
INSERT INTO `sys_log` VALUES (22, '[Category添加 :]', 'com.northcity.blog.entity.Category@21f9e45', '127.0.0.1', '2019-06-26 14:03:44');
INSERT INTO `sys_log` VALUES (23, '[Category添加 :]', 'com.northcity.blog.entity.Category@c71a33e9', '127.0.0.1', '2019-06-26 14:03:53');
INSERT INTO `sys_log` VALUES (24, '[Category添加 :]', 'com.northcity.blog.entity.Category@5bf007a4', '127.0.0.1', '2019-06-26 14:05:51');
INSERT INTO `sys_log` VALUES (25, '增加标签', 'com.northcity.blog.entity.Tag@fb3e7a51', '127.0.0.1', '2019-06-26 14:16:20');
INSERT INTO `sys_log` VALUES (26, '增加标签', 'com.northcity.blog.entity.Tag@ee0b205', '127.0.0.1', '2019-06-26 14:16:30');
INSERT INTO `sys_log` VALUES (27, '增加标签', 'com.northcity.blog.entity.Tag@763c40', '127.0.0.1', '2019-06-26 14:16:38');
INSERT INTO `sys_log` VALUES (28, '[添加友链 :]', 'com.northcity.blog.entity.Friends@649951f', '127.0.0.1', '2019-06-26 14:27:56');
INSERT INTO `sys_log` VALUES (29, '[添加友链 :]', 'com.northcity.blog.entity.Friends@1528201', '127.0.0.1', '2019-06-26 14:32:19');
INSERT INTO `sys_log` VALUES (30, '[删除友链 :]', 'com.northcity.blog.entity.Friends@71ccd140', '127.0.0.1', '2019-06-26 14:33:49');
INSERT INTO `sys_log` VALUES (31, '[修改页面配置:]', 'com.northcity.blog.entity.BlogConfig@75b447d6', '127.0.0.1', '2019-06-26 14:34:22');
INSERT INTO `sys_log` VALUES (32, '[修改页面配置:]', 'com.northcity.blog.entity.BlogConfig@8bbd9425', '127.0.0.1', '2019-06-26 14:35:31');
INSERT INTO `sys_log` VALUES (33, 'login success', 'com.northcity.blog.entity.Admin@32566fa6', '127.0.0.1', '2019-06-27 10:18:37');
INSERT INTO `sys_log` VALUES (34, '[添加文章 :]', '0', '127.0.0.1', '2019-06-27 12:05:58');
INSERT INTO `sys_log` VALUES (35, '[添加文章 :]', '0', '127.0.0.1', '2019-06-27 12:09:23');
INSERT INTO `sys_log` VALUES (36, '[添加文章 :]', '0', '127.0.0.1', '2019-06-27 12:16:59');
INSERT INTO `sys_log` VALUES (37, '[添加文章 :]', '0', '127.0.0.1', '2019-06-27 12:18:29');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签名称',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态，0表示正常，1表示删除，默认0',
  `article_count` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 'JAVA', 0, 0, '2019-06-26 14:16:20', NULL);
INSERT INTO `tag` VALUES (2, 'Latex', 0, 0, '2019-06-26 14:16:30', NULL);
INSERT INTO `tag` VALUES (3, 'TTL', 0, 0, '2019-06-26 14:16:38', NULL);

SET FOREIGN_KEY_CHECKS = 1;
