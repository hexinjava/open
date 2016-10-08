package com.lpl.kled.service.systems;

import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.base.BaseService;

public interface UserService extends BaseService<User>{
	 public User getUserById(Long id);
	 public User getUserByAccount(String userName);
	 /**
	  * 创建用户
	  * @Title: create 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param user
	  * @return Long    返回创建的主键id
	  * @throws
	  */
	 public boolean create(User user);
	 
	 public boolean delUserById(Long id);
}
