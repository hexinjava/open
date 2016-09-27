package com.lpl.kled.dao.systems;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lpl.kled.entity.systems.User;

@Repository("userDao")
@Transactional
public interface UserDao {
	public User queryById(@Param(value="id")Long id);
	
	public List<User> getUserByAccount(@Param(value="account")String userName);
	
	public List<User> getPaginationList(Map<String, Object> params);
	
	public Long getPaginationCount(Map<String, Object> params);
}
