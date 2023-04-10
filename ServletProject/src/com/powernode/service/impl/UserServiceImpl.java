package com.powernode.service.impl;

import com.powernode.common.Result;
import com.powernode.dao.UserDao;
import com.powernode.dao.impl.UserDaoImpl;
import com.powernode.domain.User;
import com.powernode.service.UserService;
import com.powernode.util.PageInfo;

import java.util.List;

/**
 * @Author 翁康
 * @Date 2023-04-06 15:24
 * @Description
 */
public class UserServiceImpl  implements UserService {
   //定义dao层对象
    private UserDao userDao=new UserDaoImpl();
    @Override
    public Result savaUser(String uname,String sex,String tel,String profession,String sal,String address) {
//        1、业务逻辑校验（数据校验）
        if (null==uname||"".equals(uname)||!(uname.matches("[\\u4e00-\\u9fa5]{2,10}"))){
            //2.数据格式有误
            return Result.Data_Format_ERROR;
        }
        //定义一个Object类型的参数
        Object[] params={uname,sex,tel,profession,sal,address};
        //3.调用dao层方法
        int i=this.userDao.insertUser(params);
        //对i判断
        if (i==0){
            //给出提示
            return new Result(-1,"添加失败");
        }
        return new Result();
    }

    @Override
    public Result removeUser(String id) {
        Object param=id;
        int i=this.userDao.deleteOneUser(param);
        if (i==0){
            return new Result(-1,"删除失败");
        }
        return new Result();
    }

    @Override
    public Result removeBatchUser(String[] id) {
        Object[] params=id;
        int i=this.userDao.deleteBatchUser(params);
        if (i==0){
            return new Result(-1,"删除失败");
        }
        return new Result();
    }

    @Override
    public Result modifyUser(String uname, String sex, String tel, String profession, String sal, String address, String id) {
        Object[] params={uname,sex,tel,profession,sal,address,id};
        int i=this.userDao.updateUser(params);
        if (i==0){
            return new Result(-1,"修改失败");
        }
        return new Result();
    }

    @Override
    public Result findUsers() {
        List<User> list=this.userDao.selectUsers();
        Result result=new Result();
        result.setData(list);
        return result;
    }

    @Override
    public Result findUserById(String id) {
        if (id==null|| "".equals(id)||!(id.matches("[1-9][0-9]{0,}"))){
            return Result.Data_Format_ERROR;
        }
        User u=this.userDao.selectUserById(id);
        Result result=new Result();
        result.setData(u);
        return result;
    }

    @Override
    public Result findUserByPage(String pageNum, String pageSize) {
        if (pageNum==null||"".equals(pageNum)||!(pageNum.matches("[1-9][0-9]{0,}"))){
              return Result.Data_Format_ERROR;
        }
        if (pageSize==null||"".equals(pageSize)||!(pageSize.matches("[1-9][0-9]{0,}"))){
            return Result.Data_Format_ERROR;
        }
        PageInfo<User> list=this.userDao.selectUserByPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
       Result result=new Result();
       result.setData(list.getData());
       result.setTotal(list.getTotal());
        return result;
    }

    @Override
    public Result findUserByName(String name) {
        if (null==name||"".equals(name)){
            return Result.Data_Format_ERROR;
        }
        List<User> list=this.userDao.selectUserByName(name);
        Result result=new Result();
        result.setData(list);
        return result;
    }

    @Override
    public Result findUserByTel(String tel) {
        if (null==tel||"".equals(tel)){
            return Result.Data_Format_ERROR;
        }
        User list=this.userDao.selectUserByTel(tel);
        Result result=new Result();
        result.setData(list);
        return result;
    }
}
