package com.lpl.kled.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.lpl.kled.common.KledConfig;
import com.lpl.kled.common.utils.QRCodeUtil;
import com.lpl.kled.service.common.CommonService;

/**
 * 系统初始化
 * @ClassName: systemInit 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author hexin 
 * @date 2016年8月26日 下午3:45:22 
 *
 */
public class systemInit extends WebApplicationObjectSupport implements ServletContextListener {
	Logger logger = Logger.getLogger(systemInit.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
        logger.info("系统停止!");		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("########## 系统初始化开始 ##########");
		ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		
		//1.生成登录二维码并存到数据库中
		CommonService commonService = application.getBean(CommonService.class);
		try {
			commonService.updateSystemQR("systemLoginQR",QRCodeUtil.encode(KledConfig.getKledUrl()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(KledConfig.getKledUrl());
		logger.info("########## 系统初始化结束 ##########");
	}

}
