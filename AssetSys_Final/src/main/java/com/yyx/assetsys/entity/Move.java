package com.yyx.assetsys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Move {
    private Integer mytask_id;
    private String moveName;
    private String tester;
    private Date attackTime;
    private String targetIP;
    private String exploitVuls;
    private String movePath;
    private String moveResult;
    private Date moveTime;
}
