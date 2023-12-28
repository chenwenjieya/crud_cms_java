package com.cj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageInfo {
    @ApiModelProperty("当前页码")
    private Integer pageNum;
    @ApiModelProperty("每页显示条数")
    private Integer pageSize;

}
