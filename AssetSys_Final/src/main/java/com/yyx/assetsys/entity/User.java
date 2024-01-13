package com.yyx.assetsys.entity;

import lombok.Data;



@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String remark;
    private Integer role;
}

