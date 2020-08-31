package com.lj.springboot_shiro_jwt.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;//返回状态  200正常
    private String msg;//返回信息
    private Object data;//返回数据

    public static Result succeed(Object data) {
        return succeed(200, "操作成功", data);
    }

    public static Result succeed(int code, String mess, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(mess);
        return r;
    }

    public static Result fail(String mess) {
        return succeed(400, mess, null);
    }

    public static Result fail(int code, String mess, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(mess);
        return r;
    }
}
