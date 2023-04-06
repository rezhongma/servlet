package com.powernode.dao;

import com.powernode.domain.User;

/**
 * 用户dao
 */
public interface UserDao {
    /**
     * 添加一条记录
     */
    int insertUser(Object[] user);

    /**
     * 删除一条记录
     */
    int deleteOneUser(Object id);
}
