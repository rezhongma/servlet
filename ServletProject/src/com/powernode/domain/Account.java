package com.powernode.domain;

/**
 * @Author 翁康
 * @Date 2023-04-07 14:29
 * @Description
 */
public class Account {
    private Long id;
    private String uname;
    private String pwd;

    public Account() {
    }

    public Account(Long id, String uname, String pwd) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
