package com.laimi.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laimi.pojo.Advisory;
import com.laimi.pojo.Agency;
import com.laimi.pojo.Review;
import com.laimi.pojo.User;
import com.laimi.service.AdvisoryService;
import com.laimi.service.AgencyService;
import com.laimi.service.ReviewService;
import com.laimi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;

//后台
@Controller
@RequestMapping("/Background/")
public class BackgroundController {
    @Autowired
    private AgencyService agencyService;//资质
    @Autowired
    private ReviewService reviewService;//职称
    @Autowired
    private UserService userService;//用户
    @Autowired
    private AdvisoryService advisoryService;//职称号码
    //后台主页
    @RequestMapping("index")
    public String index(){

        return "Background/index";
    }
     //后台用户管理
    @RequestMapping("User_management")
    public String User_management(){

        return "Background/User_management";
    }
    //后台代办留言
    @RequestMapping("Agency_message")
    public String Agency_message(){

        return "Background/Agency_message";
    }
    //后台职称条件审核数据列表展示
    @RequestMapping("ConditionalReview")
    public String ConditionalReview(){


        return "Background/Conditional_reviewList";
    }
    //添加用户页面
    @RequestMapping("addUserPage")
    public String addUserPage(){

        return "Background/addUserPage";
    }

    //职称咨询号码数据页面
    @RequestMapping("Title_advisory")
    public String Title_advisory(){

        return "Background/Title_advisory";
    }

    //客服回复页面
    @RequestMapping("kefu")
    public ModelAndView kefu(){

        return  new ModelAndView(new RedirectView("https://yzf.qq.com/xv/html/login"));
    }

    /**--------**/

    //资质代办留言查询数据
    @PostMapping("MessageList")
    @ResponseBody
    public Object MessageList(Agency agency,
                              @RequestParam(value = "page" , required = false)Integer page,
                              @RequestParam(value = "limit" , required = false)Integer limit){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Page<Agency> agencyPage = new Page<>(page, limit);
        QueryWrapper<Agency> queryWrapper = new QueryWrapper<Agency>();
        queryWrapper.like(agency.getAgency_name() !=null && ! agency.getAgency_name().equals(""),"agency_name",agency.getAgency_name());//姓名模糊查询
        queryWrapper.eq(agency.getAgency_number() !=null && !agency.getAgency_number().equals(""),"agency_number",agency.getAgency_number());//手机号码
        queryWrapper.eq(agency.getType() !=null && !agency.getType().equals("") ,"type",agency.getType());//是否沟通
        agencyService.page(agencyPage,queryWrapper);
        long count =agencyPage.getTotal();
        List<Agency> getMessageList=agencyPage.getRecords();
        resultMap.put("count",count);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("data", getMessageList);
        return JSONArray.toJSONString(resultMap);
    }

    //修改资质代办留言为已沟通
    @PostMapping("upAgency")
    @ResponseBody
    public Object upAgency(@RequestParam(value="agency_id",required = false) Integer agency_id){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Agency agency=new Agency();
        agency.setAgency_id(agency_id);
        agency.setType(2);
        boolean result= agencyService.updateById(agency);//修改方法
        if(result){
            resultMap.put("upAgency", "true");
        }else{
            resultMap.put("upAgency", "false");
        }

        return JSONArray.toJSONString(resultMap);
    }
      //删除资质代办
      @PostMapping("delAgency")
      @ResponseBody
      public Object delAgency(@RequestParam(value="agency_id",required = false) Integer agency_id){
          HashMap<String, Object> resultMap = new HashMap<String, Object>();
          boolean result= agencyService.removeById(agency_id);//删除方法
          if(result){
              resultMap.put("delAgency", "true");
          }else{
              resultMap.put("delAgency", "false");
          }

          return JSONArray.toJSONString(resultMap);
      }

