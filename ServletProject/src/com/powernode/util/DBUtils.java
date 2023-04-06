package com.powernode.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 自定义JDBC工具类:1)提供获取链接Connection的方法   2)关闭资源的方法  3)实现jdbc配置软编码
 */
public final class DBUtils {
    /**
     * 驱动名称
     */
    private static String driverClassName;
    /**
     * 链接信息
     */
    private static String url;
    /**
     * 用户名
     */
    private static String username;
    /**
     * 用户密码
     */
    private static String password;

    static {
        try {
            //关联.properties配置文件
            Properties prop = new Properties();
            InputStream ins = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(ins);
            //读取.properties配置文件的属性值
            driverClassName = prop.getProperty("driverClassName");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            Class.forName(driverClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭资源
     *
     * @param acs
     */
    public static void close(AutoCloseable acs) {
        try {
            if (acs != null) {
                acs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     *
     * @param rs
     * @param st
     * @param conn
     */
    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
