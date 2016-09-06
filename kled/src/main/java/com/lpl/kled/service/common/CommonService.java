package com.lpl.kled.service.common;


import com.lpl.kled.entity.common.Img;

public interface CommonService{
	public void updateSystemQR(String name, byte[] content);
	
	public Img querySystemQR(String name);
}
