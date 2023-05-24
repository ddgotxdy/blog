-- 用户表
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
     `user_id` bigint NOT NULL COMMENT '用户ID',
     `username` varchar(50) NOT NULL COMMENT '用户名',
     `password` varchar(100) NOT NULL COMMENT '密码',
     `email` varchar(50) NOT NULL COMMENT '邮箱号',
     `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '用户真实昵称',
     `phone_number` varchar(32) DEFAULT NULL DEFAULT '' COMMENT '手机号',
     `sex` tinyint(1) DEFAULT 0 COMMENT '用户性别（0未知，1男，2女）',
     `avatar_url` varchar(128) DEFAULT '' COMMENT '头像',
     `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色ID',
     `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
     `create_time` bigint NOT NULL COMMENT '创建时间',
     `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`user_id`)
);

-- 角色表
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role` (
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `role_name` varchar(128) DEFAULT NULL COMMENT '角色名称',
    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
    `menu_ids` varchar(1024) NOT NULL DEFAULT '[]' COMMENT '包含的权限，例如[1001,1002,1003]',
    `create_time` bigint NOT NULL COMMENT '创建时间',
    `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
    `role_desc` varchar(512) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`)
);

-- 菜单表
DROP TABLE IF EXISTS `blog_menu`;
CREATE TABLE `blog_menu` (
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    `menu_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
    `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
    `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
    `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
    `create_time` bigint NOT NULL COMMENT '创建时间',
    `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
    `menu_desc` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`menu_id`)
);

-- 文章表
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
   `article_id` bigint NOT NULL COMMENT '文章ID',
   `article_cover_url` varchar(128) NOT NULL DEFAULT '' COMMENT '文章缩略图，没有则默认填充',
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

-- 图片表
DROP TABLE IF EXISTS `blog_image`;
CREATE TABLE `blog_image`(
     `image_id` bigint NOT NULL COMMENT '图片ID',
     `image_name` varchar(20) NOT NULL COMMENT '图片名',
     `image_url` varchar(200) NOT NULL COMMENT '图片url地址',
     `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
     `create_time` bigint NOT NULL COMMENT '创建时间',
     `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`image_id`)
);

-- 文章评论表
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
   `comment_id` bigint NOT NULL COMMENT '评论ID',
   `user_id` bigint NOT NULL COMMENT '评论用户ID',
   `article_id` bigint NULL DEFAULT NULL COMMENT '评论主题id',
   `comment_content` varchar(1024) NOT NULL COMMENT '评论内容',
   `reply_user_id` bigint NULL DEFAULT NULL COMMENT '回复用户id',
   `parent_id` int NULL DEFAULT NULL COMMENT '父评论id',
   `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
   `audit_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '审核类型 0审核中 1审核通过 2审核失败',
   `create_time` bigint NOT NULL COMMENT '评论时间',
   `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`comment_id`)
);

-- 留言表
DROP TABLE IF EXISTS `blog_message`;
CREATE TABLE `blog_message`  (
     `message_id` bigint NOT NULL COMMENT '留言ID',
     `message_content` varchar(50) NOT NULL COMMENT '留言内容',
     `audit_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '审核类型 1审核中 2审核通过 3审核失败',
     `create_time` bigint NOT NULL COMMENT '留言时间',
     `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`message_id`)
);

-- 敏感词过滤
DROP TABLE IF EXISTS `blog_sensitive`;
CREATE TABLE `blog_sensitive`  (
     `sensitive_id` bigint NOT NULL COMMENT '敏感ID',
     `word` varchar(50) NOT NULL COMMENT '敏感词',
     `sensitive_type` tinyint(4) NOT NULL DEFAULT 2 COMMENT '敏感类型 2 deny 1 allow',
     `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
     `create_time` bigint NOT NULL COMMENT '创建时间',
     `update_time` bigint NULL DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`sensitive_id`)
);

-- 操作日志表
DROP TABLE IF EXISTS `blog_oplog`;
CREATE TABLE `blog_oplog`  (
    `operator_id` bigint NOT NULL COMMENT '操作id',
    `user_id` bigint NOT NULL COMMENT '操作用户ID',
    `operator_content` varchar(200) NOT NULL COMMENT '操作内容',
    `operator_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '操作类型 1文章新增',
    `operator_stage` tinyint(4) NOT NULL DEFAULT 0 COMMENT '操作阶段 1预操作 2操作成功',
    `create_time` bigint NOT NULL COMMENT '操作时间',
    PRIMARY KEY (`operator_id`)
);