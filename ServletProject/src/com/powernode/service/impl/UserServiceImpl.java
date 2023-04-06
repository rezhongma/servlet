package com.powernode.service.impl;

import com.powernode.common.Result;
import com.powernode.dao.UserDao;
import com.powernode.dao.impl.UserDaoImpl;
import com.powernode.service.UserService;

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
        Object parma=id;
        int i=this.userDao.deleteOneUser(parma);
        if (i==0){
            return new Result(-1,"删除失败");
        }
        return new Result();
    }
}
