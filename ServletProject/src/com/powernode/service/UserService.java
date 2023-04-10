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

    /**
     * 批量删除
     */
    Result removeBatchUser(String[] id);

    /**
     * 修改用户
     */
    Result modifyUser(String uname,String sex,String tel,String profession,String sal,String address,String id);

    /**
     * 查询所有用户
     */
    Result findUsers();

    /**
     * 根据ID查询用户
     */
    Result findUserById(String id);

    /**
     * 分页查询
     */
    Result findUserByPage(String pageNum,String pageSize);

    /**
     * 根据name查询用户
     */
    Result findUserByName(String name);

    /**
     * 根据电话查询用户
     */
    Result findUserByTel(String tel);
}
