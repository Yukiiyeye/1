package com.yyx.assetsys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Mytask {
    private Integer id;
    private Integer user_id;
    private Integer task_id;
    private Short finished_status;//完成状态
    private Date finished_time;
    private Integer submit_scan_num;//漏洞扫描提交次数
    private Integer submit_exploit_num;//漏洞利用提交次数
    private Integer submit_move_num;//横向移动提交次数
    private String taskName;
    private Integer relatedOrg;
    private String taskStatus;//任务状态
    private Date startTime;
    private Date endTime;

}
