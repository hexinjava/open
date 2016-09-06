package com.lpl.kled.common;

import java.util.ResourceBundle;
/**
 * 系统配置文件参数
 * @ClassName: KledConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author hexin 
 * @date 2016年8月26日 下午4:16:27 
 *
 */
public class KledConfig {
	public static ResourceBundle kledConfig = ResourceBundle.getBundle("kled");
	
	public static String getKledUrl() {
		return kledConfig.getString("kled.kledUrl");
	}
}
