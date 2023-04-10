package com.powernode.service.impl;

import com.powernode.common.Result;
import com.powernode.dao.AccountDao;
import com.powernode.dao.impl.AccountDaoImpl;
import com.powernode.domain.Account;
import com.powernode.service.AccountService;

/**
 * @Author 翁康
 * @Date 2023-04-07 18:55
 * @Description
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao=new AccountDaoImpl();
    @Override
    public Result findAccount(String uname, String pwd, String code, String captcha) {
        //1.数据校验 用户名: 字母，数字，  4-20    密码: 纯数字6-20
        if (uname==null||"".equals(uname)||!(uname.matches("[a-zA-Z0-9]{4,20}"))||
                pwd==null||"".equals(pwd)||!(pwd.matches("[0-9]{6,20}"))
        ){
            return Result.Data_Format_ERROR;
        }
        //验证码校验
        if (!captcha.equals(code)){
            return new Result(-1,"验证码错误");
        }
        //调用dao方法
        Account account=accountDao.selectAccount(uname,pwd);
        if (account==null){
            //登录失败
            return new Result(-1,"用户名或密码错误");
        }
        Result result=new Result();
        result.setData(account);
        return result;
    }
}
