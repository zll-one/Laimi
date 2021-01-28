package com.laimi.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 资质代办留言
 */
@TableName("agency")
public class Agency extends Model<Agency> implements Serializable {
    @TableId(value = "agency_id", type = IdType.AUTO)
    private Integer agency_id; //代办留言编号
    @TableField(value = "agency_name")
    private String agency_name;//代办人姓名
    @TableField(value = "consulting_business")
    private String consulting_business;//代办人业务
    @TableField(value = "address")
    private String address;//地址
    @TableField(value = "agency_number")
    private String agency_number;//代办人号码
    @TableField(value = "agency_date")
    private Date agency_date ;//代办人申请日期
    private Integer type;//是否已查看
    @TableField(value = "agency_message")
    private String agency_message;//代办人留言

    public Integer getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(Integer agency_id) {
        this.agency_id = agency_id;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getConsulting_business() {
        return consulting_business;
    }

    public void setConsulting_business(String consulting_business) {
        this.consulting_business = consulting_business;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgency_number() {
        return agency_number;
    }

    public void setAgency_number(String agency_number) {
        this.agency_number = agency_number;
    }

    public Date getAgency_date() {
        return agency_date;
    }

    public void setAgency_date(Date agency_date) {
        this.agency_date = agency_date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAgency_message() {
        return agency_message;
    }

    public void setAgency_message(String agency_message) {
        this.agency_message = agency_message;
    }
}
