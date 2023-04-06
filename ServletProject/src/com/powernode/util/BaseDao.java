package com.powernode.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC工具类:旨在为客户提供更加便捷的操作数据库的方式
 */
public class BaseDao {
    /**
     * 修改:可以执行添加、删除、修改单条操作
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql,Object...params){
        Connection conn = null;
        PreparedStatement st = null;
        int i=0;
        try {
            //参数校验
            if(sql==null || "".equals(sql.trim())){
                return 0;
            }
            //获取连接
            conn = DBUtils.getConn();
            //创建Statement对象
            st = conn.prepareStatement(sql);
            //给sql赋值参数
            for (int n=0;params!=null && params.length>=1 &&n<params.length;n++){
                st.setObject(n+1,params[n]);
            }
            //向mysql发送sql语句，接受结果
            i = st.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            DBUtils.close(null,st,conn);
        }
        return i;
    }

    /**
     * 批量删除
     * @param sql
     * @param params
     * @return
     */
    public int executeBatchDel(String sql,Object...params){
        Connection conn = null;
        PreparedStatement st = null;
        int[] arr = null;
        try {
            //基本参数校验
            if(sql==null || "".equals(sql.trim())){
                return 0;
            }
            //获取连接
            conn = DBUtils.getConn();
            //在同一个事务中执行
            conn.setAutoCommit(false);
            st = conn.prepareStatement(sql);
            //给参数赋值
            for (int i = 0;params!=null && params.length>=1 && i < params.length; i++) {
                st.setObject(1,params[i]);
                st.addBatch();
            }
            //执行sql
            arr = st.executeBatch();//[0,0,0,0]
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //释放资源
            DBUtils.close(null,st,conn);
        }
        return arr.length;
    }

    /**
     * 查询多条记录
     * @param sql
     * @param c
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> executeQueryList(String sql, Class<T> c ,Object...params){
        Connection conn= null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<T> dataList = new ArrayList<>();//{}
        try {
            //参数校验
            if(sql==null || "".equals(sql.trim())){
                return dataList;
            }
            //获取Connection连接
            conn = DBUtils.getConn();
            //创建PrepareStatement对象
            st = conn.prepareStatement(sql);
            for (int i = 0;params!=null && params.length>=1 && i < params.length; i++) {
                st.setObject(i+1,params[i]);
            }
            //发送sql，接受结果
            rs = st.executeQuery();
            //获取查询数据的字段个数
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for(;rs.next();){//取记录的次数
                //创建一个c字节码的对象，用来装一条记录
                T t = c.newInstance();
                for (int i = 1; i <=columnCount ; i++) {
                    //获取某个字段名称
                    String columnName = metaData.getColumnName(i);
                    //获取字段对应的值
                    Object value = rs.getObject(columnName);
                    //内部for全部执行完了，则代表一条记录取出来了
                    Field field = c.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,value);
                    field.setAccessible(false);
                }
                //将对象装入到List中
                dataList.add(t);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtils.close(rs,st,conn);
        }
        return dataList;
    }

    /**
     * 查询单条记录
     * @param sql
     * @param c
     * @param param
     * @param <T>
     * @return
     */
    public <T> T executeQueryOne(String sql,Class<T> c,Object...param){
        return executeQueryList(sql,c,param).size()==0?null:executeQueryList(sql,c,param).get(0);
    }

    /**
     * 分页查询
     * @param sql 基本分页sql，不用写limit
     * @param pageNum 页码
     * @param pageSize 每页显示的数据条数
     * @param c   某一条数据的类型
     * @param params  分页太哦建
     * @param <T>
     * @return
     */
    public <T> PageInfo<T> executeQueryPage(String sql,int pageNum,int pageSize,Class<T> c,Object...params){
        Connection conn=null;
        PreparedStatement st = null;
        ResultSet rs = null;
        PageInfo<T> pageInfo = new PageInfo<>();
        try {
            //校验参数
            if(sql==null || "".equals(sql.trim())){
                return pageInfo;
            }
            if(pageNum<=0 || pageSize<=0){
                return pageInfo;
            }
            if(c==null){
                return pageInfo;
            }
            //去除;
            sql = sql.replaceAll(";","");
            //获取数据库连接:Connection对象
            conn = DBUtils.getConn();
            //准备sql语句
            //创建PrepareStatement对象  (此处有个;的小bug)
            String newSql = sql + " limit "+(pageNum-1)*pageSize+","+pageSize+"";
            st = conn.prepareStatement(newSql);
            //给sql占位符?赋值
            for (int i = 0;params!=null && params.length>=1 && i < params.length; i++) {
                st.setObject(i+1,params[i]);
            }
            //执行sql，处理结果
            rs = st.executeQuery();
            //数据表头信息
            ResultSetMetaData metaData = rs.getMetaData();
            //获取查询sql语句中字段的个数
            int columnCount = metaData.getColumnCount();
            for (;rs.next();){
                //创建一个对象，用于接收一条记录
                T t = c.newInstance();
                for (int i = 1; i <=columnCount ; i++) {
                    //获取字段名
                    String columnName = metaData.getColumnName(i);
                    //获取字段值
                    Object columnValue = rs.getObject(columnName);
                    //将对应字段的值装入到实体类对象中去
                    Field field = c.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                    field.setAccessible(false);
                }
                //将某一条记录放入到List集合中去
                //往pageInfo对象中添加分页数据
                pageInfo.getData().add(t);
            }
            //往pageInfo对象中添加总记录数
            long total = executeQueryCount(sql);
            pageInfo.setTotal(total);
            //往pageInfo对象中添加总页数
            long temp = total % pageSize;//3%2=1
            int pages = temp==0? (int)(total/pageSize): (int)Math.ceil((double)total/(double)pageSize);
            pageInfo.setPages(pages);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            DBUtils.close(rs,st,conn);
        }
        return pageInfo;
    }

    /**
     * 统计满足sql条件的总记录数
     * @param sql
     * @return
     */
    private long executeQueryCount(String sql){
        long total = 0;
        Connection conn=null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConn();
            String newSql = "select count(*) from ("+sql+") t";
            st = conn.prepareStatement(newSql);
            rs = st.executeQuery();
            for (;rs.next();){
               total = rs.getLong("count(*)");
            }
            return total;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.close(rs,st,conn);
        }
        return total;
    }
}
