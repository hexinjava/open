package com.lpl.kled.entity.common;

import javax.persistence.Entity;

@Entity
public class Img{
	private Long id;
    private String name;
    private byte[] content;
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
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
    
}
