package com.yyx.assetsys.entity;

import lombok.Data;

@Data
public class Perm {
    private Integer id;
    private String name;
    private String url;
    private Integer parent_id;
    private String type;
    private String permit;
    private String remark;
}
