package com.powernode.service;

import com.powernode.common.Result;

/**
 * @Author 翁康
 * 用户接口
 * @Date 2023-04-06 14:54
 * @Description
 */
public interface UserService {
    /**
     * 添加用户
     */
    Result savaUser(String uname,String sex,String tel,String profession,String sal,String address);

    /**
     * 删除用户
     */
    Result removeUser(String id);
}
