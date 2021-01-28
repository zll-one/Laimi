package com.laimi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laimi.mapper.ReviewMapper;
import com.laimi.pojo.Review;
import com.laimi.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

}
