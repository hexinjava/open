package com.lpl.kled.service.systems.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpl.kled.common.utils.MD5;
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
		
		users.setData(getPaginationList(params));
		users.setTotal(getPaginationCount(params));
		return users;
	}
	
	private List<User> getPaginationList(Map<String, Object> params){
		return userDao.getPaginationList(params);
	}
	private Long getPaginationCount(Map<String, Object> params){
		return userDao.getPaginationCount(params);
	}
	
	public boolean create(User user){
		user.setPassword(MD5.encodePassword(user.getPassword()));
		Long l= userDao.create(user);
		return l!=null && l>0?true:false;
	}
	@Override
	public boolean delUserById(Long id) {
		Long l= userDao.delUserById(id);
		return l!=null && l>0?true:false;
	}
}
