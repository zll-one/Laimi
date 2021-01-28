package com.laimi.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 咨询号码
 */
@TableName("advisory")
public class Advisory extends Model<Advisory> implements Serializable {
       @TableId(value = "advisory_id", type = IdType.AUTO)
       private Integer advisory_id;//咨询id
       @TableField(value = "advisory_number")
       private String advisory_number;//咨询号码
       @TableField(value = "advisory_data")
       private Date advisory_data;//咨询日期
       private Integer type;//是否已沟通

    public Integer getAdvisory_id() {
        return advisory_id;
    }

    public void setAdvisory_id(Integer advisory_id) {
        this.advisory_id = advisory_id;
    }

    public String getAdvisory_number() {
        return advisory_number;
    }

    public void setAdvisory_number(String advisory_number) {
        this.advisory_number = advisory_number;
    }

    public Date getAdvisory_data() {
        return advisory_data;
    }

    public void setAdvisory_data(Date advisory_data) {
        this.advisory_data = advisory_data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
