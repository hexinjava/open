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
import com.lpl.kled.entity.systems.Role;
import com.lpl.kled.service.systems.RoleService;


@Controller  
public class RoleController extends BaseController{

	@Autowired
    private RoleService roleService;  
      
    @RequestMapping("/pages/systems/role/list")
    public String toList(HttpServletRequest request,Model model){
    	return "/systems/role/list";
    }
    
    @RequestMapping("/pages/systems/role/add")
    @ResponseBody
    public String saveUser(HttpServletRequest request,Model model){
    	Role role=getPostEntity(request, Role.class);
    	boolean bool=this.roleService.create(role);
    	return toJson(new SubmitResult(bool?0:1));
    }
    
    @RequestMapping("/pages/systems/role/get")
    @ResponseBody
    public String getUser(HttpServletRequest request,Model model){
    	return toJson(this.roleService.getById(Long.parseLong(request.getParameter("roleId"))));
    }
    
    @RequestMapping("/pages/systems/role/list/data")
	@ResponseBody
    public String getUserData(HttpServletRequest request,Model model)throws Exception{
    	Map<String,Object> params=getQueryParams(request);
    	QueryResult<Role> result = this.roleService.getPaginationData(params);
        return toJson(result);
    }
    
    @RequestMapping("/pages/systems/role/del")
    @ResponseBody
    public String delUser(HttpServletRequest request,Model model){
    	boolean bool=this.roleService.delById(Long.parseLong(request.getParameter("roleId")));
    	return toJson(new SubmitResult(bool?0:1));
    }
}
