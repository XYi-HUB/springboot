package com.zjj.dao;

import com.zjj.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User getUserByMessage(@Param("username") String username, @Param("password") String password);

    //查询用户
    public List<User> getAllUser(@Param("username")String username,@Param("pageStart")int PageStart,@Param("pageSize")int pageSize);

    //获取用户数
    public int getUserCounts(@Param("username")String username);

    //修改状态
    public int updateState(Integer id,Boolean state);

    //添加用户
    public int addUser(User user);

    //删除方法
    public int deleteUser(int id);

    //获取修改用户
    public User getUpdateUser(int id);

    //修改用户
    public int editUser(User user);
}
