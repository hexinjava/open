package com.lpl.kled.test;


import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.UserService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	private static Logger logger = Logger.getLogger(Test.class);
	
	//private ApplicationContext ac = null;  
	@Autowired  
	private UserService userService;  
	  
	/*@Before  
	public void before() {  
	    ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
	    userService = (UserService) ac.getBean("userService");  
	}  */
	  
	@org.junit.Test     
	public void test1() {  
	    User user = userService.getUserById(1L);  
	    // System.out.println(user.getUserName());  
	    // logger.info("值："+user.getUserName());  
	    logger.info(JSON.toJSONString(user)); 
	}
}
