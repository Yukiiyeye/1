package com.yyx.assetsys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Task {
    private Integer id;
    private String taskName;
    private String taskDesc;
    private Integer relatedOrg;
    private String joinedTesters;
    private Short taskStatus;
    private Date startTime;
    private Date endTime;
    private String bugNum;
}
