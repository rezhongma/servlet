package com.powernode.common;

import javax.xml.crypto.Data;

/**
 * @Author 翁康
 * 通用结果类
 * @Date 2023-04-06 15:01
 * @Description
 */
public class Result {

    /**
     * 错误码:正确200 错误-1
     */
    private int code=200;

    /**
     * 错误的消息
     */
    private String msg="";

    /**
     *满足条件的记录数
     */
    private Long total;

    /**
     *满足条件的数据
     */
    private Object data;

    //做一个静态常量
    public  static  final Result Data_Format_ERROR=new Result(-1,"数据格式错误");
    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Long total, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
