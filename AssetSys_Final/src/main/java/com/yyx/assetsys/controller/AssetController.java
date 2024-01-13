package com.yyx.assetsys.controller;

import com.yyx.assetsys.entity.Asset;
import com.yyx.assetsys.entity.AssetCategory;
import com.yyx.assetsys.entity.AssetLabel;
import com.yyx.assetsys.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yyx.assetsys.entity.Result;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @GetMapping("/findAssetByAssetCategory")
    public List findAssetByAssetCategory(String assetCategory){
        return assetService.findAssetByAssetCategory(assetCategory);
    }
    @GetMapping("/findAssetByBelongOrg")
    public List findAssetByBelongOrg(Integer belongOrg){
        return assetService.findAssetByBelongOrg(belongOrg);
    }
    @GetMapping("/findAllAsset")
    public List<Asset> findAllAsset(){
        return assetService.findAllAsset();
    }

    @GetMapping("/findAssetByAssetName")
    public List findAssetByAssetName(String assetName){
        return  assetService.findAssetByAssetName(assetName);
    }

    @GetMapping("/findAssetByAssetLabel")
    public List findAssetByAssetLabel(String assetLabel){
        return assetService.findAssetByAssetLabel(assetLabel);
    }
    @PostMapping("/addAsset")
    public String addAsset(@RequestBody Asset asset){
        assetService.addAsset(asset);
        return Result.success().getMessage();
    }
    @PostMapping("/updateAsset")
    public String updateAsset(@RequestBody Asset asset){
        assetService.updateAsset(asset);
        return Result.success().getMessage();
    }
    @PostMapping("/addAssetLabel")
    public String addLabel( AssetLabel assetlabel){
        assetService.addAssetLabel(assetlabel);
        return Result.success().getMessage();
    }
    @PostMapping("/updateAssetCategory")
    public String updateAssetCategory(@RequestBody AssetCategory AssetCategory){
        assetService.updateAssetCategory(AssetCategory);
        return Result.success().getMessage();
    }
    @PostMapping("/updateAssetLabel")
    public String updateAssetLabel(@RequestBody AssetLabel assetlabel){
        assetService.updateAssetLabel(assetlabel);
        return Result.success().getMessage();
    }
    @PostMapping("/addAssetCategory")
    public String addCategory(AssetCategory AssetCategory){
        assetService.addAssetCategory(AssetCategory);
        return Result.success().getMessage();
    }
    @GetMapping("/findAllLabel")
    public List findAllLabel(){
        return assetService.findAllLabel();
    }
    @GetMapping("/findAllCategory")
    public List findAllCategory(){
        return assetService.findAllCategory();
    }
}
