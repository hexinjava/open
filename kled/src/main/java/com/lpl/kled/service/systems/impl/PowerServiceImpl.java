package com.lpl.kled.service.systems.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpl.kled.common.utils.StringUtil;
import com.lpl.kled.dao.systems.PowerDao;
import com.lpl.kled.dto.MenuSecurity;
import com.lpl.kled.entity.systems.Power;
import com.lpl.kled.entity.systems.Role;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.PowerService;

@Service("powerService")
@Transactional
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
	
	/**
	 * 获取权限树
	 * @Title: exist 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  roleId 角色id 如果为null 则权限不选中
	 * @return boolean    返回类型 
	 * @throws
	 */
	public List<Map<String, Object>> getTreeData(Long roleId){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();  
        List<Power> powerList = powerDao.getPowerList();  
        
        List<Power> rolePowerList=null;
        if(roleId !=null){
        	rolePowerList = powerDao.getPowerByRoleId(roleId); 
        }  
        Map<String, Object> fristMap = new HashMap<String, Object>();
        
        fristMap.put("id", 0);  
        fristMap.put("pId", -1);  
        fristMap.put("name", "全选");
        boolean fristBool=true;
        
        for (Power power : powerList) {
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", power.getId());  
            map.put("pId", power.getParentId()!=null?power.getParentId():0);  
            map.put("name", power.getName());
            for (Power rolePower : rolePowerList) {
				if(power.getId()==rolePower.getId()){
					map.put("checked", true);
					if(fristBool){
						fristMap.put("checked", true);
						fristBool=false;
					}
					break;
				}
			}
            mapList.add(map); 
		}
        mapList.add(fristMap);
        return mapList;
	}
	
	/**
	 * 配置角色权限关系
	 * @Title: exist 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  roleId 角色id 
	 * @param  powerIds 权限id   以英文逗号隔开（,） 
	 * @return boolean    返回类型 
	 * @throws
	 */
	public boolean rolePowerRelation(Long roleId,String powerIds){
		boolean bool=false;
		if(roleId!=null){
			//roleDao.delUserRoleByUserId(user.getId());
			powerDao.delRolePowerByRoleId(roleId);
			if(StringUtil.isNotEmpty(powerIds)){
				String [] powers=powerIds.split(",");
				for (String powerId : powers) {
					if(!"0".equals(powerId)){//0id 是全选的id
						powerDao.createRolePower(roleId, Long.parseLong(powerId));
					}
					
				}
			}
			bool=true;
		}
	    return bool;
    }
}
