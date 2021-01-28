package com.laimi.controller;


import com.alibaba.fastjson.JSONArray;
import com.laimi.pojo.User;
import com.laimi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/Login/")
public class LoginController {
     @Autowired
     private UserService userService;

    //登录页面
    @RequestMapping("login")
    public String login(){

        return "login";
    }
    //登录后台
    @PostMapping("userLogin")
    @ResponseBody
    public Object userLogin(User user){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        String account=user.getUser_name();//用户名
        String password=user.getPassword();//密码
        UsernamePasswordToken token = new UsernamePasswordToken(account,password,false);//解密
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            resultMap.put("userLogin","true");
            //此步将 调用realm的认证方法
        } catch(IncorrectCredentialsException e){
            //这最好把 所有的 异常类型都背会
            resultMap.put("userLogin","false");
            resultMap.put("message","密码错误");

        } catch (AuthenticationException e) {
            resultMap.put("userLogin","false");
            resultMap.put("message","登录失败");

        }
        return JSONArray.toJSONString(resultMap);
    }
    //退出登录
    @RequestMapping("out")
    public String logout(HttpServletRequest request){
        //subject的实现类DelegatingSubject的logout方法，将本subject对象的session清空了
        //即使session托管给了redis ，redis有很多个浏览器的session
        //只要调用退出方法，此subject的、此浏览器的session就没了
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "login";

    }
}
