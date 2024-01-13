package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.AssetCategory;
import com.yyx.assetsys.entity.AssetLabel;
import com.yyx.assetsys.entity.Asset;

import java.util.List;

public interface AssetService {
    List findAssetByAssetName(String assetName);
    List findAssetByAssetLabel(String assetLabel);

    List findAssetByAssetCategory(String assetCategory);
    List findAssetByBelongOrg(Integer belongOrg);
    List<Asset> findAllAsset();

    void addAsset(Asset asset);
    void updateAsset(Asset asset);


    void updateAssetCategory(AssetCategory assetCategory);
    void updateAssetLabel(AssetLabel assetLabel);

    void addAssetLabel(AssetLabel assetLabel);
    void addAssetCategory(AssetCategory assetCategory);

    List findAllLabel();
    List findAllCategory();
}
