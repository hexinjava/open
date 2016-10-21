-- 创建用户表并且增加初始化数据
DROP TABLE IF EXISTS tb_user;  
  
CREATE TABLE tb_user (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  name varchar(50) NOT NULL COMMENT '用户名称',  
  password varchar(255) NOT NULL COMMENT '用户密码',  
  account varchar(255) NOT NULL COMMENT '用户账号', 
  state varchar(10) NOT NULL DEFAULT '0' COMMENT '用户状态: 0 有效 1 无效', 
  email varchar(255) DEFAULT NULL COMMENT '邮箱',
  phone varchar(255) DEFAULT NULL COMMENT '手机号', 

  createTime datetime DEFAULT NULL COMMENT '创建时间',   
  updateTime datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;  
 
insert  into tb_user(id,name,account,password) values (1,'超级管理员','admin','e10adc3949ba59abbe56e057f20f883e'); 

-- 创建图片表并初始化数据
DROP TABLE IF EXISTS tb_img;  
  
CREATE TABLE tb_img (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  name varchar(50) NOT NULL COMMENT '名称',  
  content longblob DEFAULT NULL COMMENT '内容',  
  PRIMARY KEY (`id`)  
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;  
 
insert  into tb_img(id,name) values (1,'systemLoginQR'); 

-- 创建权限表
DROP TABLE IF EXISTS tb_power;  
  
CREATE TABLE tb_power (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  name varchar(50) NOT NULL COMMENT '名称',  
  code varchar(50) NOT NULL COMMENT '编码',
  value varchar(250) DEFAULT NULL COMMENT '数据值',
  parentId  bigint(20) DEFAULT 0 COMMENT '父节点id',
  level int(2) DEFAULT NULL COMMENT '级别 1：一级菜单 2：二级菜单...',
  remark varchar(250) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)  
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8; 

insert  into tb_power(name,code,value,parentId,LEVEL) values ('首页','homePage','#pages/welcome/index',0,1);
insert  into tb_power(name,code,parentId,LEVEL) values ('系统','system',0,1);
insert  into tb_power(name,code,value,parentId,LEVEL) values ('用户管理','userManagement','#pages/systems/user/list',2,2);
insert  into tb_power(name,code,value,parentId,LEVEL) values ('角色管理','roleManagement','#pages/welcome/index',2,2);

-- 创建角色表
DROP TABLE IF EXISTS tb_role;  
  
CREATE TABLE tb_role (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  name varchar(50) NOT NULL COMMENT '名称',  
  code varchar(50) DEFAULT  NULL COMMENT '编码',
  remark varchar(250) DEFAULT NULL COMMENT '备注',
  createTime datetime DEFAULT NULL COMMENT '创建时间',   
  updateTime datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)  
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8; 

INSERT INTO tb_role(name) values('系统管理员');
INSERT INTO tb_role(name) values('普通用户');
-- 创建用户角色关系表
DROP TABLE IF EXISTS tb_user_role;  
CREATE TABLE tb_user_role (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  userId bigint(20) NOT NULL COMMENT '用户id',  
  roleId bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)  
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8; 
INSERT INTO tb_user_role(userId,roleId) values(1,1);

-- 创建角色权限关系表
DROP TABLE IF EXISTS tb_role_power;  
CREATE TABLE tb_role_power (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  roleId bigint(20) NOT NULL COMMENT '角色id',  
  powerId bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)  
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8; 
