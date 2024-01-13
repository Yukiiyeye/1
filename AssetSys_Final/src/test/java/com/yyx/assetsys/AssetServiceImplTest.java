package com.yyx.assetsys;

import com.yyx.assetsys.entity.Asset;
import com.yyx.assetsys.entity.AssetCategory;
import com.yyx.assetsys.entity.AssetLabel;
import com.yyx.assetsys.mapper.AssetMapper;
import com.yyx.assetsys.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssetServiceImplTest {

    @InjectMocks
    private AssetServiceImpl assetServiceimpl;

    @Mock
    private AssetMapper assetMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAssetByAssetName() {
        String assetName = "test";
        when(assetMapper.findAssetByAssetName("%" + assetName + "%")).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAssetByAssetName(assetName);
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAssetByAssetName("%" + assetName + "%");
    }
    @Test
    public void testFindAssetByAssetLabel() {
        String assetLabel = "test";
        when(assetMapper.findAssetByAssetLabel("%" + assetLabel + "%")).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAssetByAssetLabel(assetLabel);
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAssetByAssetLabel("%" + assetLabel + "%");
    }
    @Test
    public void testFindAssetByAssetCategory() {
        String assetCategory = "test";
        when(assetMapper.findAssetByAssetCategory("%" + assetCategory + "%")).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAssetByAssetCategory(assetCategory);
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAssetByAssetCategory("%" + assetCategory + "%");
    }
    @Test
    public void testFindAssetByBelongOrg() {
        Integer belongOrg = 1;
        when(assetMapper.findAssetByBelongOrg(belongOrg)).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAssetByBelongOrg(belongOrg);
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAssetByBelongOrg(belongOrg);
    }
    @Test
    public void testFindAllAsset() {
        when(assetMapper.findAllAsset()).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAllAsset();
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAllAsset();
    }
    @Test
    public void testAddAsset() {
        Asset asset = new Asset();
        assetServiceimpl.addAsset(asset);
        verify(assetMapper, times(1)).addAsset(asset);
    }
    @Test
    public void testUpdateAsset() {
        Asset asset = new Asset();
        assetServiceimpl.updateAsset(asset);
        verify(assetMapper, times(1)).updateAsset(asset);
    }
    @Test
    public void testAddAssetCategory() {
        AssetCategory assetCategory = new AssetCategory();
        assetServiceimpl.addAssetCategory(assetCategory);
        verify(assetMapper, times(1)).addAssetCategory(assetCategory);
    }
    @Test
    public void testAddAssetLabel() {
        AssetLabel assetLabel = new AssetLabel();
        assetServiceimpl.addAssetLabel(assetLabel);
        verify(assetMapper, times(1)).addAssetLabel(assetLabel);
    }
    @Test
    public void testUpdateAssetCategory() {
        AssetCategory assetCategory = new AssetCategory();
        assetServiceimpl.updateAssetCategory(assetCategory);
        verify(assetMapper, times(1)).updateAssetCategory(assetCategory);
    }
    @Test
    public void testUpdateAssetLabel() {
        AssetLabel assetLabel = new AssetLabel();
        assetServiceimpl.updateAssetLabel(assetLabel);
        verify(assetMapper, times(1)).updateAssetLabel(assetLabel);
    }
    @Test
    public void testFindAllLabel() {
        when(assetMapper.findAllLabel()).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAllLabel();
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAllLabel();
    }
    @Test
    public void testFindAllCategory() {
        when(assetMapper.findAllCategory()).thenReturn(Collections.emptyList());
        List result = assetServiceimpl.findAllCategory();
        assertEquals(Collections.emptyList(), result);
        verify(assetMapper, times(1)).findAllCategory();
    }



}
