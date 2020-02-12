package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Admin;
import com.wataxi.epidemic.service.AdminService;
import com.wataxi.epidemic.utils.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public R login(@RequestParam("username") String username,@RequestParam("password") String password){
        Admin one = adminService.getOne(new QueryWrapper<Admin>().eq("name", username).eq("pwd", PasswordEncryption.encode(password)));
        if (null == one){
            return R.error(2,"账号或密码错误");
        }
        return R.success("登录成功");
    }

    @Transactional
    @PostMapping("/")
    public R addAdmin(String username,String password){
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName(username);
        admin.setPwd(PasswordEncryption.encode(password));
        admin.setSign(0);
        adminService.save(admin);
        return R.success("");
    }

    @Transactional
    @PutMapping("/{id}")
    public R updateAdmin(@PathVariable Integer id, String username,String password,Integer sign){
        Admin byId = adminService.getById(id);
        if(byId == null){
            return R.error(1,"");
        }
        byId.setSign(sign);
        byId.setName(username);
        byId.setPwd(PasswordEncryption.encode(password));
        return R.success("");
    }

    @GetMapping("/")
    public R listAdmin(){
        List<Admin> list = adminService.list(new QueryWrapper<>());
        return R.success(list);
    }
}
