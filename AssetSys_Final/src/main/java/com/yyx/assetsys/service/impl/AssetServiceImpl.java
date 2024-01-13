package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.entity.AssetCategory;
import com.yyx.assetsys.entity.AssetLabel;
import com.yyx.assetsys.entity.Asset;
import com.yyx.assetsys.mapper.AssetMapper;
import com.yyx.assetsys.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetMapper assetMapper;
    @Override
    public List findAssetByAssetName(String assetName) {
        return assetMapper.findAssetByAssetName("%" + assetName + "%");

    }


    @Override
    public List findAssetByAssetLabel(String assetLabel) {
        return assetMapper.findAssetByAssetLabel("%" + assetLabel + "%");
    }

    @Override
    public List findAssetByAssetCategory(String assetCategory) {
        return assetMapper.findAssetByAssetCategory("%" + assetCategory + "%");
    }

    @Override
    public List findAssetByBelongOrg(Integer fatherOrg) {
        return assetMapper.findAssetByBelongOrg(fatherOrg);
    }

    @Override
    public List<Asset> findAllAsset() {
        return assetMapper.findAllAsset();
    }

    @Override
    public void addAsset(Asset asset) {
        assetMapper.addAsset(asset);

    }

    @Override
    public void updateAsset(Asset asset) {
        assetMapper.updateAsset(asset);

    }

    @Override
    public void addAssetCategory(AssetCategory assetCategory) {
        assetMapper.addAssetCategory(assetCategory);
    }

    @Override
    public void addAssetLabel(AssetLabel assetLabel) {
        assetMapper.addAssetLabel(assetLabel);

    }

    @Override
    public void updateAssetCategory(AssetCategory assetCategory) {
        assetMapper.updateAssetCategory(assetCategory);

    }

    @Override
    public void updateAssetLabel(AssetLabel assetLabel) {
        assetMapper.updateAssetLabel(assetLabel);

    }


    @Override
    public List findAllLabel() {
        return assetMapper.findAllLabel();
    }

    @Override
    public List findAllCategory() {
        return assetMapper.findAllCategory();
    }
}
