package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.dao.DeptDao;
import com.zb.pojo.Dept;
import com.zb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptDao deptDao;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public List<Dept> findDeptAll() {
        String key = "findDeptAll";
        if(redisTemplate.hasKey(key)){
            System.out.println("redis中查询");
            String json = redisTemplate.opsForValue().get(key).toString();
            List<Dept> list = JSON.parseArray(json,Dept.class);
            return list;
        }else{
            System.out.println("db查询");
            List<Dept> list = deptDao.findDeptAll();
            String json = JSON.toJSONString(list);
            redisTemplate.opsForValue().set(key,json,60, TimeUnit.SECONDS);
            for (Dept dept : list) {
                Map<String,Object> data = new HashMap<String,Object>();
                data.put("id",dept.getId()+"");
                data.put("deptname",dept.getDeptName());
                redisTemplate.opsForHash().putAll("dept:"+dept.getId(),data);
            }
            return list;
        }
    }
}