     //职称审核条件数据展示
     @PostMapping("Conditional_reviewList")
     @ResponseBody
     public Object Conditional_reviewList(Review review,
                                          @RequestParam(value = "page" , required = false)Integer page,
                                          @RequestParam(value = "limit" , required = false)Integer limit){
         HashMap<String, Object> resultMap = new HashMap<String, Object>();
         Page<Review> reviewPage = new Page<>(page, limit);
         QueryWrapper<Review> queryWrapper = new QueryWrapper<Review>();
         queryWrapper.eq(review.getType() !=null && !review.getType().equals(""),"type",review.getType());//是否已评审
         queryWrapper.eq(review.getEducation()!=null && !review.getEducation().equals(""),"education",review.getEducation());//学历
         queryWrapper.eq(review.getNumber() !=null && !review.getNumber().equals(""),"number",review.getNumber());//手机号码
         queryWrapper.like(review.getName() !=null && !review.getName().equals(""),"name",review.getName());//姓名
         queryWrapper.eq(review.getGraduation_time() !=null && !review.getGraduation_time().equals(""),"graduation_time",review.getGraduation_time());//毕业时长
         queryWrapper.eq(review.getReview_level() !=null && ! review.getReview_level().equals(""),"review_level",review.getReview_level());//评审级别

         reviewService.page(reviewPage,queryWrapper);
         long count =reviewPage.getTotal();
         List<Review> getConditional_reviewList=reviewPage.getRecords();
         resultMap.put("count",count);
         resultMap.put("code", 0);
         resultMap.put("msg", "");
         resultMap.put("data", getConditional_reviewList);

         return JSONArray.toJSONString(resultMap);
     }

     //更改为已评审
    @PostMapping("upReview")
    @ResponseBody
    public Object upReview(@RequestParam(value="review_id",required = false) Integer review_id){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Review review=new Review();
        review.setReview_id(review_id);
        review.setType(2);
        boolean result= reviewService.updateById(review);//更改为已评审
         if(result){
             resultMap.put("upReview","true");
         }else{
             resultMap.put("upReview","false");
         }
        return JSONArray.toJSONString(resultMap);
    }
    //删除评审信息
   @PostMapping("delReview")
   @ResponseBody
   public Object delReview(@RequestParam(value="review_id",required = false) Integer review_id){
       HashMap<String, Object> resultMap = new HashMap<String, Object>();
       boolean result=  reviewService.removeById(review_id);//删除
       if(result){
           resultMap.put("delReview","true");
       }else{
           resultMap.put("delReview","false");
       }
       return JSONArray.toJSONString(resultMap);
   }

  //用户信息数据展示
    @PostMapping("UserList")
    @ResponseBody
    public Object UserList(User user,
                           @RequestParam(value = "page" , required = false)Integer page,
                           @RequestParam(value = "limit" , required = false)Integer limit){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Page<User> userPage = new Page<>(page, limit);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like(user.getUser_name()!=null && ! user.getUser_name().equals(""),"user_name",user.getUser_name());//模糊查询用户名
        userService.page(userPage,queryWrapper);
        long count =userPage.getTotal();
        List<User> getUserList=userPage.getRecords();
        resultMap.put("count",count);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("data", getUserList);


        return JSONArray.toJSONString(resultMap);
    }

    //职称咨询号码数据
    @PostMapping("Title_advisoryList")
    @ResponseBody
    public Object Title_advisoryList(Advisory advisory,
                                     @RequestParam(value = "page" , required = false)Integer page,
                                     @RequestParam(value = "limit" , required = false)Integer limit){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Page<Advisory> advisoryPage = new Page<>(page, limit);
        QueryWrapper<Advisory> queryWrapper = new QueryWrapper<Advisory>();
        queryWrapper.eq(advisory.getType()!=null && !advisory.getType().equals(""),"type",advisory.getType());//是否已沟通
        queryWrapper.eq(advisory.getAdvisory_number()!=null && !advisory.getAdvisory_number().equals(""),"advisory_number",advisory.getAdvisory_number());//咨询号码
        advisoryService.page(advisoryPage,queryWrapper);//分页查询方法
        long count =advisoryPage.getTotal();
        List<Advisory> getAdvisoryList=advisoryPage.getRecords();
        resultMap.put("count",count);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("data", getAdvisoryList);
        return JSONArray.toJSONString(resultMap);
    }
      //修改资质号码为已沟通
     @PostMapping("upAdvisory")
     @ResponseBody
    public Object upAdvisory(@RequestParam(value="advisory_id",required = false) Integer advisory_id){
         HashMap<String, Object> resultMap = new HashMap<String, Object>();
         Advisory advisory=new Advisory();
         advisory.setType(2);
         advisory.setAdvisory_id(advisory_id);
         boolean result=  advisoryService.updateById(advisory);//修改方法
         if(result){
             resultMap.put("upAdvisory","true");
         }else{
             resultMap.put("upAdvisory","false");
         }
         return JSONArray.toJSONString(resultMap);
     }
     //删除资质号码
    @PostMapping("delAdvisory")
    @ResponseBody
    public Object delAdvisory(@RequestParam(value="advisory_id",required = false) Integer advisory_id){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        boolean result=  advisoryService.removeById(advisory_id);//删除方法
        if(result){
            resultMap.put("delAdvisory","true");
        }else{
            resultMap.put("delAdvisory","false");
        }
        return JSONArray.toJSONString(resultMap);

    }


}
