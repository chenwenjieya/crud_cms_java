package com.cj.dto.user;

import com.cj.dto.PageInfo;
import lombok.Data;

@Data
public class UserQuery extends PageInfo {

    private String username;
    private Long roleId;
}
