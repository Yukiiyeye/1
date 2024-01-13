package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.AssetCategory;
import com.yyx.assetsys.entity.AssetLabel;
import com.yyx.assetsys.entity.Asset;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AssetMapper {



    @Select("select * from tb_asset where assetName LIKE #{assetName}")
    List<Asset> findAssetByAssetName(String assetName);

    @Select("SELECT * FROM tb_asset WHERE assetLabel LIKE #{assetLabel}")
    List<Asset> findAssetByAssetLabel(String assetLabel);

    @Select("SELECT * FROM tb_asset WHERE assetCategory LIKE #{assetCategory}")
    List<Asset> findAssetByAssetCategory(String assetCategory);
    @Select("SELECT * FROM tb_asset WHERE belongOrg LIKE #{belongOrg}")
    List<Asset> findAssetByBelongOrg(Integer belongOrg);

    @Select("SELECT * FROM tb_asset")
    List<Asset> findAllAsset();

    @Update("UPDATE tb_asset SET assetNo=#{assetNo}, assetTypeNo=#{assetTypeNo}, assetName=#{assetName}, assetLabel=#{assetLabel}, assetDesc=#{assetDesc}, assetCategory=#{assetCategory}, assetStatus=#{assetStatus}, registerTime=#{registerTime}, lastUpdatedTime=CURRENT_TIMESTAMP, belongOrg=#{belongOrg}, assetUser=#{assetUser}, ip=#{ip} WHERE id=#{id}")
    void updateAsset(Asset asset);

    @Insert("INSERT INTO tb_asset (assetNo,assetTypeNo,assetName,assetLabel,assetDesc,assetCategory,assetStatus,registerTime,lastUpdatedTime,belongOrg,assetUser,ip) VALUES (#{assetNo}, #{assetTypeNo}, #{assetName}, #{assetLabel}, #{assetDesc}, #{assetCategory}, #{assetStatus}, #{registerTime}, CURRENT_TIMESTAMP, #{belongOrg}, #{assetUser}, #{ip})")
    void addAsset(Asset asset);


    @Update("UPDATE tb_asset_category SET category=#{category} WHERE id=#{id}")
    void updateAssetCategory(AssetCategory assetCategory);

    @Update("UPDATE tb_asset_label SET label=#{label} WHERE id=#{id}")
    void updateAssetLabel(AssetLabel assetLabel);
    @Insert("INSERT INTO tb_asset_label(label) VALUES (#{label})")
    void addAssetLabel(AssetLabel assetlabel);
    @Insert("INSERT INTO tb_asset_category(category) VALUES (#{category})")
    void addAssetCategory(AssetCategory assetCategory);
    @Select("SELECT * FROM tb_asset_label")
    List<AssetLabel> findAllLabel();
    @Select("SELECT * FROM tb_asset_category")
    List<AssetCategory> findAllCategory();

}
