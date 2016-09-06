package com.lpl.kled.service.common.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpl.kled.dao.common.CommonDao;
import com.lpl.kled.entity.common.Img;
import com.lpl.kled.service.common.CommonService;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	@Autowired  
	private CommonDao commonDao;

	@Override
	public void updateSystemQR(String name, byte[] content) {
		if(name!=null && content!=null){
			commonDao.updateSystemQR(name,content);
		}
	}  
	
	@Override
	public Img querySystemQR(String name){
		List<Img> files=commonDao.querySystemQR(name);
		if(files!=null && files.size()==1){
			return files.get(0);
		}
		return null;
	}
	
}
