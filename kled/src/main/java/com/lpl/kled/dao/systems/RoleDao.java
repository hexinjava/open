package com.lpl.kled.dao.systems;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lpl.kled.entity.systems.Role;

@Repository("roleDao")
@Transactional
public interface RoleDao {
	
	public List<Role> getRoleByUserId(@Param(value="userId")Long userId);
	
	public Role queryById(Long id);
	public List<Role> getPaginationList(Map<String, Object> params);
	public Long getPaginationCount(Map<String, Object> params);
	public Long create(Role role);
	public Long delById(Long id);
	
	/**
	 * 创建用户角色关联关系
	 * @Title: createUserRole 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  userId
	 * @param  roleId
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long createUserRole(@Param(value="userId")Long userId,@Param(value="roleId")Long roleId);
	public Long delUserRoleByUserId(@Param(value="userId")Long userId);
}
