package com.laimi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laimi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public  interface UserMapper extends BaseMapper<User> {


}
