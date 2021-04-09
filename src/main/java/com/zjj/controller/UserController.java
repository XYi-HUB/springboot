package com.zjj.controller;

import com.alibaba.fastjson.JSON;
import com.zjj.dao.UserDao;
import com.zjj.entity.QueryInfo;
import com.zjj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/allUser")
    public String getUserList(QueryInfo queryInfo){
        //获取最大列表数
        int numbers = userDao.getUserCounts("%" + queryInfo.getQuery() + "%");
        System.out.println(queryInfo.getQuery());
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> users = userDao.getAllUser("%" + queryInfo.getQuery() + "%", pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("number",numbers);
        res.put("data",users);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/userstate")
    public String updateUserDate(@RequestParam("id")Integer id,@RequestParam("state")Boolean state){
        int i = userDao.updateState(id,state);
        return i>0 ? "success":"error";
    }

    @RequestMapping("/adduser")
    public String addUser(@RequestBody User user){
        user.setRole("普通用户");
        user.setState(false);
        int i = userDao.addUser(user);
        return i>0 ?"success":"error";
    }

    @RequestMapping("/deleteuser")
    public String deleteUser(int id){
        int i = userDao.deleteUser(id);
        return i> 0?"success":"error";
    }

    @RequestMapping("/getupdate")
    public String getUpdateUser(int id){
        User user = userDao.getUpdateUser(id);
        String json_user = JSON.toJSONString(user);
        return json_user;
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        int i = userDao.editUser(user);
        return i> 0?"success":"error";
    }
}
