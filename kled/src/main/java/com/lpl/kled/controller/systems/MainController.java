package com.lpl.kled.controller.systems;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lpl.kled.common.utils.SessionUtil;
/**
 * 
 * @ClassName: MainController 
 * @Description: TODO(首页控制层) 
 * @author hexin 
 * @date 2016年8月25日 下午2:12:25 
 *
 */
@Controller  
public class MainController {
	/**
	 * @Title: main 
	 * @Description: TODO(首页) 
	 * @param  request
	 * @param  model
	 * @param  设定文件 
	 * @return String
	 * @throws
	 */
    @RequestMapping("/main")
    public String main(HttpServletRequest request,Model model){
    	model.addAttribute("currentUser", SessionUtil.getAttr(request, "currentUser"));
    	return "/welcome/index";
    }
    
    
    /**
	 * @Title: main 
	 * @Description: TODO(首页右侧内容) 
	 * @param  request
	 * @param  model
	 * @param  设定文件 
	 * @return String
	 * @throws
	 */
    @RequestMapping("/pages/welcome/home")
    public String home(HttpServletRequest request,Model model){
    	
    	return "/welcome/home";
    }
}
