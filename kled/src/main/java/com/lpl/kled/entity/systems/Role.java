package com.lpl.kled.entity.systems;

import java.util.List;

import com.lpl.kled.entity.base.Base;

/**
 * 角色DTO
 * @ClassName: RoleDto 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author hexin 
 * @date 2016年10月8日 下午5:52:34 
 *
 */
public class Role extends Base{
    
	
    private String name;//名称

    private String code;//code
    
    private String remark;//value
    
    private List<Power> powers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}

	
    
    
    
	
}
