package com.lpl.kled.entity.systems;


/**
 * 用户DTO
 * @ClassName: UserDto 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author hexin 
 * @date 2016年10月8日 下午5:52:34 
 *
 */
public class Power {
    
    private Long id;
	
    private String name;//名称

    private String code;//code
    
    private String value;//value
    
    private int level;
    
    private Long parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
    

}
