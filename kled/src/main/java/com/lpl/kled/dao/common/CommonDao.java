package com.lpl.kled.dao.common;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lpl.kled.entity.common.Img;


@Repository("commonDao")
public interface CommonDao {
	public void updateSystemQR(@Param(value="name")String name, @Param(value="content")byte[] content);
    
	public List<Img> querySystemQR(@Param(value="name")String name);
}
