package com.powernode.dao;

import com.powernode.domain.Account;

/**
 * @Author 翁康
 * @Date 2023-04-07 17:21
 * @Description
 */
public interface AccountDao {
    /**
     *
     * @param uname 用户名
     * @param pwd   密码
     * @return
     */
    Account selectAccount(String uname, String pwd);
}
