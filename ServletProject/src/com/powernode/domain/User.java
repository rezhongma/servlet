package com.powernode.domain;

/**
 * @Author 翁康
 * @Date 2023-04-06 14:27
 * @Description
 */
public class User {
    private Long id;
    private String uname;
    private String sex;
    private String tel;
    private String profession;
    private Float sal;
    private String address;

    public User() {
    }

    public User(Long id, String uname, String sex, String tel, String profession, Float sal, String address) {
        this.id = id;
        this.uname = uname;
        this.sex = sex;
        this.tel = tel;
        this.profession = profession;
        this.sal = sal;
        this.address = address;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(Float sal) {
        this.sal = sal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", profession='" + profession + '\'' +
                ", sal=" + sal +
                ", address='" + address + '\'' +
                '}';
    }
}
