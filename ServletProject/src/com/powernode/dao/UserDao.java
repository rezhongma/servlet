package com.powernode.dao;

import com.powernode.domain.User;
import com.powernode.util.PageInfo;

import java.util.List;

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

    /**
     * 批量删除
     */
    int deleteBatchUser(Object[] id);

    /**
     * 修改记录
     */
    int updateUser(Object[] user);

    /**
     * 查询所有
     */
    List<User> selectUsers();

    /**
     * 根据id查询指定记录
     *
     * @param id 编号
     * @return
     */
    User selectUserById(String id);
    /**
     * 分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @return
     */
    PageInfo<User> selectUserByPage(int pageNum,int pageSize);

    /**
     * 根据name查询记录
     * @param name 姓名
     * @return
     */
    List<User>  selectUserByName(String name);

    /**
     * 根据tel查询
     * @param tel 电话号
     * @return
     */
    User selectUserByTel(String tel);
}
