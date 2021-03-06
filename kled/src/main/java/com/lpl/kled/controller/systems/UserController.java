package com.lpl.kled.controller.systems;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lpl.kled.controller.base.BaseController;
import com.lpl.kled.dto.QueryResult;
import com.lpl.kled.dto.SubmitResult;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.UserService;


@Controller  
public class UserController extends BaseController{

	@Autowired
    private UserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        Long userId = Long.parseLong(request.getParameter("id"));  
        User user = this.userService.getById(userId);  
        model.addAttribute("user", user);  
        return "/systems/showUser"; 
    }
    @RequestMapping("/pages/systems/user/list")
    public String toList(HttpServletRequest request,Model model){
    	return "/systems/user/list";
    }
    
    @RequestMapping("/pages/systems/user/add")
    @ResponseBody
    public String saveUser(HttpServletRequest request,Model model){
    	User user=getPostEntity(request, User.class);
    	boolean bool=this.userService.createOrUpdate(user);
    	return toJson(new SubmitResult(bool?0:1));
    }
    
    @RequestMapping("/pages/systems/user/get")
    @ResponseBody
    public String getUser(HttpServletRequest request,Model model){
    	return toJson(this.userService.selectUserById(Long.parseLong(request.getParameter("userId"))));
    }
    
    @RequestMapping("/pages/systems/user/list/data")
	@ResponseBody
    public String getUserData(HttpServletRequest request,Model model)throws Exception{
    	Map<String,Object> params=getQueryParams(request);
    	QueryResult<User> result = this.userService.getPaginationData(params);
        return toJson(result);
    }
    
    @RequestMapping("/pages/systems/user/del")
    @ResponseBody
    public String delUser(HttpServletRequest request,Model model){
    	boolean bool=this.userService.delete(Long.parseLong(request.getParameter("userId")));
    	return toJson(new SubmitResult(bool?0:1));
    }
}
