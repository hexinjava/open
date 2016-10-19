package com.lpl.kled.entity.systems;

import java.util.List;

import javax.persistence.Entity;

import com.google.gson.annotations.Expose;
import com.lpl.kled.entity.base.Base;

@Entity
public class User extends Base{
	
	@Expose
	private String name;
	@Expose
    private String password;
	@Expose
    private String account;
	@Expose
    private String state;
	@Expose
    private String email;
	@Expose
    private String phone;
	@Expose
    private String roleIds;
    @Expose
    private List<Role> roles;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
    
	
}
