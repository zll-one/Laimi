package com.laimi.controller;


import com.alibaba.fastjson.JSONArray;
import com.laimi.pojo.Agency;
import com.laimi.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

//资质
@Controller
@RequestMapping("/Qualification/")
public class QualificationController {
    @Autowired
    private RedisTemplate redisTemplate;//Redis缓存
    @Autowired
    private AgencyService agencyService;

    //买卖
    @RequestMapping("Business")
    public String Business(){

        return "Qualification/Business";
    }

    //办理价格查询
    @RequestMapping("Price_inquiry")
    public String Price_inquiry(){

        return "Qualification/Price_inquiry";
    }

    //资质代办
    @RequestMapping("agency")
    public String agency(){

        return "Qualification/agency";
    }

    //资质代办留言提交数据
    @PostMapping("addAgency")
    @ResponseBody
    public Object addAgency(Agency agency){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Date date=new Date();//当前日期
        agency.setAgency_date(date);
        redisTemplate.opsForValue().set(agency,agency);//存入缓存
        Agency agency1 = (Agency)redisTemplate.opsForValue().get(agency);//从缓存中取出
        boolean  result=  agencyService.save(agency1);
        redisTemplate.delete(agency);//清除Redis
          if(result){
              resultMap.put("addAgency","true");
          }else{
              resultMap.put("addAgency","false");
          }

        return JSONArray.toJSONString(resultMap);
    }

    //资质案例展示
    @RequestMapping("Case")
     public String Case(){

        return "Qualification/Case";
    }
    //工程业绩展示页面
    @RequestMapping("Project_performance")
    public String Project_performance(){


        return "Qualification/Project_performance";
    }



}
