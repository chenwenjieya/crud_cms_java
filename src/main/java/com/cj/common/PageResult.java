package com.cj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private Integer code; // 200
    private String msg; // "操作成功"
    private T data; // 数据
    private  Long total; // 总条数
    private  Long size; // 每页显示条数
    private  Long current; // 当前页



    public PageResult success(T data, Long total, Long size, Long current){
        return new PageResult(200, "操作成功", data, total,size, current);
    }




}
