package com.dao;

import com.pojo.Forum;
import com.pojo.User;

/**
 * @Author : yjp
 * @Date : 2022/4/13 23:10
 */
public interface forumDao {
    //添加论文
    public int addForum(Forum forum) throws Exception;
    //删除指定论文
    public int deleteForum(int forumId) throws Exception;
}
