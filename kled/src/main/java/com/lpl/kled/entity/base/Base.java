package com.lpl.kled.entity.base;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Base {
	
	@Expose
	private Long id;
	@Expose
    private Date createTime;
	@Expose
    private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
