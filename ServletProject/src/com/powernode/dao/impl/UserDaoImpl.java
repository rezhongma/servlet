package com.powernode.dao.impl;

import com.powernode.dao.UserDao;
import com.powernode.domain.User;
import com.powernode.util.BaseDao;

/**
 * @Author 翁康
 * @Date 2023-04-06 14:34
 * @Description
 */
public class UserDaoImpl extends BaseDao implements UserDao{
    /**
     * 插入用户
     * @param user
     * @return
     */
    @Override
    public int insertUser(Object[] user) {

        return this.executeUpdate("insert  into user values(null,?,?,?,?,?,?);",user);
    }

    /**
     * 删除一条用户
     * @param id
     * @return
     */
    @Override
    public int deleteOneUser(Object id) {
        return this.executeUpdate("delete from user where id=?",id);
    }
}
