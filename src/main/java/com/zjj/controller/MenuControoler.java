package com.zjj.controller;

import com.alibaba.fastjson.JSON;
import com.zjj.dao.MenuDao;
import com.zjj.entity.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuControoler {

    @Autowired
    private MenuDao menuDao;

    @RequestMapping("/menus")
    public String getAllMenu(){
        HashMap<String, Object> data = new HashMap<>();
        int status = 404;
        List<MainMenu> menus = menuDao.getMenus();
        if (menus!=null){
            data.put("menus",menus);
            data.put("flag",200);
        }else{
            data.put("flag",404);
        }
        String jsonString = JSON.toJSONString(data);
        return jsonString;
    }
}
