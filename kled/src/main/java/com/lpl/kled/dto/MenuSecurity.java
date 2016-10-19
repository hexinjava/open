package com.lpl.kled.dto;

import java.util.List;

/**
 * 菜单权限
 * @ClassName: MenuSecurity 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author hexin 
 * @date 2016年10月8日 下午5:52:34 
 *
 */
public class MenuSecurity {
    private Long id;
	
    private String name;//名称

    private String code;//code
    
    private String value;//value
    
    private int level;
    
    private Long parentId;

    private List<MenuSecurity> menuSecurity;//下级权限

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<MenuSecurity> getMenuSecurity() {
		return menuSecurity;
	}

	public void setMenuSecurity(List<MenuSecurity> menuSecurity) {
		this.menuSecurity = menuSecurity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
	
}
