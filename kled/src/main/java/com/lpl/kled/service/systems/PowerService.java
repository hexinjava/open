package com.lpl.kled.service.systems;

import java.util.List;
import java.util.Map;


import com.lpl.kled.dto.MenuSecurity;
import com.lpl.kled.entity.systems.User;
public interface PowerService{
	
	 public List<MenuSecurity> getCurrentUserMenuSecurity();
	 
	 public boolean exist(User user,String code);
	 
	 public List<Map<String, Object>> getTreeData(Long roleId);
	 
	 public boolean rolePowerRelation(Long roleId,String powers);
	 
}
