package com.lpl.kled.controller.systems;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lpl.kled.common.utils.MD5;
import com.lpl.kled.common.utils.SessionUtil;
import com.lpl.kled.entity.common.Img;
import com.lpl.kled.entity.systems.User;
import com.lpl.kled.service.common.CommonService;
import com.lpl.kled.service.systems.UserService;
/**
 * 
 * @ClassName: LoginController 
 * @Description: TODO(用户登录) 
 * @author hexin 
 * @date 2016年8月25日 下午2:07:06 
 *
 */
@Controller  
public class LoginController {
	@Autowired
    private UserService userService;  
	@Autowired
    private CommonService commonService;
    /**
     * @Title: login 
     * @Description: TODO(登录) 
     * @param  request
     * @param  model
     * @return String
     * @throws
     */
    @RequestMapping(value="/login",method=RequestMethod.GET)  
    public String login(HttpServletRequest request,Model model){  
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        User user = this.userService.getCurrentUserByAccount(userName);
        String md5PassWord=MD5.encodePassword(passWord);
        if(user != null && md5PassWord.equals(user.getPassword())){
        	SessionUtil.setAttr(request, "currentUser", user);
        	return "redirect:/main"; 
        }
        return "/index"; 
    }
    /**
     * 加载登录二维码图片
     * @Title: getSystemLoginQR 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param  response
     * @param  model
     * @return void    返回类型 
     * @throws
     */
    @RequestMapping(value = "/getSystemLoginQR")  
    public void getSystemLoginQR (HttpServletResponse response,Model model) throws IOException, SQLException{  
    	Img entity = commonService.querySystemQR("systemLoginQR");  
        byte[] content = entity.getContent();  
        response.setContentType("image/jpg");  
        response.setCharacterEncoding("UTF-8");  
        OutputStream outputSream = response.getOutputStream();  
        InputStream in = new ByteArrayInputStream(content);  
        int len = 0;  
        byte[] buf = new byte[1024];  
        while ((len = in.read(buf, 0, 1024)) != -1) {  
            outputSream.write(buf, 0, len);  
        }  
        outputSream.close();  
    }
}
