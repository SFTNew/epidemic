package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.entity.Admin;
import com.wataxi.epidemic.mapper.AdminMapper;
import com.wataxi.epidemic.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author yh200
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
