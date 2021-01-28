package com.laimi.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 职称评审条件
 */
@TableName("review")
public class Review extends Model<Review> implements Serializable {
       @TableId(value = "review_id", type = IdType.AUTO)
       private Integer review_id;//评审编号
       private String  education;//学历
       @TableField(value = "graduation_time")
       private String  graduation_time;//毕业时长
       @TableField(value = "review_level")
       private String  review_level;//评审级别
       private String  name;//提交人姓名
       private String  number;//提交人手机号
       private Date date; //提交日期
       private Integer type;//是否评审

    //get set封装
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduation_time() {
        return graduation_time;
    }

    public void setGraduation_time(String graduation_time) {
        this.graduation_time = graduation_time;
    }

    public String getReview_level() {
        return review_level;
    }

    public void setReview_level(String review_level) {
        this.review_level = review_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
