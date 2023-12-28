package com.cj.dto;

import lombok.Data;

@Data
public class UserQuery extends PageInfo{

    private String username;
    private Long roleId;
}
