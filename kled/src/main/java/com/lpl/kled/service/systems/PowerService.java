package com.lpl.kled.service.systems;

import java.util.List;

import com.lpl.kled.dto.MenuSecurity;
import com.lpl.kled.entity.systems.User;
public interface PowerService{
	
	 public List<MenuSecurity> getCurrentUserMenuSecurity();
	 
	 public boolean exist(User user,String code);
}
