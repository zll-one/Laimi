package com.laimi.controller;

import com.alibaba.fastjson.JSONArray;
import com.laimi.pojo.Advisory;
import com.laimi.pojo.Review;
import com.laimi.service.AdvisoryService;
import com.laimi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

/**
 * 职称中心
 */
@Controller
@RequestMapping("/TitleCenter/")
public class TitleCenterController {
    @Autowired
    private ReviewService reviewService;//职称审核
    @Autowired
    private AdvisoryService advisoryService;//咨询号码

    //职称评审页面
    @RequestMapping("Conditional_review")
    public String Audit_conditions(){


        return "TitleCenter/Conditional_review";
    }

    //申报困扰
    @RequestMapping("perplex")
    public String perplex(){

        return "TitleCenter/perplex";
    }

    //一览表
    @RequestMapping("List")
    public String List(){

        return "TitleCenter/List";
    }

    //职称申报流程
     @RequestMapping("Reporting_process")
    public String  Reporting_process(){


        return "TitleCenter/Reporting_process";
     }

     //职称评审提交数据
     @PostMapping("addReview")
     @ResponseBody
    public Object addReview(Review review) {
         HashMap<String, Object> resultMap = new HashMap<String, Object>();
         Date date=new Date();//当前日期
         review.setDate(date);
         boolean  result= reviewService.save(review);//向数据库添加数据
         if(result){
             resultMap.put("addReview","true");
         }else{
             resultMap.put("addReview","false");
         }


         return JSONArray.toJSONString(resultMap);
     }

     //咨询号码提交
    @PostMapping("addAdvisory")
    @ResponseBody
    public Object addAdvisory(Advisory advisory){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        advisory.setAdvisory_data(new Date());
        advisory.setType(1);
        boolean result= advisoryService.save(advisory);//增加咨询数据
        if(result){
            resultMap.put("addAdvisory","true");
        }else{
            resultMap.put("addAdvisory","false");
        }
        return JSONArray.toJSONString(resultMap);
    }

}
