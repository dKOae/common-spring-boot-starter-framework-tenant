package com.code.demo.tenant.controller;

import cn.hutool.json.JSONUtil;
import com.code.demo.tenant.mapper.MenuMapper;
import com.code.demo.tenant.pojo.Menu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zdl
 * @date 2025/12/2
 * @description
 */

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuMapper menuMapper;

    @RequestMapping("/list")
    public String list() {
        return JSONUtil.toJsonStr(menuMapper.selectAll());
    }
    @RequestMapping("/save")
    public String add(@RequestParam("name") String name) {
        Menu menu = new Menu();
        menu.setName(name);
        menuMapper.insert(menu);
        return "menu add";
    }
}