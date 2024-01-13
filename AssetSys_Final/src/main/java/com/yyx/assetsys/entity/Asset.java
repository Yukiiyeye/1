package com.yyx.assetsys.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Asset {
    private Integer id;
    private String assetNo;
    private String assetTypeNo;
    private String assetName;
    private String assetLabel;
    private String assetDesc;
    private String assetCategory;
    private String assetStatus;
    private Date registerTime;
    private Date lastUpdatedTime;
    private Integer belongOrg;
    private String assetUser;
    private String ip;
}
