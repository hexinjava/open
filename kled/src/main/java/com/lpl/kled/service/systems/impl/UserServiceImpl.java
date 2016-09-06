package com.lpl.kled.service.systems.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpl.kled.dao.systems.UserDao;
import com.lpl.kled.dto.QueryResult;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired  
	private UserDao userDao;  
	
	@Override
	public User getUserById(Long id) {
		return this.userDao.queryById(id);
	}
	public User getUserByAccount(String userName){
		List<User> users=this.userDao.getUserByAccount(userName);
		return users!=null&& users.size()>0?users.get(0):null;
	}

	@Override
	public QueryResult<User> getPaginationData(Map<String, Object> params) {
		QueryResult<User> users=new QueryResult<User>();
		users.setResult(getPaginationList(params));
		users.setTotal(getPaginationCount(params));
		return users;
	}
	
	private List<User> getPaginationList(Map<String, Object> params){
		return null;
	}
	private Long getPaginationCount(Map<String, Object> params){
		return null;
	}
}
