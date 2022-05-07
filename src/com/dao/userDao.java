package com.dao;

import com.pojo.User;

import java.util.Vector;

/**
 * @Author : yjp
 * @Date : 2022/4/13 22:27
 */
public interface userDao {
    //添加用户
    public boolean addUser(User user) throws Exception;
    //删除指定用户
    public int deleteUser(String id) throws Exception;
    //更新用户信息
    public int updateUser(User user) throws Exception;
    //用户登录
    boolean LoginUser(String id, String password, String identity) throws Exception;
    //查询用户
    public String search(String id) throws Exception;
    //是否拉黑用户
    public int block(String id, String identity) throws Exception;
}
