package com.lpl.kled.service.systems.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpl.kled.common.utils.MD5;
import com.lpl.kled.dao.systems.PowerDao;
import com.lpl.kled.dao.systems.RoleDao;
import com.lpl.kled.dao.systems.UserDao;
import com.lpl.kled.dto.QueryResult;
import com.lpl.kled.entity.systems.Power;
import com.lpl.kled.entity.systems.Role;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.RoleService;
import com.lpl.kled.service.systems.UserService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired  
	private RoleDao roleDao; 
	
	
	@Override
	public Role getById(Long id) {
		return this.roleDao.queryById(id);
	}

	@Override
	public QueryResult<Role> getPaginationData(Map<String, Object> params) {
		QueryResult<Role> roles=new QueryResult<Role>();
		
		roles.setData(getPaginationList(params));
		roles.setTotal(getPaginationCount(params));
		return roles;
	}
	
	private List<Role> getPaginationList(Map<String, Object> params){
		return roleDao.getPaginationList(params);
	}
	private Long getPaginationCount(Map<String, Object> params){
		return roleDao.getPaginationCount(params);
	}
	
	public boolean create(Role role){
		Long l= roleDao.create(role);
		return l!=null && l>0?true:false;
	}
	@Override
	public boolean delById(Long id) {
		Long l= roleDao.delById(id);
		return l!=null && l>0?true:false;
	}

	@Override
	public boolean update(Role entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createOrUpdate(Role entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
