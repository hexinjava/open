package com.lpl.kled.service.systems.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpl.kled.common.utils.MD5;
import com.lpl.kled.common.utils.StringUtil;
import com.lpl.kled.dao.systems.PowerDao;
import com.lpl.kled.dao.systems.RoleDao;
import com.lpl.kled.dao.systems.UserDao;
import com.lpl.kled.dto.QueryResult;
import com.lpl.kled.entity.systems.Power;
import com.lpl.kled.entity.systems.Role;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.UserService;

@Service("userService")
public  class UserServiceImpl implements UserService{
	@Autowired  
	private UserDao userDao;  
	@Autowired  
	private RoleDao roleDao; 
	@Autowired  
	private PowerDao powerDao;
	
	
	@Override
	public User getById(Long id) {
		return this.userDao.queryById(id);
	}

	public User selectUserById(Long id) {
		return this.userDao.selectUserById(id);
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
	
	public boolean createOrUpdate(User user){
		if(user!=null && user.getId()!=null){
			return update(user);
		}else{
			return create(user);
		}
	}
	
	@Transactional
	public boolean create(User user){
		user.setPassword(MD5.encodePassword(user.getPassword()));
		user.setCreateTime(new Date());
		Long l= userDao.create(user);
		List<User> insertUser=userDao.getUserByAccount(user.getAccount());
		Long userId=null;
		userId = (insertUser!=null && insertUser.size()==1?insertUser.get(0).getId():null);
		boolean bool= l!=null && l>0?true:false;
		if(bool || userId!=null){
			userRoleRelation(userId, user.getRoleIds());
		}
		return bool;
	}
	
	@Transactional
	public boolean update(User user){
		user.setUpdateTime(new Date());
		Long l= userDao.update(user);
		boolean bool= l!=null && l>0?true:false;
		if(bool){
			roleDao.delUserRoleByUserId(user.getId());
			userRoleRelation(user.getId(), user.getRoleIds());
		}
		return bool;
	}
	
	/**
	 * 维护用户角色关系
	 * @Title: userRoleRelation 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param userId
	 * @param @param roleIds    设定文件 
	 * @throws
	 */
	private void userRoleRelation(Long userId,String roleIds){
		if(StringUtil.isNotNull(roleIds)){
			String [] ids=roleIds.split(",");
			for (String roleId : ids) {
				roleDao.createUserRole(userId, Long.parseLong(roleId));
			}
		}
	}
	
	@Override
	public boolean delete(Long id) {
		Long l= userDao.delete(id);
		return l!=null && l>0?true:false;
	}
	@Override
	public User getCurrentUserByAccount(String account) {
		List<User> users=this.userDao.getUserByAccount(account);
		User user=null;
		if(users!=null && users.size()>0){
			user =users.get(0);
			List<Role> roles=this.roleDao.getRoleByUserId(user.getId());
			
			if(roles!=null && roles.size()>0){
				for (Role role : roles) {
					List<Power> powers=this.powerDao.getPowerByRoleId(role.getId());
					role.setPowers(powers);
				}
				
			}
			user.setRoles(roles);
		}
		
		return user;
	}

}
