package com.laimi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laimi.mapper.AdvisoryMapper;
import com.laimi.pojo.Advisory;
import com.laimi.service.AdvisoryService;
import org.springframework.stereotype.Service;

/**
 * 咨询号码
 */
@Service
public class AdvisoryServiceImpl extends ServiceImpl<AdvisoryMapper, Advisory> implements AdvisoryService {
}
