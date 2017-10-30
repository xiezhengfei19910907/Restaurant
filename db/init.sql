DROP DATABASE IF EXISTS Restaurants;
CREATE DATABASE Restaurants CHARSET =utf8;
USE Restaurants;

DROP TABLE IF EXISTS UserLogin;
CREATE TABLE UserLogin(
    clerkid INT UNSIGNED NOT NULL COMMENT '编号id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '姓名',
    zhiwei VARCHAR(255) NOT NULL DEFAULT '' COMMENT '职位',
    `password` INT UNSIGNED NOT NULL COMMENT '密码'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '用户登录';

INSERT INTO UserLogin (clerkid, `name`, zhiwei, `password`) VALUES (104135, '谢正飞', '系统管理员', 104135);

DROP TABLE IF EXISTS renshiziliao;
CREATE TABLE renshiziliao(
	clerkid CHAR(6) NOT NULL COMMENT '编号id',
	`name` VARCHAR(50) NOT NULL COMMENT '姓名',
	sex CHAR(1) NOT NULL COMMENT '性别',
	address VARCHAR(50) NULL COMMENT '地址',
	birth VARCHAR(100) NOT NULL COMMENT '生日',
	id CHAR(18) NOT NULL COMMENT '身份id',
	xueli CHAR(50) NOT NULL COMMENT '学历',
	zhiwei VARCHAR(50) NOT NULL COMMENT '职位',
	hunfou CHAR(10) NOT NULL COMMENT '婚否',
    PRIMARY KEY(clerkid)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '人事资料';

INSERT INTO renshiziliao(clerkid, NAME, sex, address, birth, id, xueli, zhiwei, hunfou) VALUES(104135, '谢正飞', '男', '216', '1991-09-07', '342409199109071234', '本科','系统管理员', '已婚');

DROP TABLE IF EXISTS menu;
CREATE TABLE menu(
    abbreviation VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	category VARCHAR(255) NOT NULL,
	price FLOAT NOT NULL,
	material VARCHAR(255) NULL,
	remark VARCHAR(255) NULL,
	sale VARCHAR(255) NULL
) ENGINE=INNODB DEFAULT CHARSET= utf8 COMMENT '菜单信息';

INSERT INTO menu (abbreviation, NAME, category, price, material, remark, sale) VALUES('wxnr', '五香牛肉', '东北菜', 25, '牛肉', '', ''), ('dj', '冻鸡', '京菜', 25, '肉鸡', '', 5);