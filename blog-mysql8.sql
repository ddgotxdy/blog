-- 用户表
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
     `user_id` bigint NOT NULL COMMENT '用户ID',
     `username` varchar(50) NOT NULL COMMENT '用户名',
     `password` varchar(100) NOT NULL COMMENT '密码',
     `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱号',
     `nickname` varchar(50) NOT NULL COMMENT '用户真实昵称',
     `avatar_url` varchar(1024) NOT NULL DEFAULT '' COMMENT '用户头像地址',
     `introduce` varchar(255) NOT NULL DEFAULT '' COMMENT '用户简介',
     `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
     `create_time` bigint NOT NULL COMMENT '创建时间',
     `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`user_id`),
     UNIQUE INDEX `username`(`username`)
);

-- 文章表
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
   `article_id` bigint NOT NULL COMMENT '文章ID',
   `article_cover_url` varchar(1024) NOT NULL DEFAULT '' COMMENT '文章缩略图，没有则默认填充',
   `article_title` varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
   `article_content` longtext NOT NULL COMMENT '文章内容',
   `rank` int NOT NULL DEFAULT 0 COMMENT '置顶排序，值越大，排名越高',
   `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
   `article_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '文章状态值 1公开 2私密 3登录可见',
   `tag_ids` varchar(1024) NOT NULL DEFAULT '[]' COMMENT '包含的标签，1<=num<=3 例如[1001,1002,1003]',
   `category_id` bigint NOT NULL COMMENT '所属分类',
   `create_time` bigint NOT NULL COMMENT '发表时间',
   `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`article_id`)
);

-- 分类表
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`(
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `category_name` varchar(20) NOT NULL COMMENT '分类名',
    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
    `create_time` bigint NOT NULL COMMENT '创建时间',
    `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`category_id`)
);

-- 标签表
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
    `tag_id` bigint NOT NULL COMMENT '标签ID',
    `tag_name` varchar(20) NOT NULL COMMENT '标签名',
    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
    `create_time` bigint NOT NULL COMMENT '创建时间',
    `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`tag_id`)
);

-- 文章评论表
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
   `comment_id` bigint NOT NULL COMMENT '评论ID',
   `user_id` bigint NOT NULL COMMENT '评论用户ID',
   `article_id` bigint NULL DEFAULT NULL COMMENT '评论主题id',
   `comment_content` text NOT NULL COMMENT '评论内容',
   `reply_user_id` bigint NULL DEFAULT NULL COMMENT '回复用户id',
   `parent_id` int NULL DEFAULT NULL COMMENT '父评论id',
   `type` tinyint NOT NULL COMMENT '评论类型 1.文章 2.友链 3.说说',
   `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
   `audit_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '审核类型 0审核中 1审核通过 2审核失败',
   `create_time` bigint NOT NULL COMMENT '评论时间',
   `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`comment_id`)
);

-- 角色表
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role`  (
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `role_name` varchar(20) NOT NULL COMMENT '角色名',
    `role_desc` varchar(50) NOT NULL COMMENT '角色描述',
    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用  0否 1是',
    `create_time` bigint NOT NULL COMMENT '创建时间',
    `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`role_id`)
);

-- 菜单表



-- 界面配置
DROP TABLE IF EXISTS `blog_page`;
CREATE TABLE `blog_page`  (
    `page_id` bigint NOT NULL AUTO_INCREMENT COMMENT '页面id',
    `page_name` varchar(10) NOT NULL COMMENT '页面名',
    `page_desc` varchar(255) NULL DEFAULT NULL COMMENT '页面描述',
    `page_cover_url` varchar(1024) NOT NULL COMMENT '页面封面',
    `create_time` bigint NOT NULL COMMENT '创建时间',
    `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`page_id`)
);

-- 留言表
DROP TABLE IF EXISTS `blog_message`;
CREATE TABLE `blog_message`  (
   `message_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
   `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称，默认访客',
   `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
   `message_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容',
   `audit_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '审核类型 0审核中 1审核通过 2审核失败',
   `create_time` bigint NOT NULL COMMENT '创建时间',
   `update_time` bigint NULL DEFAULT NULL COMMENT '修改时间',
   PRIMARY KEY (`message_id`)
);



-- 各种日志表，后续添加