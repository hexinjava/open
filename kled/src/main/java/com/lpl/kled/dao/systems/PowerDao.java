package com.lpl.kled.dao.systems;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lpl.kled.dto.MenuSecurity;
import com.lpl.kled.entity.systems.Power;

@Repository("powerDao")
@Transactional
public interface PowerDao {
	
	public List<MenuSecurity> getPowerByLevel(@Param(value="level")int level);
	
	public List<Power> getPowerByRoleId(@Param(value="roleId")Long roleId);
	
}
