package com.laimi.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laimi.pojo.Agency;
import com.laimi.pojo.User;
import com.laimi.service.UserService;
import com.laimi.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 * 用户
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    //联系我们
    @RequestMapping("Contact_us")
    public String Contact_us(){

        return "Contact_us";
    }
    //公司简介
    @RequestMapping("Company_profile")
    public String Company_profile(){

        return "Company_profile";
    }
     //公司企业文化页面
    @RequestMapping("Enterprise_culture")
    public String Enterprise_culture(){


        return "Enterprise_culture";
    }
    //修改密码页面
    @RequestMapping("user_password")
    public  String user_password(){

        return "Background/user-password";
    }
    //修改用户页面
    @RequestMapping("upUserPage")
    public String upUserPage(@RequestParam(value="user_id",required = false)Integer user_id,
                             Model model){
          model.addAttribute("user_id",user_id);
        return "Background/upUserPage";
    }
    //工程业绩
    @RequestMapping("Performance")
    public  String Performance(){

        return "Performance";
    }

    //查询用户名是否已使用
    @PostMapping("UserNameCount")
    @ResponseBody
    public Object UserNameCount(@RequestParam(value = "user_name",required = false)String user_name){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name",user_name);
        int count=userService.count(queryWrapper);//查询用户名是否已使用
        if(count>0){
            resultMap.put("UserNameCount","true");
        }else{
            resultMap.put("UserNameCount","false");
        }

        return JSONArray.toJSONString(resultMap);
    }
    //判断用户是否存在
    @PostMapping("nameCount")
    @ResponseBody
    public Object nameCount(@RequestParam(value = "user_name",required = false)String user_name){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name",user_name);
        int count=userService.count(queryWrapper);
        if(count>0){
            resultMap.put("nameCount","true");
        }else{
            resultMap.put("nameCount","false");
        }
        return JSONArray.toJSONString(resultMap);
    }
    //注册用户
    @PostMapping("addUser")
    @ResponseBody
    public Object addUser(User user){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        PasswordHelper passwordHelper=new PasswordHelper();
        Date date = new Date(System.currentTimeMillis());
        user.setDate(date);
        user.setPassword(passwordHelper.encryption(user));
        user.setSalt(user.getSalt());
        boolean count =userService.save(user);//用户数据添加
        if(count){
            resultMap.put("addUser","true");
        }else{
            resultMap.put("addUser","false");
        }
        return JSONArray.toJSONString(resultMap);
    }
    //查询旧密码是否正确
    @PostMapping("passwordCount")
    @ResponseBody
    public Object passwordCount(User user){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        String account=user.getUser_name();//用户名
        String password=user.getPassword();//密码
        UsernamePasswordToken token = new UsernamePasswordToken(account,password,false);//解密
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            resultMap.put("passwordCount","true");
            //此步将 调用realm的认证方法
        } catch(IncorrectCredentialsException e){
            //这最好把 所有的 异常类型都背会
            resultMap.put("passwordCount","false");

        } catch (AuthenticationException e) {
            resultMap.put("passwordCount","false");
        }

        return JSONArray.toJSONString(resultMap);
    }
    //删除用户
    @PostMapping("delUser")
    @ResponseBody
    public Object delUser(@RequestParam(value = "user_id",required = false)Integer user_id){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        boolean result= userService.removeById(user_id);//删除用户
        if(result){
            resultMap.put("delUser","true");
        }else{
            resultMap.put("delUser","false");
        }

        return JSONArray.toJSONString(resultMap);
    }
    //修改密码
    @PostMapping("upUser")
    @ResponseBody
    public Object upUser(@RequestParam(value = "user_id",required = false)Integer user_id,
                         @RequestParam(value = "password",required = false)String password,
                         @RequestParam(value = "user_name",required = false)String user_name){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        User user=new  User();
        user.setUser_id(user_id);
        user.setPassword(password);
        user.setUser_name(user_name);
        PasswordHelper passwordHelper=new PasswordHelper();
        user.setPassword(passwordHelper.encryption(user));
        user.setSalt(user.getSalt());
        boolean result= userService.updateById(user);//修改密码
        if(result){
            resultMap.put("upUser","true");
        }else{
            resultMap.put("upUser","false");
        }
        return JSONArray.toJSONString(resultMap);


    }
}
