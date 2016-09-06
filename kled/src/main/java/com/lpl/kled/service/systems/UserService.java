package com.lpl.kled.service.systems;

import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.base.BaseService;

public interface UserService extends BaseService<User>{
	 public User getUserById(Long id);
	 public User getUserByAccount(String userName);
}
