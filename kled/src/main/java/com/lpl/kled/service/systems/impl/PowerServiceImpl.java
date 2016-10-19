package com.lpl.kled.service.systems.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpl.kled.common.utils.StringUtil;
import com.lpl.kled.dao.systems.PowerDao;
import com.lpl.kled.dto.MenuSecurity;
import com.lpl.kled.entity.systems.Power;
import com.lpl.kled.entity.systems.Role;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.PowerService;

@Service("powerService")
public class PowerServiceImpl implements PowerService{
	@Autowired  
	private PowerDao powerDao;  
	
	
	@Override
	public List<MenuSecurity> getCurrentUserMenuSecurity() {
		List<MenuSecurity> fristMenus=powerDao.getPowerByLevel(1);
		List<MenuSecurity> twoMenus=powerDao.getPowerByLevel(2);
		List<MenuSecurity> threeMenus=powerDao.getPowerByLevel(3);
		
		if(fristMenus!=null && fristMenus.size()>0){
			for (MenuSecurity fristMenu : fristMenus) {
				
				List<MenuSecurity> two=new ArrayList<MenuSecurity>();
				if(twoMenus!=null && twoMenus.size()>0){
					for (MenuSecurity twoMenu : twoMenus) {
						
						if(threeMenus!=null && threeMenus.size()>0){
							List<MenuSecurity> three=new ArrayList<MenuSecurity>();
							for (MenuSecurity threeMenu : threeMenus) {
								if(twoMenu.getId().equals(threeMenu.getParentId())){
									three.add(threeMenu);
								}
							}
							twoMenu.setMenuSecurity(three);
						}
						
						if(fristMenu.getId().equals(twoMenu.getParentId())){
							two.add(twoMenu);
						}
					}
					fristMenu.setMenuSecurity(two);
					
				}
			}
		}
		
		return fristMenus;
	}
    

	/**
	 * 判断用户是否可以访问该code
	 * @Title: exist 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  user
	 * @param  code
	 * @return boolean    返回类型 
	 * @throws
	 */
	public boolean exist(User user,String code){
		if(user.getRoles() != null && user.getRoles().size()>0){
			for(Role role : user.getRoles()){
				if(role.getPowers()!=null && role.getPowers().size()>0){
					for (Power power : role.getPowers()) {
						if(code.equals(power.getCode())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
