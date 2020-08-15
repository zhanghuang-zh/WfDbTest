package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.zb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/findDeptAll")
    public String  findDeptAll(){
        return JSON.toJSONString(deptService.findDeptAll());
    }





}
