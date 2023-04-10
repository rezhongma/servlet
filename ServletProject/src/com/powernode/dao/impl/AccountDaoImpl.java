package com.powernode.dao.impl;

import com.powernode.dao.AccountDao;
import com.powernode.domain.Account;
import com.powernode.util.BaseDao;

/**
 * @Author 翁康
 * @Date 2023-04-07 17:23
 * @Description
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {
    @Override
    public Account selectAccount(String uname, String pwd) {
        return this.executeQueryOne("select * from account where uname=? and pwd=?",Account.class,uname,pwd);
    }
}
