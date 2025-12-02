package com.code.demo.tenant.controller;

import cn.hutool.json.JSONUtil;
import com.code.demo.tenant.mapper.UserMapper;
import com.code.demo.tenant.pojo.User;
import com.code.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zdl
 * @date 2025/12/2
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;


    @PostMapping("/save")
    public String get(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insert(user);
        return "success";
    }

    @GetMapping("/get")
    public String get(@RequestParam("username") String username) {
        return JSONUtil.toJsonStr(userMapper.selectOne(new LambdaQueryWrapperX<User>().eq(User::getUsername, username)));
    }
}