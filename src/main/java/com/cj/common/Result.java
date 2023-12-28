package com.cj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code; // 200
    private String msg; // "操作成功"
    private T data; // 数据


    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    public static <T> Result success(T data) {
        return new Result(200, "操作成功", data);
    }


    public static Result error(String msg) {
        return new Result(500, msg, null);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error() {
        return new Result(500, "程序异常，请联系管理员", null);
    }


}
