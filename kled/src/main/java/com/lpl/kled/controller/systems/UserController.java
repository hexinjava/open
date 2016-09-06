package com.lpl.kled.controller.systems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lpl.kled.dto.QueryResult;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.systems.UserService;

@Controller  
public class UserController {
	@Autowired
    private UserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        Long userId = Long.parseLong(request.getParameter("id"));  
        User user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "/systems/showUser"; 
    }
    @RequestMapping("/pages/systems/list")
    public String toList(HttpServletRequest request,Model model){
    	Map<String,Object> params=new HashMap<String,Object>();
    	QueryResult<User> users = this.userService.getPaginationData(params);
    	model.addAttribute("users", users); 
    	return "/systems/list";
    }
}
