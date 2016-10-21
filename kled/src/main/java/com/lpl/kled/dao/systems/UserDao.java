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
	
	public Long create(User user);
	
	public Long update(User user);
	
	public Long delete(@Param(value="id")Long id);
	/**
	 * 根据id查询用户信息 包含角色
	 * @Title: selectUserById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param id
	 * @return User    返回类型 
	 * @throws
	 */
	public User selectUserById(@Param(value="id")Long id);
}
