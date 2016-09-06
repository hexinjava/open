package com.lpl.kled.dao.systems;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lpl.kled.entity.systems.User;

@Repository("userDao")
public interface UserDao {
	public User queryById(@Param(value="id")Long id);
	
	public List<User> getUserByAccount(@Param(value="account")String userName);
}
