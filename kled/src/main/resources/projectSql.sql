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
