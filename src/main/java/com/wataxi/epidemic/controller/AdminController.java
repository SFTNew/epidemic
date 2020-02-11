package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Admin;
import com.wataxi.epidemic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yh200
 */
@RestController
@RequestMapping("/admin/v1")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R login(){
        return R.success("登录成功");
    }
    @GetMapping("/")
    public R listAdmin(){
        List<Admin> list = adminService.list(new QueryWrapper<>());
        return R.success(list);
    }
}
