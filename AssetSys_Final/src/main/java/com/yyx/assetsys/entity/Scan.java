package com.yyx.assetsys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Scan {
    private Integer id;
    private String scanName;
    private String tester;
    private Integer mytask_id;
    private String scanTarget;
    private String scanPolicy;
    private Date scanTime;
    private String foundVul;
    private String vulNo;
    private String scanTools;
    private String riskLevel;
}
