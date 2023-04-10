package com.powernode.dao.impl;

import com.powernode.dao.UserDao;
import com.powernode.domain.User;
import com.powernode.util.BaseDao;
import com.powernode.util.PageInfo;

import java.util.List;

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

    /**
     * 删除多条用户
     * @param id
     * @return
     */
    @Override
    public int deleteBatchUser(Object[] id) {
        return this.executeBatchDel("delete from user where id=?",id);

    }

    /**
     * 修改记录
     * @param user
     * @return
     */
    @Override
    public int updateUser(Object[] user) {
        return this.executeUpdate("update user set uname=?,sex=?,tel=?,profession=?,sal=?,address=? where id=?",user);
    }

    @Override
    public List<User> selectUsers() {
        return this.executeQueryList("select * from user",User.class);
    }

    @Override
    public User selectUserById(String id) {
        return this.executeQueryOne("select * from user where id=?",User.class,id);
    }

    @Override
    public PageInfo<User> selectUserByPage(int pageNum, int pageSize) {
        return this.executeQueryPage("select * from user",pageNum,pageSize,User.class);
    }

    @Override
    public List<User> selectUserByName(String name) {
        return this.executeQueryList("select * from user where uname=?",User.class,name);
    }

    @Override
    public User selectUserByTel(String tel) {
        return this.executeQueryOne("select * from user where tel=?",User.class,tel);
    }
}
