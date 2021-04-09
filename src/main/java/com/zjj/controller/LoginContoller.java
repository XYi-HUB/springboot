package com.zjj.controller;

import com.alibaba.fastjson.JSON;
import com.zjj.dao.UserDao;
import com.zjj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginContoller {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flag = "error";
        User us = userDao.getUserByMessage(user.getUsername(), user.getPassword());
        HashMap<Object, Object> res = new HashMap<>();
        if (us !=null){
            flag = "ok";
        }
        res.put("flag",flag);
        res.put("user",user);
        System.out.println("us"+us);
        String json = JSON.toJSONString(res);
        return json;
    }
}
